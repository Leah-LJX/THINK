from typing import Any, List, Optional, Sequence


from langchain.agents.agent import Agent, AgentExecutor, AgentOutputParser     
from langchain.agents.agent_types import AgentType
from ljx_output_parser import ReActOutputParser

from textworld_prompt import TEXTWORLD_PROMPT
from wiki_prompt import WIKI_PROMPT
from parafill_prompt import PARAFILL_PROMPT
from methodcall_prompt import METHODCALL_PROMPT
from langchain.vectorstores import FAISS
from langchain.docstore.wikipedia import Wikipedia
from langchain.chat_models import ChatOpenAI
import os
from langchain.schema import HumanMessage, SystemMessage
from langchain.agents import initialize_agent
from langchain.vectorstores import VectorStore
import re
import openai
from langchain.embeddings import OpenAIEmbeddings

from langchain.agents.tools import Tool
from langchain.agents.utils import validate_tools_single_input
from langchain.docstore.base import Docstore
from langchain.docstore.document import Document
from langchain.pydantic_v1 import Field
from langchain.schema import BasePromptTemplate
from langchain.schema.language_model import BaseLanguageModel
from langchain.tools.base import BaseTool


openai.api_base = "your-api-base"
openai.api_key = "your-api-key"
os.environ["OPENAI_API_KEY"] = "your-api-key"
os.environ['OPENAI_API_BASE'] = "your-api-base"


class ReActDocstoreAgent(Agent):
    """Agent for the ReAct chain."""

    output_parser: AgentOutputParser = Field(default_factory=ReActOutputParser)

    @classmethod                                          
    def _get_default_output_parser(cls, **kwargs: Any) -> AgentOutputParser:
        return ReActOutputParser()

    @property                                            
    def _agent_type(self) -> str:
        """Return Identifier of an agent type."""
        return AgentType.REACT_DOCSTORE

    @classmethod
    def create_prompt(cls, tools: Sequence[BaseTool]) -> BasePromptTemplate:
        """Return default prompt."""
        return WIKI_PROMPT

    @classmethod
    def _validate_tools(cls, tools: Sequence[BaseTool]) -> None:
        validate_tools_single_input(cls.__name__, tools)
        super()._validate_tools(tools)
        if len(tools) != 2:
            raise ValueError(f"Exactly two tools must be specified, but got {tools}")
        tool_names = {tool.name for tool in tools}
        if tool_names != {"Lookup", "Search"}:
            raise ValueError(
                f"Tool names should be Lookup and Search, got {tool_names}"              
            )

    @property
    def observation_prefix(self) -> str:
        """Prefix to append the observation with."""
        return "Observation: "

    @property
    def _stop(self) -> List[str]:                         
        return ["\nObservation:"]

    @property
    def llm_prefix(self) -> str:
        """Prefix to append the LLM call with."""          
        return "Thought:"


class ReActVectorStoreParaFillAgent(Agent):
    """Agent for the ReAct chain."""

    output_parser: AgentOutputParser = Field(default_factory=ReActOutputParser)

    @classmethod                                          
    def _get_default_output_parser(cls, **kwargs: Any) -> AgentOutputParser:
        return ReActOutputParser()

    @property                                              
    def _agent_type(self) -> str:
        """Return Identifier of an agent type."""
        return "ljx-test-parafill"

    @classmethod
    def create_prompt(cls, tools: Sequence[BaseTool]) -> BasePromptTemplate:
        """Return default prompt."""
        return PARAFILL_PROMPT

    @classmethod
    def _validate_tools(cls, tools: Sequence[BaseTool]) -> None:
        validate_tools_single_input(cls.__name__, tools)
        super()._validate_tools(tools)
        if len(tools) != 1:
            raise ValueError(f"Exactly one tools must be specified, but got {tools}")
        tool_names = {tool.name for tool in tools}
        if tool_names != {"Search"}:
            raise ValueError(
                f"Tool names should be Search, got {tool_names}"
            )

    @property
    def observation_prefix(self) -> str:
        """Prefix to append the observation with."""
        return "Observation: "

    @property
    def _stop(self) -> List[str]:                          
        return ["\nObservation:"]

    @property
    def llm_prefix(self) -> str:
        """Prefix to append the LLM call with."""         
        return "Thought:"


class ReActVectorStoreMethodCallAgent(Agent):
    """Agent for the ReAct chain."""

    output_parser: AgentOutputParser = Field(default_factory=ReActOutputParser)

    @classmethod                                           
    def _get_default_output_parser(cls, **kwargs: Any) -> AgentOutputParser:
        return ReActOutputParser()

    @property                                             
    def _agent_type(self) -> str:
        """Return Identifier of an agent type."""
        return "ljx-test-methodcall"

    @classmethod
    def create_prompt(cls, tools: Sequence[BaseTool]) -> BasePromptTemplate:
        """Return default prompt."""
        return METHODCALL_PROMPT

    @classmethod
    def _validate_tools(cls, tools: Sequence[BaseTool]) -> None:
        validate_tools_single_input(cls.__name__, tools)
        super()._validate_tools(tools)
        if len(tools) != 1:
            raise ValueError(f"Exactly one tools must be specified, but got {tools}")
        tool_names = {tool.name for tool in tools}
        if tool_names != {"Search"}:
            raise ValueError(
                f"Tool names should be Search, got {tool_names}"
            )

    @property
    def observation_prefix(self) -> str:
        """Prefix to append the observation with."""
        return "Observation: "

    @property
    def _stop(self) -> List[str]:                          
        return ["\nObservation:"]

    @property
    def llm_prefix(self) -> str:
        """Prefix to append the LLM call with."""          
        return "Thought:"

class DocstoreExplorer:
    """Class to assist with exploration of a document store."""

    def __init__(self, docstore: Docstore):
        """Initialize with a docstore, and set initial document to None."""
        self.docstore = docstore
        self.document: Optional[Document] = None
        self.lookup_str = ""
        self.lookup_index = 0

    def search(self, term: str) -> str:
        """Search for a term in the docstore, and if found save."""
        result = self.docstore.search(term)                          
        if isinstance(result, Document):
            self.document = result
            return self._summary
        else:
            self.document = None
            return result

    def lookup(self, term: str) -> str:
        """Lookup a term in document (if saved)."""
        if self.document is None:
            raise ValueError("Cannot lookup without a successful search first")
        if term.lower() != self.lookup_str:
            self.lookup_str = term.lower()
            self.lookup_index = 0
        else:
            self.lookup_index += 1
        lookups = [p for p in self._paragraphs if self.lookup_str in p.lower()]
        if len(lookups) == 0:
            return "No Results"
        elif self.lookup_index >= len(lookups):
            return "No More Results"
        else:
            result_prefix = f"(Result {self.lookup_index + 1}/{len(lookups)})"
            return f"{result_prefix} {lookups[self.lookup_index]}"

    @property
    def _summary(self) -> str:
        return self._paragraphs[0]

    @property
    def _paragraphs(self) -> List[str]:
        if self.document is None:
            raise ValueError("Cannot get paragraphs without a document")
        return self.document.page_content.split("\n\n")


class ReActTextWorldAgent(ReActDocstoreAgent):
    """Agent for the ReAct TextWorld chain."""

    @classmethod
    def create_prompt(cls, tools: Sequence[BaseTool]) -> BasePromptTemplate:
        """Return default prompt."""
        return TEXTWORLD_PROMPT

    @classmethod
    def _validate_tools(cls, tools: Sequence[BaseTool]) -> None:
        validate_tools_single_input(cls.__name__, tools)
        super()._validate_tools(tools)
        if len(tools) != 1:
            raise ValueError(f"Exactly one tool must be specified, but got {tools}")
        tool_names = {tool.name for tool in tools}
        if tool_names != {"Play"}:
            raise ValueError(f"Tool name should be Play, got {tool_names}")                 


class ReActChain(AgentExecutor):
    """[Deprecated] Chain that implements the ReAct paper."""

    def __init__(self, llm: BaseLanguageModel, docstore: Docstore, **kwargs: Any):
        """Initialize with the LLM and a docstore."""
        docstore_explorer = DocstoreExplorer(docstore)
        tools = [
            Tool(
                name="Search",
                func=docstore_explorer.search,
                description="Search for a term in the docstore.",
            ),
            Tool(
                name="Lookup",
                func=docstore_explorer.lookup,
                description="Lookup a term in the docstore.",
            ),
        ]
        agent = ReActDocstoreAgent.from_llm_and_tools(llm, tools)                  
        super().__init__(agent=agent, tools=tools, **kwargs)

embeddings = OpenAIEmbeddings(model="text-embedding-ada-002", openai_api_base="",
                openai_api_key="")  

class ParafillReActChain(AgentExecutor):
    def search(self, query):
        result = []
        num = 1  # need mod

        if self.vector_name == "gpt4lib":
            vec_store_absPath = "my-javatext\\javadoc_gpt4_store.faiss"  # need mod
        if self.vector_name == "codegen4lib":
            vec_store_absPath = "my-javatext\\javadoc_code4lib_store.faiss"


        vector_store = FAISS.load_local(vec_store_absPath, embeddings=embeddings)

        for q in query.split(','):
            content = ''
            search_result = ''
            if False:  
                class_fullname = extract_full_lass_name(q)
                javadoc_path = dataPath + class_fullname.replace(".", "\\") + ".txt"
    
                if os.path.exists(rootPath + javadoc_path):
                    question = q.split('in class')[0].strip()
                    print(question, javadoc_path)
                    res = vector_store.similarity_search_with_score(question, k=num,
                                                                    filter=dict(source=javadoc_path))  # ,
                    
                    if not res:
                        res = vector_store.similarity_search_with_score(q, k=num)  
                       
            else:
                res = vector_store.similarity_search_with_score(q)  # k=num, q = question
               
                print(f"----- res3: {res}")


            max_results = len(res)
            while num <= max_results:
                content_found = False
                for doc in res[:num]:
                    api = doc[0].page_content.split('\n\n')

                    if "inherit" in q or "inherits" in q or "extend" in q or "extends" in q or "implement" in q or "implements" in q:
                        content = '\n'.join(api) + '\n'
                        search_result = content.strip()
                        content_found = True
                        break
                    else:
                        filter_api = [re.sub(' {2,}', '', x.strip()) for x in api if '(' in x and ')' in x]  
                        if filter_api:
                            content = '\n'.join(filter_api) + '\n'
                            search_result = content.strip()
                            content_found = True
                            break

                if content_found:
                    break
                else:
                    num += 1

            result.append(search_result.strip())

        return '\n'.join(result)


    def __init__(self, llm: BaseLanguageModel, **kwargs: Any):
        """Initialize with the LLM and a vector store."""
        tools = [
            Tool(
                name="Search",
                func=self.search,
                description="Search for a chunk in the vector store.",
            )
        ]
        agent = ReActVectorStoreParaFillAgent.from_llm_and_tools(llm, tools)                 

        super().__init__(agent=agent, tools=tools, **kwargs)

# sk-VihoDwcIR8PqyVdIB647AbE9D2794a9e958b1cDcEe677dAb

def extract_full_lass_name(text):
    """
    从字符串中提取完全限定的 Java 类名。
    """
    # 正则表达式匹配完全限定的类名
    pattern = re.compile(r'\b([a-zA-Z_][\w\.]*\.[A-Z]\w*)\b')
    match = pattern.search(text)
    if match:
        return match.group(1)
    else:
        return "No class name found."

class MethodCallReActChain(AgentExecutor):
    

    def search(self, query):
        result = []
        jar = "org.apache.pdfbox"  # 设置为self.jar
        num = 1  # need mod
        rootPath = ""
        dataPath = f"data\\mydoc-gpt4-txt\\{jar}\\"  # need mod

        if self.vector_name == "gpt4lib":
            vec_store_absPath = "my-javatext\\javadoc_gpt4_store.faiss"  # need mod
        if self.vector_name == "codegen4lib":
            vec_store_absPath = "my-javatext\\javadoc_code4lib_store.faiss"


        vector_store = FAISS.load_local(vec_store_absPath, embeddings=embeddings)
        for q in query.split(','):
            content = ''
            search_result = ''
            if False:  
                class_fullname = extract_full_lass_name(q)
                javadoc_path = dataPath + class_fullname.replace(".", "\\") + ".txt"
                
                if os.path.exists(rootPath + javadoc_path):
                    question = q.split('in class')[0].strip()
                    print(question, javadoc_path)
                    res = vector_store.similarity_search_with_score(question, k=num,
                                                                    filter=dict(source=javadoc_path))  # ,
                    
                    if not res:
                        res = vector_store.similarity_search_with_score(q, k=num)  # q = question
                        
            else:
                res = vector_store.similarity_search_with_score(q)  # k=num, q = question
            

            max_results = len(res)
            while num <= max_results:
                content_found = False
                for doc in res[:num]:
                    noclass_api = doc[0].page_content.split('\n\n')
                    source = doc[0].metadata['source'].split('\\')[-1][:-4]
                    api = []
                    for per in noclass_api:
                        if '(' in per and ')' in per:
                            per = per.split(')')[0] + f"): class {source}, Description: " + per[per.find(')')+1:]
                            api.append(per)


                    if "inherit" in q or "inherits" in q or "extend" in q or "extends" in q or "implement" in q or "implements" in q:
                        content = '\n'.join(api) + '\n'
                        search_result = content.strip()
                        content_found = True
                        break
                    else:
                        filter_api = [re.sub(' {2,}', '', x.strip()) for x in api if '(' in x and ')' in x]  
                        if filter_api:
                            content = '\n'.join(filter_api) + '\n'
                            search_result = content.strip()
                            content_found = True
                            break

        

                if content_found:
                    break
                else:
                    num += 1


            result.append(search_result.strip())

        return '\n'.join(result)

    def __init__(self, llm: BaseLanguageModel, **kwargs: Any):
        """Initialize with the LLM and a vector store."""
        tools = [
            Tool(
                name="Search",
                func=self.search,
                description="Search for a chunk in the vector store.",
            )
        ]
        agent = ReActVectorStoreMethodCallAgent.from_llm_and_tools(llm, tools)                  
        super().__init__(agent=agent, tools=tools, **kwargs)
