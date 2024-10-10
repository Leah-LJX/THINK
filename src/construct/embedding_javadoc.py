# old
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.vectorstores import FAISS
from langchain.embeddings.openai import OpenAIEmbeddings
import os
from langchain.text_splitter import HTMLHeaderTextSplitter
from langchain.document_loaders import UnstructuredHTMLLoader, DirectoryLoader

# new
# from langchain_community.vectorstores import FAISS
# from langchain_community.embeddings import OpenAIEmbeddings
# from langchain_openai import OpenAIEmbeddings
# from langchain_community.document_loaders import UnstructuredHTMLLoader, DirectoryLoader

import os
import openai
# 环境配置
openai.api_key = "sk-U9gJd75cGm3Lj25qVxfTFgDVSTBFaBvdvpomMWTbP5DUEBp5"  # gpt-api-free
openai.api_base = "https://api.chatanywhere.cn/v1" # (国外服务器使用)
os.environ['OPENAI_API_KEY'] = "sk-U9gJd75cGm3Lj25qVxfTFgDVSTBFaBvdvpomMWTbP5DUEBp5"
os.environ['OPENAI_API_BASE'] = "https://api.chatanywhere.cn/v1" # (国外服务器使用)

# openai.api_key = 'sk-qIuSz3viVs5jYMcA3d9d49E5Cf5d427d9d7eF2F01a4b655c'    #sk-Sd8LlhnDT8Fph3FtAa63Ce0d20E3431cB63bF6F419E41c94'
# openai.api_base = "https://api.zhiyunai168.com/v1"
#
# os.environ['OPENAI_API_KEY'] = 'sk-qIuSz3viVs5jYMcA3d9d49E5Cf5d427d9d7eF2F01a4b655c'   #sk-Sd8LlhnDT8Fph3FtAa63Ce0d20E3431cB63bF6F419E41c94'
# os.environ['OPENAI_API_BASE'] = "https://api.zhiyunai168.com/v1"
#
# openai.api_key = 'sk-VihoDwcIR8PqyVdIB647AbE9D2794a9e958b1cDcEe677dAb'    #sk-Sd8LlhnDT8Fph3FtAa63Ce0d20E3431cB63bF6F419E41c94'
# openai.api_base = "https://openkey.cloud"
# os.environ['OPENAI_API_KEY'] = 'sk-VihoDwcIR8PqyVdIB647AbE9D2794a9e958b1cDcEe677dAb'   #sk-Sd8LlhnDT8Fph3FtAa63Ce0d20E3431cB63bF6F419E41c94'
# os.environ['OPENAI_API_BASE'] = "https://openkey.cloud"

os.environ["http_proxy"] = "http://127.0.0.1:7078"
os.environ["https_proxy"] = "http://127.0.0.1:7078"

# 构建向量库
def create_vectorbase(dir_path, faiss_name):

    file_type = "**/*.txt"
    persist_dir = "data/vector_store/langchain/my-javatext/"  # javadoc --> html kg_database
    # 初始化openai的embeddings对象
    # embeddings = OpenAIEmbeddings(model="text-embedding-ada-002", api_key="sk-VihoDwcIR8PqyVdIB647AbE9D2794a9e958b1cDcEe677dAb",
    #                               base_url="https://openkey.cloud/v1")  # "text-embedding-ada-002"  # model="text-embedding-3-large"

    embeddings = OpenAIEmbeddings(model="text-embedding-ada-002")

    # faiss_name = 'javadoc_code4lib_store.faiss'
    if not os.path.exists(f"data/vector_store/langchain/my-javatext/{faiss_name}"):
        # 加载文件夹中所有xx类型的文件
        loader = DirectoryLoader(dir_path, glob=file_type)
        documents = loader.load()

        # 构造faiss向量数据库
        text_splitter = RecursiveCharacterTextSplitter(separators=["\n\n", "\n"], chunk_size=230, chunk_overlap=0)  # code default 1000, 10
        split_docs = text_splitter.split_documents(documents)
        vectore_store = FAISS.from_documents(split_docs, embeddings)
        vectore_store.save_local(f"data/vector_store/langchain/my-javatext/{faiss_name}")  # my for math: javadoc_faiss_store.faiss
        print("Finish!")
    else:
        print(f'Loading {faiss_name} ...')
        vectore_store = FAISS.load_local(f"data/vector_store/langchain/my-javatext/{faiss_name}",  # my for math: javadoc_faiss_store.faiss
                                         embeddings=embeddings)

    return vectore_store

if __name__ == '__main__':
    faiss_name = 'javadoc_code4lib_store.faiss'
    # faiss_name = 'javadoc_faiss_store.faiss' # for math task
    faiss_name = 'javadoc_gpt4_store.faiss'

    dir_path = "data/mydoc-txt/"
    dir_path = "data/mydoc-code4lib-txt/"
    dir_path = "data/mydoc-gpt4-txt"

    vector_store = create_vectorbase(dir_path, faiss_name)

    # question = "Translate this sentence from English to French. I love programming."
    # completion = openai.ChatCompletion.create(
    #     model="gpt-3.5-turbo-1106",  # default 0613
    #     messages=[
    #         {"role": "user", "content": question}
    #     ],
    #     temperature=0.0
    # )  # temperature=0.0
    # openai_res = completion['choices'][0]['message']['content']
    # print(openai_res)


    print(f'Calculating similarity ...')
    try:
        # res = vector_store.similarity_search("DefaultFlowDataset")

        embedding_vector = OpenAIEmbeddings().embed_query("DefaultFlowDataset")
        docs = vector_store.similarity_search_by_vector(embedding_vector)

        print(docs[0].page_content)
    #     res = vector_store.similarity_search("In class Array2DRowRealMatrix, which method is similar to outerProduct", filter=dict(source='data\\mydoc-txt\\org.apache.commons.math3.linear\\Array2DRowRealMatrix.txt'), fetch_k=100)
    #     print(f'res: {res}')
    except openai.error.InvalidRequestError as e:
        print(f"An error occurred: {e}")


    # 执行搜索
    # res = vectore_store.search("For SingularValueDecomposition, All Known Implementing Classes are: ", "similarity")  #search_type: similarity, mmr, similarity_score_threshold
    # print(f'Calculating similarity ...')
    # res = vector_store.search("DefaultFlowDataset", "similarity")
    # res = vectore_store.search("In class Array2DRowRealMatrix, which methood is similar to outerProduct", "similarity")
    # res = vectore_store.search("DefaultFlowDataset, All Implemented Interfaces are: ", "similarity")
    # print(res)