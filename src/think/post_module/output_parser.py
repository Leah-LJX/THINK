import re
from ljx_extract_code import save_code
from parafill_prompt import PARAFILL_PROMPT
from typing import Union

from langchain.agents.agent import AgentOutputParser
from langchain.schema import AgentAction, AgentFinish, OutputParserException


class ReActOutputParser(AgentOutputParser):
    """Output parser for the ReAct agent."""

    def parse(self, text: str) -> Union[AgentAction, AgentFinish]:
        action_prefix = "Action: "
        if not text.strip().split("\n")[-1].startswith(action_prefix):
    
            action_input = re.findall(r"```java(.*?)```", text, re.DOTALL)
            if action_input:
                content = action_input[0].strip()
                if re.findall(r"class (.*?)\{", content, re.DOTALL):
                    file_name = re.findall(r"class (.*?)\{", content, re.DOTALL)[0].strip() + ".java"
                    if ' ' in file_name:
                        file_name = file_name.split(' ')[0] + '.java'
                    save_code("results/extraction_code/" + file_name, content) 
                    return AgentFinish({"output": content}, text)
            else:
                raise OutputParserException(f"Could not parse LLM Output: {text}")

        action_block = text.strip().split("\n")[-1]

        action_str = action_block[len(action_prefix):]
        # Parse out the action and the directive.
        re_matches = re.search(r"(.*?)\[(.*?)\]", action_str)
        if re_matches is None:
            raise OutputParserException(
                f"Could not parse action directive: {action_str}"
            )
        action, action_input = re_matches.group(1), re_matches.group(2)
        if action == "Finish":
            return AgentFinish({"output": action_input}, text)
        else:
            return AgentAction(action, action_input, text)

    @property
    def _type(self) -> str:
        return "react"