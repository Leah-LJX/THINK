from datasets import load_dataset
import openai
import os
import shutil
from check_syntax import parse_generate_code
from check_syntax import check_withEclipse, ignore_warnings, Signal
import pandas as pd
from react.utils import check_words_exist
from react.Error import Error

# 环境配置
openai.api_base = "your-api-base"
openai.api_key = "your-api-key"


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


def try_task():

    # 加载数据集
    # 读取 jsonl 文件
    file_name_list = ['chart', 'dom', 'joda', 'pdfbox', 'imaging', 'opennlp', 'pdfbox', 'gen_imaging', 'gen_opennlp', 'gen_pdfbox']
    file_name = file_name_list[0]
    df = pd.read_json('../benchmark/'+f'{file_name}.jsonl', lines=True)

    result_path = 'GPT_3_5_result_temp0/' + file_name + '/'
    print('dataset length:', len(df))
    error_count = 0  # error java code

    error_columns = ['Task', 'error_type', 'snippet', 'empty', 'plain', 'complete']
    df_error = pd.DataFrame(columns=error_columns)
    success_columns = ['Task', 'max_tries', 'snippet', 'empty', 'plain', 'complete']
    df_success = pd.DataFrame(columns=success_columns)

    for cursor in range(0, len(df)): 
        flag = 0
        # record some message
        plain_count = 0  # only text without java code
        empty_count = 0  # empty method body
        snippet_count = 0  # code snippt
        code_count = 0  # complete code


        description = df.iloc[cursor].description
        libs = [df.iloc[cursor].package]  

        print(f"[task{cursor+1}]")
        task = f'Please "{description}" in Java.'
        package_str = ', '.join(libs)
        signature = df.iloc[cursor].method_declaration.strip()

        task = description 
        question = f'''Please solve the following Java programming task and return a complete class-level code with the code block.
    [Task]: {task}
    [Dependency Package]: {package_str}
    [Method Declaration]: {signature}
    [Constraints]: Don't modify the signature provided, and call the API methods in the dependency package to solve it.'''  # existing jar
        print(question)


        for index in range(1, 6):
            # try 5 times
            llm_model = "gpt-3.5-turbo-1106"  # 1106
            # llm_model = "gpt-4o-2024-08-06"
            # llm_model = "deepseek-coder"
            completion = openai.ChatCompletion.create(
                model=llm_model,
                messages=[
                    {"role": "user", "content": question}
                ],
                temperature=0.0
            )
            openai_res = completion['choices'][0]['message']['content']

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
                print(f'Try {index} times, generate the successful program.')  # 如果编译成功则执行下一个任务;否则重复编译，保存当前轮次的错误
                df_success = pd.concat([df_success, pd.DataFrame([[i, index, snippet_count, empty_count, plain_count, code_count]], columns=success_columns)], ignore_index=True)
                break

            # save error information
            with open(result_path + "compile_error.log", "a") as f:  # "compile_log/compile_error.log"
                # content = '\n'.join(error)  # for check_compileAPIanyesh
                content = error  # for check_withEclipse
                content = content.replace("\n", "")
                f.write(f"[task{i}]\n{content}\n")

        if not file_path:
            df_error = pd.concat([df_error, pd.DataFrame([[i, 'None', snippet_count, empty_count, plain_count, code_count]], columns=error_columns)], ignore_index=True)

        error_generate_path = result_path + f"error_code4lib_5/task{i}/"
        if flag == 0 and file_path:
            error_count += 1
            if not os.path.exists(error_generate_path):
                shutil.copytree(result_path + f"select_code4lib_dataset/task{i}/", error_generate_path)  # 直接创建文件夹并复制，不需额外创建

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

    print('Total dataset size: {}, LLM cannot process nums: {}'.format(len(df), error_count))
    df_error.to_excel(result_path+'error.xlsx', index=False)
    df_success.to_excel(result_path+'success.xlsx', index=False)

if __name__ == '__main__':
    try_task()