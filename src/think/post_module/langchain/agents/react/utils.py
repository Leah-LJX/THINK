import os
from langchain.chat_models import ChatOpenAI
from langchain.schema import HumanMessage, SystemMessage
import openai


openai.api_base = ""
openai.api_key = ""
os.environ["OPENAI_API_KEY"] = ""
os.environ['OPENAI_API_BASE'] = ""

def list_class(root_path, class_list):
    for temp_path in os.listdir(root_path):
        file_path = os.path.join(root_path, temp_path)
        if os.path.exists(file_path):
            if os.path.isfile(file_path):
                class_list.append(file_path.replace('\\', '/'))
            else:
                list_class(file_path, class_list)
    return class_list

def check_words_exist(sentence1, sentence2):
    words1 = [word.strip(",.!?") for word in sentence1.split()]
    words2 = [word.strip(",.!?") for word in sentence2.split()]

    if set(words1).issubset(set(words2)):
        return True
    else:
        return False

def load_3_5_model(llm_model, temperature=0.0):
    # llm_model = "gpt-3.5-turbo"
    chatModel = ChatOpenAI(model=llm_model, temperature=temperature)  # temperature=0.0
    # print(chatModel)
    return chatModel

def chat(chatModel, prompt):
    messages = [
        SystemMessage(
            content="You are a helpful assistant."
        ),
        HumanMessage(
            content=prompt
        ),
    ]
    response = chatModel(messages)
    return response.content

def model_3_5_test():
    chatModel = load_3_5_model("gpt-3.5-turbo")  
    prompt = "Translate this sentence from English to French. I love programming."
    messages = [
        SystemMessage(
            content="You are a helpful assistant."
        ),
        HumanMessage(
            content=prompt
        ),
    ]
    response = chatModel(messages)
    print(response.content)
