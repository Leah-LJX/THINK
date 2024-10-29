from langchain.vectorstores import FAISS
from langchain.chat_models import ChatOpenAI
import openai
from langchain.embeddings import OpenAIEmbeddings
from check_syntax import check_withEclipse, ignore_warnings, Signal, parse_generate_code
from utils import check_words_exist
from Error import Error
import os, re
import pandas as pd
import shutil
from lable_rag import process_chunks, expand_query, construct_code_example


use_example = True

openai.api_base = "" 
openai.api_key = ""
os.environ['OPENAI_API_KEY'] = ""
os.environ['OPENAI_API_BASE'] = ""

def load_database(database_path):
    embeddings = OpenAIEmbeddings(model="text-embedding-ada-002", openai_api_base="",
                openai_api_key="",)
    vector_store = FAISS.load_local(database_path, embeddings=embeddings)

    return vector_store

def search_kg(signature, description, vector_store, top_k=5, example_path='default-example-path'):

    question_step_list, step_str = expand_query(description)
    simi_search = []
    for step in question_step_list:
        temp_search = vector_store.similarity_search_with_score(step)

        temp_search = sorted(temp_search, key=lambda x: x[1], reverse=True)[0][0]
        simi_search.append(temp_search)

    query = signature + " " + description
    simi_search_ori = vector_store.similarity_search(query, k=top_k)
    simi_search.extend(simi_search_ori)

    query = query + '\n' + step_str
    label_filter_result = process_chunks(simi_search, query)  # query

    filter_nonapi = [] 
    for api in label_filter_result:
        if not ('(' in api and ')' in api):
            if 'enum' in api or 'Enum' in api or (len(api.split(' ')) > 1 and api.split(' ')[-1].isupper()):
                pass
            if 'Direct Known Subclasses' in api or 'All Known Implementing Classes' in api:
                pass
                # 去除 invisible api
            if 'org.apache.commons.imaging.palette.ColorCount' in api:
                continue

            if 'protected' in api or 'private' in api:
                continue
            else:
                continue
        filter_nonapi.append(api)

    print('------- after label -------')
    for doc in filter_nonapi:
        print(doc)

    api_str = ''
    if filter_nonapi:
        for api in filter_nonapi:
            if ')' in api:
                api_name = api.split(")")[0] + ')'
                api_des = extract_between_strings(api, ')', ': class')
            else:
                api_name = api.split(': class')[0]
                api_des = ''
            api_name = api_name.replace('\u200b', '')  

            class_info = api.split(': class')[1]
            if api_des and api_des.strip() and not api_des.strip() == ';':
                api_str = api_str + f"- API Name: `{api_name.strip()}: class {class_info.strip()}`, API Description: {api_des.strip()}" + "\n"
            else:
                api_str = api_str + f"- API Name: `{api_name.strip()}: class {class_info.strip()}`" + "\n"


            if use_example:
                if (len(api_name.split(' ')) > 1 and api_name.split(' ')[-1].isupper()) or ('(' not in api_name and ')' not in api_name):
                    continue
                if api_des:
                    api_usage_example = construct_code_example(example_path,f"{api_name.strip()}: class {class_info.strip()}\t{api_des.strip()}")
                else:
                    api_usage_example = construct_code_example(example_path,f"{api_name.strip()}: class {class_info.strip()}")
                if api_usage_example:
                    api_str = api_str + f"Example:\n```java\n{api_usage_example}\n```\n"

    return api_str

def extract_between_strings(input_str, start_str, end_str):
    pattern = re.escape(start_str) + r'(.*?)' + re.escape(end_str)
    match = re.search(pattern, input_str, re.DOTALL)
    if match:
        return match.group(1)
    else:
        return None



def construct_input(task, package_str, signature, apidoc_str):

    if apidoc_str:
        question = f'''{task}
        [Dependency Package]: {package_str}
        [Method Declaration]: {signature}
        [Constraints]: Don't modify the signature provided, and call the API methods in the dependency package to solve it.'''
    # If the API knowledge above you think is not useful, return a complete class-level Java code directly. Otherwise, solve the task using these API knowledge and return a complete class-level Java code with the code block format.'''

        query = ("The possible API usage knowledge related to the following Java programming task is shown below. \n"
                 "[API Usage Knowledge]"
                 "\n"
                 + apidoc_str.strip() +
                 "\n"
                 "Please solve the following task using these API knowledge and return a complete class-level Java code with the code block format.\n"
                 "[Task]: " + question
                 )
    # If the API knowledge provided isn't useful, please return 'No knowledge used.' and
    else:
        print('--------- No Knowledge Provided is Useful ---------')
   
        query = f'''Please solve the following Java programming task and return a complete class-level code with the code block.
        [Task]: {task}
        [Dependency Package]: {package_str}
        [Method Declaration]: {signature}
        [Constraints]: Don't modify the signature provided, and call the API methods in the dependency package to solve it.'''  # existing jar
 

    return query

def diff_error_type(error_msg):
    if check_words_exist('is not applicable for the arguments', error_msg) or check_words_exist('The constructor is undefined', error_msg):
        return Error.PARA_FILL_ERROR
    if check_words_exist('The method is undefined for the type', error_msg):
        return Error.METHOD_CALL_ERROR
    if check_words_exist('cannot be resolved to a type', error_msg) or check_words_exist('The import cannot be resolved', error_msg):
        return Error.IMPORT_ERROR
    if check_words_exist('Type mismatch: cannot convert from to', error_msg):
        return Error.TYPE_MISMATCH_ERROR
    if check_words_exist('Unhandled exception type', error_msg):
        return Error.EXCEPTION_ERROR
    if check_words_exist('Cannot make a static reference to the non-static method', error_msg):
        return Error.NONSTATIC_ERROR
    if check_words_exist('cannot be resolved', error_msg):
        return Error.VARDEF_ERROR

def case_example_path(file_name):
    base_path = 'code_example/'
    if 'chart' in file_name:
        return base_path + 'code_example_chart.jsonl'
    if 'joda' in file_name:
        return base_path + 'code_example_joda.jsonl'
    if 'dom' in file_name:
        return base_path + 'code_example_dom.jsonl'
    if 'imaging' in file_name:
        return base_path + 'code_example_imaging.jsonl'
    if 'pdfbox' in file_name:
        return base_path + 'code_example_pdfbox.jsonl'
    if 'opennlp' in file_name:
        return base_path + 'code_example_opennlp.jsonl'


# llm输出结果判断 5次
def try_task_with_rag():

    gpt4_task = False
    codegen4lib_task = True

    rootPath = ""

    if gpt4_task:
        file_name = 'opennlp'

        example_path = case_example_path(file_name)
        print('example path: ', example_path)

        vec_store_absPath = "my-javatext\\javadoc_gpt4_store.faiss"
        df = pd.read_json(rootPath + f'data_generate/gpt4/{file_name}.jsonl', lines=True)
        result_path = f'rag_gpt4lib_result_model3_5_queryexpand_codeexample/{file_name}/'

    if codegen4lib_task:
        vec_store_absPath = "my-javatext\\javadoc_code4lib_store.faiss"

        codegen4lib_path = 'final_full_chosen/'
        file_name = 'codegen4lib_dom_fulchosen'
        file_name = 'codegen4lib_chart_fulchosen'
        file_name = 'codegen4lib_joda_fulchosen'
        file_name = 'codegen4lib_pdfbox_fulchosen'
        file_name = 'imaging_chosen'
        file_name = 'opennlp_chosen'
        #
        file_name = 'gpt_imaging'
        file_name = 'gpt_opennlp'
        file_name = 'gpt_pdfbox'


        if 'opennlp' in file_name or 'pdfbox' in file_name or 'imaging' in file_name:
            vec_store_absPath = "my-javatext\\javadoc_gpt4_store.faiss"

        example_path = case_example_path(file_name)
        print('example path: ', example_path)
        
        df = pd.read_json(rootPath + f'data_generate/codegen4lib/{codegen4lib_path}{file_name}.jsonl', lines=True)

        result_path = f'rag_final_codegen4lib_result_model3_5/{file_name}/'  
        result_path = f'rag_final_codegen4lib_result_deepseekcoder_temp0/{file_name}/'


    vector_database = load_database(vec_store_absPath)



    try_df = df

    print('dataset length:', len(try_df))
    error_count = 0  # error java code

    error_columns = ['Task', 'error_type', 'snippet', 'empty', 'plain', 'complete']
    df_error = pd.DataFrame(columns=error_columns)
    success_columns = ['Task', 'max_tries', 'snippet', 'empty', 'plain', 'complete']
    df_success = pd.DataFrame(columns=success_columns)

    for cursor in range(0, len(try_df)):  # 根据过滤后的lib进行测试 # 4 # 5

        flag = 0
        # record some message
        plain_count = 0  # only text without java code
        empty_count = 0  # empty method body
        snippet_count = 0  # code snippt
        code_count = 0  # complete code

        description = try_df.iloc[cursor].description
        libs = [try_df.iloc[cursor].package]  # list 类型 #


        print(f"[task{cursor + 1}]")
        # task = f'Please "{description}" in Java.'
        task = description
        package_str = ', '.join(libs)
        signature = try_df.iloc[cursor].method_declaration.strip()

        apidoc_str = search_kg(signature, description, vector_database, 5, example_path)

        question = construct_input(task, package_str, signature, apidoc_str)
        print(question)

        for index in range(1, 6):

            llm_model = "gpt-3.5-turbo-1106"  
            # llm_model = "gpt-4o-2024-08-06"
            # llm_model = "deepseek-coder"
            

            temp = 0.0
            completion = openai.ChatCompletion.create(
                model=llm_model,  
                messages=[
                    {"role": "user", "content": question}
                ],
                temperature=temp   # 0.0001  # 0.01?
            )  # temperature=0.0

            openai_res = completion['choices'][0]['message']['content']
            # print(openai_res)

            ori_res = openai_res  # modify this

            i = cursor + 1
            save_path = result_path + f"select_code4lib_dataset/task{i}/{index}/"
            file_path, signal = parse_generate_code(ori_res, save_path)

            # 处理singal
            if signal == Signal.SNIPPET_CODE:
                snippet_count += 1
            if signal == Signal.EMPTY_BODY:
                empty_count += 1
            if signal == Signal.PLAIN_TEXT:
                plain_count += 1
            if signal == Signal.NORMAL_CODE:
                code_count += 1

            if not file_path:
                continue
            # error = check_compileAPI(file_path)
            check_res = check_withEclipse(file_path)
            error = ignore_warnings(check_res)
            if not error:
                flag = 1
                print(f'Try {index} times, generate the successful program.')  
                df_success = pd.concat([df_success,
                                        pd.DataFrame([[i, index, snippet_count, empty_count, plain_count, code_count]],
                                                     columns=success_columns)], ignore_index=True)
                break

            # save error information
            with open(result_path + "compile_error.log", "a") as f:  
                # content = '\n'.join(error)  # for check_compileAPI
                content = error  # for check_withEclipse
                content = content.replace("\n", "")
                f.write(f"[task{i}]\n{content}\n")

        if not file_path:
            df_error = pd.concat([df_error,
                                  pd.DataFrame([[i, 'None', snippet_count, empty_count, plain_count, code_count]],
                                               columns=error_columns)], ignore_index=True)

        error_generate_path = result_path + f"error_code4lib_5/task{i}/"
        if flag == 0 and file_path:
            error_count += 1
            if not os.path.exists(error_generate_path):
                shutil.copytree(result_path + f"select_code4lib_dataset/task{i}/",
                                error_generate_path)  

            error_type_list = []
            for per in error.strip().split('\r\n\r\n')[:-1]:
                error_code = per.strip().split('\r\n')[1].strip()
                error_msg = per.strip().split('\r\n')[-1].strip()
                error_type = diff_error_type(error_msg)
                if error_type:
                    error_type_list.append(error_type.name)
                else:
                    error_type_list.append('UnrResolved')

            error_types = '\n'.join(error_type_list)
            df_error = pd.concat([df_error,
                                  pd.DataFrame([[i, error_types, snippet_count, empty_count, plain_count, code_count]],
                                               columns=error_columns)], ignore_index=True)

    print('Total dataset size: {}, LLM cannot process nums: {}'.format(len(try_df), error_count))
    df_error.to_excel(result_path + 'error.xlsx', index=False)
    df_success.to_excel(result_path + 'success.xlsx', index=False)


if __name__ == '__main__':
    print("Testing with the API knowledge with RAG idea ...")
    try_task_with_rag()


