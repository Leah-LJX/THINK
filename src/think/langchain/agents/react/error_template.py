import re
import os
import openai
from fuzzywuzzy import fuzz
from check_syntax import check_withEclipse, ignore_warnings
from extract_code import save_code
from utils import list_class, check_words_exist, load_3_5_model, model_3_5_test, chat
from remove_unuse import remove_unused_imports
from Error import Error
from base import MethodCallReActChain, ParafillReActChain
import logging
from langchain.vectorstores import FAISS
from langchain.embeddings import OpenAIEmbeddings


# 配置日志记录器
logging.basicConfig(filename='log/error_deepseek_0908.log', level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')  # error_repair_v3-2.log

# gpt-4o
openai.api_base = "your-api-base"
openai.api_key = "your-api-key"
os.environ["OPENAI_API_KEY"] = "your-api-key"
os.environ['OPENAI_API_BASE'] = "your-api-base"


rootPath = "data/"
rt_extract_dir = rootPath + 'rt_extract/'
jcommons_extract_dir = rootPath + 'jcommon_extract/'


# jar = 'org.apache.commons.imaging'
# jar = 'opennlp.tools'  # opennlp.tools
# jar = 'org.apache.pdfbox'   # task2 repeat generation
# vector_name = "gpt4lib"
# javadoc_path = rootPath + f'mydoc-gpt4-txt/'  # {jar}/

package_list = ['org.apache.commons.imaging', 'opennlp.tools', 'org.apache.pdfbox', 'org.jfree', 'org.joda.time', 'org.w3c.dom']

jar = 'org.jfree'
# jar = 'org.joda.time'
# jar = 'org.w3c.dom'
vector_name = "codegen4lib"
javadoc_path = rootPath + f'mydoc-code4lib-txt/' 
def load_database(database_path):
    embeddings = OpenAIEmbeddings(model="text-embedding-ada-002", openai_api_base="your-api-base",
                openai_api_key="your-api-key")
    vector_store = FAISS.load_local(database_path, embeddings=embeddings)

    return vector_store

def extract_classes_from_jar(extract_dir):
    """
    extract the class qualified name 
    """

    class_map = {}
    for root, dirs, files in os.walk(extract_dir):
        for file in files:
            if file.endswith('.class'):
                full_path = os.path.join(root, file)
                # 移除基础路径和扩展名，替换路径分隔符为点
                class_name = os.path.relpath(full_path, start=extract_dir).replace('.class', '').replace('\\', '.').replace('/', '.')
                simple_name = class_name.split('.')[-1]
                class_map[simple_name] = class_name
    return class_map



def find_pascal_case_errors(s, error_message):
    pattern = re.compile(r'\b([A-Z][a-zA-Z0-9_]*)\b\s+' + re.escape(error_message))
    matches = pattern.findall(s)
    return matches

def is_camel_case(s):
    pattern = re.compile(r'^[a-z]+(?:[A-Z][a-z]*)* cannot be resolved.*$')
    return bool(pattern.match(s))


def find_max_prefix_match(base_str, str_list):
    max_match_length = 0
    best_match = None

    for s in str_list:
        match_length = 0
        for i in range(min(len(base_str), len(s))):
            if base_str[i] == s[i]:
                match_length += 1
            else:
                break

        if match_length > max_match_length:
            max_match_length = match_length
            best_match = s

    return best_match

def import_error_resolve_template(error_msg, program):

    pattern1 = r'The import (.*?) cannot be resolved'
    pattern2 = r'(.*?) cannot be resolved'  


    match1 = re.search(pattern1, error_msg)
    match2 = re.search(pattern2, error_msg)


    if match1 or len(match2.group(1).split(".")) > 2: 
        logging.debug('====== Import Uncorrectly ======')
        if not match1 and len(match2.group(1).split(".")) > 2:
            error_import = match2.group(1)
        else:
            error_import = match1.group(1)
        print(f"===== Import Uncorrectly, error import ===== {error_import}")


        error_import_package = ".".join(error_import.split(".")[:-1])
        package = find_max_prefix_match(error_import_package, package_list)
        candidate_class = []
        if package:
            if 'jfree' in package or 'dom' in package or 'joda' in package:
                candidate_class = list_class(rootPath + f'mydoc-code4lib-txt/' + package + '/', [])
            if 'imaging' in package or 'opennlp' in package or 'pdfbox' in package:
                candidate_class = list_class(rootPath + f'mydoc-gpt4-txt/' + package + '/', [])

        similarities = []
        left_candidates = []
        if candidate_class:
            for candidate_import in candidate_class:

                if len(candidate_import.split(javadoc_path + package + '/')) > 0 and os.path.exists(javadoc_path + package):
                    candidate_import = candidate_import.split(javadoc_path + package + '/')[1].replace('/', '.')[:-4]
                    if error_import.split('.')[-1] in candidate_import.split('.')[-1]:
  
                        similarity = fuzz.ratio(error_import, candidate_import)
                        similarities.append((candidate_import, similarity))
                    else:
                        left_candidates.append(candidate_import)


        if similarities:
            most_similar = max(similarities, key=lambda x: x[1])
            correct_import = most_similar[0]
            print(f"===== correct_import: {correct_import}")

            corrected_program = re.sub(error_import, correct_import, program)
            print('----- correct program:\n', corrected_program)
            return corrected_program
        else:
            if "org.jfree" in jar:
                class_map = extract_classes_from_jar(jcommons_extract_dir)
                pattern = re.compile(r'\b[A-Z]\w*\b')  
                matches = pattern.findall(error_import)
                correct_import = [class_map.get(match) for match in matches if match in class_map]
                if correct_import:
                    corrected_program = re.sub(error_import, correct_import[0], program)
                    print(f'----- jcommon the correct_import {correct_import}')
                    return corrected_program
            if rt_extract_dir:
                class_map = extract_classes_from_jar(rt_extract_dir)
                pattern = re.compile(r'\b[A-Z]\w*\b')  
                matches = pattern.findall(error_import)
                correct_import = [class_map.get(match) for match in matches if match in class_map]
                if correct_import:
                    corrected_program = re.sub(error_import, correct_import[0], program)
                    print(f'----- rt the correct_import: {correct_import}')
                    return corrected_program
            else:
                print(f"There is not the class {error_import} in the javadoc, try the similar class...")
                for candidate_import in left_candidates:
                    similarity = fuzz.ratio(error_import.split('.')[-1], candidate_import.split('.')[-1]) 
                    if similarity > 70:
                        similarities.append((candidate_import, similarity))
                if similarities:
                    most_similar = max(similarities, key=lambda x: x[1])
                    correct_import = most_similar[0]
                    print(f"===== similar_import: {correct_import}")
                    program = re.sub(error_import, correct_import, program)
                    corrected_program = re.sub(error_import.split('.')[-1], correct_import.split('.')[-1], program)
                    print('----- similar correct program:\n', corrected_program)
                else:
                    print(f"There is no the class {error_import} and no similar class in the javadoc")

    if match2:
        logging.debug('====== Lack Import ======')
        print('====== Lack Import ======')
        lack_import = match2.group(1)
        add_import = ''

        if lack_import.isupper():
            error_field = lack_import
            if vector_name == "gpt4lib":
                vec_store_absPath = "my-javatext\\javadoc_gpt4_store.faiss"
            if vector_name == "codegen4lib":
                vec_store_absPath = "my-javatext\\javadoc_code4lib_store.faiss"
            vector_store = load_database(vec_store_absPath)
            result = vector_store.similarity_search_with_score(error_field, k=1)
            result = sorted(result, key=lambda x: x[1], reverse=True)
            source = result[0][0].metadata['source'].split('\\')[-1][:-4]
            # 可能缺少该field的所属类
            lack_import = source.split(".")[0]


            correct_field = source + "." + error_field
            print(f'---- correct field: {correct_field}')
            pattern = re.compile(r'\b([A-Z][a-zA-Z0-9_]*)\b' + re.escape("." + error_field))
            matches = pattern.findall(program)
            print(f'--------- matches: {matches}')
            if matches:
                program = re.sub(matches[0]+'.'+error_field, correct_field, program)
                print('----- field modify correct program:\n', program)

        print('------', javadoc_path)
        candidate_class = list_class(javadoc_path + jar, [])  
        for candidate_import in candidate_class:
            if lack_import == candidate_import.split(javadoc_path + jar + '/')[1].replace('/', '.')[:-4].split('.')[-1]:
                add_import = candidate_import.split(javadoc_path + jar + '/')[1].replace('/', '.')[:-4]
                print(f'----- add the {add_import}')
                break

        if not add_import:
            class_map = extract_classes_from_jar(rt_extract_dir)
            pattern = re.compile(r'\b[A-Z]\w*\b')  
            matches = pattern.findall(lack_import)
            add_import = [class_map.get(match) for match in matches if match in class_map]
            if add_import:
                add_import = add_import[0]
                print(f'----- add the {add_import}')

        if add_import and f'import {add_import};' not in program:
            if program.startswith('package'):
                program = program.split('\n')[0] + f'\nimport {add_import};\n' + '\n'.join(program.split('\n')[1:])
            else:
                program = f'import {add_import};\n' + program

        return program

    return None

def duplicate_error_msg(error_list):
    value_to_keys = {}

    for d in error_list:
        key = d['error_code']
        value = d['error_msg']
        if value in value_to_keys:
            value_to_keys[value].append(key)
        else:
            value_to_keys[value] = [key]

    result_list = []
    for value, keys in value_to_keys.items():
        result_list.append({'error_code': ', '.join(keys), 'error_msg': value})

    return result_list

def methodcall_error_resolve_template(chatModel, methodcall_error_list, program, output_parser_path, file_path):
    chain = MethodCallReActChain(chatModel,  vector_name=vector_name, max_iterations=10, verbose=True, handle_parsing_errors=True,
                                 early_stopping_method="generate")
    if len(methodcall_error_list) == 1: 
        question = f"In the following java code\n ```\n{program}\n```, "
        for error in methodcall_error_list:
            question = question + f"the line `{error['error_code']}` reports the error `{error['error_msg']}`, "
        question = question + "which means the method is called incorrectly.\nHow to solve these errors? Please use the `Thought, Action, Observation` step by step to get related knowledge and return a complete class-level code."
        # print(question)

        # 替换{}, 使得langchain能够识别code
        question = question.replace('{', '{{').replace('}', '}}')
        chain.run(question)

    else:
        methodcall_error_list = duplicate_error_msg(methodcall_error_list)
        print("methodcall_error_list", methodcall_error_list, len(methodcall_error_list))
        for error in methodcall_error_list:
            question = f"In the following java code\n ```\n{program}\n```, "
            question = question + f"the line `{error['error_code']}` reports the error `{error['error_msg']}`, "
            question = question + "which means the method is called incorrectly.\nHow to solve these errors? Please use the `Thought, Action, Observation` step by step to get related knowledge and return a complete class-level code."
            # print(question)

            
            question = question.replace('{', '{{').replace('}', '}}')
            chain.run(question)
 

            save_path = output_parser_path + os.path.basename(file_path) 
            if os.path.exists(save_path):
                logging.info(f'After processing methodcall error, save to {save_path}')
                with open(save_path, 'r', encoding='utf-8') as f:
                    program = f.read()
            question = ''

def parafill_error_resolve_template(chatModel, parafill_error_list, program):
    chain = ParafillReActChain(chatModel, vector_name=vector_name, max_iterations=10, verbose=True, handle_parsing_errors=True,
                         early_stopping_method="generate")
    if len(parafill_error_list) == 1:
        question = f"In the following java code\n ```\n{program}\n```, "
        for error in parafill_error_list:
            question = question + f"the line `{error['error_code']}` reports the error `{error['error_msg']}`, " # according the error message    # adjust calling code to match.
        question = question + "which means the parameters are incorrectly populated.\nPlease check method signatures, verify parameter types and counts, adjust calling code or call new method to match. Use the `Thought, Action, Observation` step by step to get related knowledge and return a complete class-level code without omitting any part of the original input code."
        question = question.replace('{', '{{').replace('}', '}}')
        chain.run(question)
    else:
        for error in parafill_error_list:
            question = f"In the following java code\n ```\n{program}\n```, "
            question = question + f"the line `{error['error_code']}` reports the error `{error['error_msg']}`, "
            question = question + "which means the parameters are incorrectly populated.\nPlease check method signatures, verify parameter types and counts, adjust calling code or call new method to match. Use the `Thought, Action, Observation` step by step to get related knowledge and return a complete class-level code without omitting any part of the original input code."
            if 'setFont' in error['error_code']:
                correct_example = 'contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);'
                question = question + f'\nCorrect Usage Example:\n {correct_example}'

            question = question.replace('{', '{{').replace('}', '}}')
            chain.run(question)

            save_path = output_parser_path + os.path.basename(file_path)  
            if os.path.exists(save_path):
                logging.info(f'After processing parafill error, save to {save_path}')
                with open(save_path, 'r', encoding='utf-8') as f:
                    program = f.read() 

def llm_resolve_template(chatModel, error_list, program):
    question = f"In the following java code\n ```\n{program}\n```, "
    for error in error_list:
        question += f"the line `{error['error_code']}` reports the error `{error['error_msg']}`, "

    if "Type mismatch" in question:
        question = question[:-1] + ". Try if you can add cast to the code, or changes the type of variable, or optimize the code, or other methods, "

    question += 'please think step by step to solve these errors. Give me a complete class-level code.'

    response = chat(chatModel, question)
    # parse to java code from response
    if len(re.findall(r"```java(.*?)```", response, re.DOTALL)) > 0:
        generate_code = re.findall(r"```java(.*?)```", response, re.DOTALL)[0].strip()
        return generate_code
    else:
        print('No new program generated.')


def exception_error_resolve_template(exception_error_list, program):
    for error in exception_error_list:
        error_msg = error['error_msg']
        unhandle_type = error_msg.split(' ')[-1]
        if 'throws' not in program:
            start_index = program.find(')')
            end_index = program.find('{', program.find('{') + 1)  
            program = f'{program[:start_index+1]} throws {unhandle_type} {program[end_index:]}'
        else:
            index = program.find('{', program.find('{') + 1) 
            program = f'{program[:index-1]}, {unhandle_type} {program[index:]}'

    return program


def diff_error_type(error_msg):

    if check_words_exist('is not applicable for the arguments', error_msg) or check_words_exist('The constructor is undefined', error_msg):
        return Error.PARA_FILL_ERROR

    if (check_words_exist('The method is undefined for the type', error_msg) or check_words_exist("The method from the type is not visible", error_msg) or check_words_exist("Cannot instantiate the type", error_msg)
            or check_words_exist("The constructor is not visible", error_msg) or check_words_exist("The type is not visible", error_msg)):
        return Error.METHOD_CALL_ERROR


                                                                     # 匹配 class_x cannot be resolved
    if (('.' in error_msg and check_words_exist("cannot be resolved to a type", error_msg)) or find_pascal_case_errors(error_msg, "cannot be resolved") or
            check_words_exist('The import cannot be resolved', error_msg)) or check_words_exist('cannot be resolved or is not a field', error_msg):  # 匹配字段error
        return Error.IMPORT_ERROR
    if check_words_exist('Type mismatch: cannot convert from to', error_msg):
        return Error.TYPE_MISMATCH_ERROR
    if check_words_exist('Unhandled exception type', error_msg):
        return Error.EXCEPTION_ERROR
    if check_words_exist('Cannot make a static reference to the non-static method', error_msg):
        return Error.NONSTATIC_ERROR
    if is_camel_case(error_msg):
        return Error.VARDEF_ERROR

def get_error_by_type(errors):
    parafill_error_list = []
    methodcall_error_list = []
    exception_error_list = []
    nonstatic_error_list = []
    vardef_error_list = []
    mismatch_list = []

    for per in errors.strip().split('\r\n\r\n')[:-1]:
        error_code = per.strip().split('\r\n')[1].strip()
        error_msg = per.strip().split('\r\n')[-1].strip()
        error_type = diff_error_type(error_msg)

        if error_type == Error.PARA_FILL_ERROR:
            parafill_error_list.append({'error_code': error_code, 'error_msg': error_msg})

        if error_type == Error.METHOD_CALL_ERROR:
            methodcall_error_list.append({'error_code': error_code, 'error_msg': error_msg})

        if error_type == Error.EXCEPTION_ERROR:
            exception_error_list.append({'error_code': error_code, 'error_msg': error_msg})

        if error_type == Error.NONSTATIC_ERROR:
            nonstatic_error_list.append({'error_code': error_code, 'error_msg': error_msg})

        if error_type == Error.VARDEF_ERROR:
            vardef_error_list.append({'error_code': error_code, 'error_msg': error_msg})

        if error_type == Error.TYPE_MISMATCH_ERROR:
            mismatch_list.append({'error_code': error_code, 'error_msg': error_msg})

    return parafill_error_list, methodcall_error_list, exception_error_list, nonstatic_error_list, vardef_error_list, mismatch_list

def update_errors(temp_save_path):
    temp_check_res = check_withEclipse(temp_save_path)
    temp_errors = ignore_warnings(temp_check_res)
    error_list = []
    if temp_errors:
        parafill_error_list, methodcall_error_list, exception_error_list, nonstatic_error_list, vardef_error_list, type_mismatch_list = get_error_by_type(temp_errors)
        error_list = [parafill_error_list, methodcall_error_list, exception_error_list, nonstatic_error_list, vardef_error_list, type_mismatch_list]
        for idx, error in enumerate(error_list, 1):
            if error:
                print("---- error message:{}".format(error))
    return temp_errors, error_list



def process_errors(chatModel, file_path, output_parser_path, program, errors):

    save_path = file_path # default
    parafill_error_list, methodcall_error_list, exception_error_list, nonstatic_error_list, vardef_error_list, type_mismatch_list = get_error_by_type(errors)
    print("---- parafill error:{}\n---- method call error: {}\n"
          "---- exception: {}\n---- non-static error: {}\n---- vardef error: {}\n---- type_mismatch error: {}".format(parafill_error_list, methodcall_error_list, exception_error_list, nonstatic_error_list, vardef_error_list, type_mismatch_list))
    type_list = [parafill_error_list, methodcall_error_list, exception_error_list, nonstatic_error_list, vardef_error_list, type_mismatch_list]
    type_list_map = {'parafill_error_list': 0,
                     'methodcall_error_list': 1,
                     'exception_error_list': 2,
                     'nonstatic_error_list': 3,
                     'vardef_error_list': 4,
                     'type_mismatch_list': 5
                     }


    if type_list and type_list[1]: 
        logging.debug("Processing method call errors ...")
        result = methodcall_error_resolve_template(chatModel, type_list[type_list_map['methodcall_error_list']], program, output_parser_path, file_path)
        logging.debug(result)

        save_path = output_parser_path + os.path.basename(file_path) 
        if os.path.exists(save_path):
            logging.info(f'After processing methodcall error, save to {save_path}')
            with open(save_path, 'r', encoding='utf-8') as f:
                program = f.read()    


        errors, type_list = update_errors(save_path)


    if type_list and type_list[0]: # parafill_error_list
        logging.debug("Processing parameter fill errors ...")
        print("Processing parameter fill errors ...")
        parafill_error_resolve_template(chatModel, type_list[type_list_map['parafill_error_list']], program)
        # code here; 保存处理之后的代码
        save_path = output_parser_path + os.path.basename(file_path)  
        if os.path.exists(save_path):
            logging.info(f'After processing parafill error, save to {save_path}')
            with open(save_path, 'r', encoding='utf-8') as f:
                program = f.read()



        errors, type_list = update_errors(save_path)

    if type_list and type_list[5]:      # type_mismatch_list
        logging.debug("Processing type mismatch errors ...")
        print("Processing type mismatch errors ...")
        program = llm_resolve_template(chatModel, type_list[type_list_map['type_mismatch_list']], program)
        logging.debug(program)
        print('------ type mismatch process result:\n', program)
        if program:
            save_path = 'results/type_mismatch_resolve/' + os.path.basename(file_path)
            logging.info(f'After processing type mismatch error, save to {save_path}')
            save_code(save_path, program)


        errors, type_list = update_errors(save_path)


    if type_list and type_list[3]: # nonstatic_error_list
        logging.debug("Processing non-static errors ...")
        program = llm_resolve_template(chatModel, type_list[type_list_map['nonstatic_error_list']], program)
        logging.debug(program)
        print('------ non-static process result:\n', program)
        if program:
            save_path = 'results/nonstatic_resolve/' + os.path.basename(file_path)
            logging.info(f'After processing non-static error, save to {save_path}')
            save_code(save_path, program)

  
        errors, type_list = update_errors(save_path)

    if type_list and type_list[4]: # vardef_error_list
        logging.debug("Processing variable undefined errors ...")
        # to do here
        program = llm_resolve_template(chatModel, type_list[type_list_map['vardef_error_list']], program)
        logging.debug(program)
        print('------ variable undefined process result:\n', program)
        if program:
            save_path = 'results/vardef_resolve/' + os.path.basename(file_path)
            logging.info(f'After processing variable undefined error, save to {save_path}')
            save_code(save_path, program)

     
        errors, type_list = update_errors(save_path)

    if type_list and type_list[2]:  # exception_error_list
        logging.debug("Processing exception handle errors ...")
        print("Processing exception handle errors ...")
        program = llm_resolve_template(chatModel, type_list[type_list_map['exception_error_list']], program)

        logging.debug(program)
        print('------ exception process result:\n', program)
        if program:
            save_path = 'results/exception_resolve/' + os.path.basename(file_path)
            logging.info(f'After processing exception error, save to {save_path}')
            save_code(save_path, program)

        errors, type_list = update_errors(save_path)
        if find_pascal_case_errors(errors, "cannot be resolved") or check_words_exist('The import cannot be resolved',
                                                                                      errors):
            print('----exception lack errors: ', errors)
            save_path, program = process_import_error(save_path, program, errors)
            errors, type_list = update_errors(save_path)

    if errors:  
        logging.debug("there are some other type errors need to be resolved. Unresolved Errors: {}\n".format(errors))
        print("there are some other type errors need to be resolved. Unresolved Errors: {}\n".format(errors))

        error_info = ""
        for per in errors.strip().split('\r\n\r\n')[:-1]:
            error_code = per.strip().split('\r\n')[1].strip()
            error_msg = per.strip().split('\r\n')[-1].strip()
            
            error_info += f"the line `{error_code}` reports the error `{error_msg}`, "
        if error_info:
            question = f"In the following java code\n ```\n{program}\n```, {error_info}please think step by step to solve these errors and return a complete class-level code, without omitting any part of the original input code."
            response = chat(chatModel, question)

            # parse to java code from response
            if len(re.findall(r"```java(.*?)```", response, re.DOTALL)) > 0:
                generate_code = re.findall(r"```java(.*?)```", response, re.DOTALL)[0].strip()
                program = generate_code

                print(f"After other processing, the program:{program}")
                if program:
                    save_path = 'results/other_resolve/' + os.path.basename(file_path)
                    logging.info(f'After processing other error, save to {save_path}')
                    save_code(save_path, program)
                
                errors, type_list = update_errors(save_path)

              
                if type_list and type_list[2]:  # exception_error_list
                    logging.debug("Other Resolve: Processing exception handle errors ...")
                    print("Processing exception handle errors ...")
                    program = llm_resolve_template(chatModel, type_list[type_list_map['exception_error_list']], program)

                    logging.debug(program)
                    print('------ exception process result:\n', program)
                    if program:
                        save_path = 'results/exception_resolve/' + os.path.basename(file_path)
                        logging.info(f'After processing exception error, save to {save_path}')
                        save_code(save_path, program)

            
                    errors, type_list = update_errors(save_path)

    program = remove_unused_imports(program)

    if find_pascal_case_errors(errors, "cannot be resolved") or check_words_exist('The import cannot be resolved', errors):
        print('----- last import process program.... ------\n', {program})
        print('----errors: ', errors)

        save_path, program = process_import_error(save_path, program, errors)

    save_code('results/final/' + os.path.basename(file_path), program)

    return save_path

def process_import_error(file_path, program, errors):
 
    ori_program = program
    program = remove_unused_imports(program)
    if not ori_program == program:
  
        save_path = 'results/import_unuse/' + os.path.basename(file_path)
        logging.info(f'After processing import unused, save to {save_path}')
        save_code(save_path, program)
    
        check_res = check_withEclipse(save_path)
        errors = ignore_warnings(check_res)

    logging.info(f'Processing import error ...')

    import_error_dict = {}
    pattern = re.compile(r'\b([A-Z][a-zA-Z0-9_]*)\b\s+' + re.escape("cannot be resolved"))
    for per in errors.strip().split('\r\n\r\n')[:-1]:
        
        error_msg = per.strip().split('\r\n')[-1].strip()
    
        error_type = diff_error_type(error_msg)
        if error_type == Error.IMPORT_ERROR:

            if re.findall(pattern, error_msg):
                error_class = re.findall(pattern, error_msg)[0]
                import_error_dict.setdefault(error_class, error_msg)
            else:
    
                if 'import' in error_msg:
                    error_code = per.strip().split('\r\n')[1].strip()
                    error_class = error_code[6:-1]  # 取'import ;'之间的code
                    import_error_dict.setdefault(error_class, error_msg)
                else:  
                    print('------方法调用错误信息--包调用：', error_msg)
                    program = re.sub(error_msg.split(" ")[0]+'.', '', program)

    
    import_error_list = list(import_error_dict.values())

    
    print('----- import error: {}'.format(import_error_list))

   
    for import_error in import_error_list:
        program = import_error_resolve_template(import_error, program)


    save_path = 'results/import_resolve/' + os.path.basename(file_path)
    logging.info(f'After processing import error, save to {save_path}')
    print(f'After processing import error, save to {save_path}')
    save_code(save_path, program)

    return save_path, program

from collections import defaultdict
def group_files_by_third_last_directory(files, index):
    
    grouped_files = defaultdict(list)

    for file in files:
        parts = os.path.normpath(file).split(os.sep)
        if len(parts) >= -index:
            third_last_dir = parts[index]
            grouped_files[third_last_dir].append(file)

        sorted_grouped_files = dict(sorted(grouped_files.items(), key=lambda item: natural_keys(item[0])))

    return sorted_grouped_files

def atoi(text):
    return int(text) if text.isdigit() else text

def natural_keys(text):
    
    return [atoi(c) for c in re.split(r'(\d+)', text)]



if __name__ == '__main__':

    error_file_list = list_class(rootPath + f'../data/benchmark/GPT_4o_rag_res/{jar}', [])

    index = -3
    print(len(error_file_list))

    # llm_model = 'gpt-3.5-turbo-1106'
    llm_model = "gpt-4o-2024-08-06"
    chatModel = load_3_5_model(llm_model)


    score = 0
    score_list = []
    output_parser_path = "results/extraction_code/"

    grouped_files = group_files_by_third_last_directory(error_file_list, index)
    print(grouped_files)

    for index, (third_last_dir, files) in enumerate(grouped_files.items()):
        for file_path in files:
            if '.class' in file_path:
                continue

            logging.info(f'Processing the file {file_path}')
            print(f'Processing the file {file_path}')
            with open(file_path, 'r', encoding='utf-8') as f:
                program = f.read()

            check_res = check_withEclipse(file_path)
            errors = ignore_warnings(check_res)

            if errors:
                init_error_num_info = errors.strip().splitlines()[-1]
                logging.info(f'====== Inital error info: {init_error_num_info} =====')

                print(f'------ init errors: {errors}') # repr
                if find_pascal_case_errors(errors, "cannot be resolved") or check_words_exist('The import cannot be resolved', errors):
   
                    save_path, program = process_import_error(file_path, program, errors)

                    check_res = check_withEclipse(save_path)
       
                    errors = ignore_warnings(check_res)
            
                    if errors:
                        logging.info(f"===== Errors after import resolved ===== \n{errors.strip()}")
                        after_import_error_num_info = errors.strip().splitlines()[-1]
                        logging.info(f'====== after import process error info: {after_import_error_num_info} =====')
                        print(f'process errors method from path: {save_path}')
                        save_path = process_errors(chatModel, save_path, output_parser_path, program, errors)
                        print(f'----- check the program from {save_path} -----')
                     
                        check_res = check_withEclipse(save_path)
                        errors = ignore_warnings(check_res)
                        if errors:
                            after_process_error_num_info = errors.strip().splitlines()[-1]
                            logging.info(f'1.After processing, the left error info: {after_process_error_num_info}')
                            logging.info(f"===== Errors after methodcall, parafill, exception resolved ===== \n{errors}")
                            print("--------- Init error info: {}, afer import error info: {}, after process error info: {}".format(init_error_num_info, after_import_error_num_info, after_process_error_num_info))
                        else:
                            score += 1
                            score_list.append(third_last_dir)
                            logging.info('===== 1.Finally, there are no errors after processing. =====')
                            print('===== 1.Finally, there are no errors after processing. =====')
                            break
                    else:
                        score += 1
                        score_list.append(third_last_dir)
                        logging.info('===== There are no errors after "Import Error Resolve". =====')
                        print('===== There are no errors after "Import Error Resolve". =====')
                        break
                else:
                    save_path = file_path  # default

                    logging.info(f"===== Errors ===== \n{errors}")
                    
                    save_path = process_errors(chatModel, file_path, output_parser_path, program, errors)

             
                    check_res = check_withEclipse(save_path)
                    errors = ignore_warnings(check_res)
                    if errors:
                        after_process_error_num_info = errors.strip().splitlines()[-1]
                        logging.info(f'2.After direct processing, the left error info: {after_process_error_num_info}')
                        print("--------- Init error info: {}, after process error info: {}".format(init_error_num_info, after_process_error_num_info))
                    else:
                        score += 1
                        score_list.append(third_last_dir)
                        logging.info('===== 2.Finally, there are no errors after processing =====')
                        print('===== 2.Finally, there are no errors after processing =====')
                        break
            else:
                logging.info('===== There are no errors. =====')
                print('===== There are no errors. =====')
                break

    print(f"Processing score nums: {score}")
    print(f"Processing tasks: {score_list}")
