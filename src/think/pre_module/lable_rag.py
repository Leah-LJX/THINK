import openai
import os
import re
from extract_code import extract_code_from_output, save_api_example, get_api_code_example
from check_syntax import check_withEclipse, ignore_warnings, parse_generate_code

openai.api_base = "" 
openai.api_key = ""
os.environ['OPENAI_API_KEY'] = ""
os.environ['OPENAI_API_BASE'] = ""



def expand_query(description):
    question = (
        f"Given the following task description, break down the programming task into step-by-step solutions, focusing on the brief steps that need to call API methods of the specific package only, without any extra explanation."
        "Example Task Description: Extract all hyperlinks from a PDF file and save them to a CSV file.\n"
        "Example Steps:\n"
        "1. Load the PDF from the file path.\n"
        "2. Extract text from each page.\n"
        "3. Find all hyperlinks in the text.\n"
        "4. Save the extracted hyperlinks to a CSV file."
        f"\n\nTask Description: {description}\n"
        "Steps:\n")

    llm_model = "gpt-3.5-turbo-1106"
    completion = openai.ChatCompletion.create(
        model=llm_model,  
        messages=[
            {"role": "user", "content": question}
        ],
    )  
    response = completion['choices'][0]['message']['content']
    print(f'--------- response:\n{response}')

    steps = re.split(r'\d+\.\s*', response)
    steps = [step.strip() for step in steps if step.strip()]

    return steps, response


def construct_code_example(path, api_description):
    api_usage_code = get_api_code_example(path, api_description)
    if api_usage_code:
        print(f'------------ api info : {api_description}')
        print(f'------------ api usage example search --------------\n{api_usage_code}')
        return api_usage_code
    else:
        llm_model = "gpt-3.5-turbo-1106"
        question = ("Given the following Java API description, please give me a API usage example. Ensure that the import information is complete and does not miss any class import statements. Initial variables must be present and cannot be placeholders. The variables that need to be initialized can be passed as parameters or defined as `null`. Return a complete class-level code with the code block format ```java ```, without any other explanations.\n"
                    f"{api_description}")
        completion = openai.ChatCompletion.create(
            model=llm_model,  # default 0613   # gpt-3.5-turbo-1106
            messages=[
                {"role": "user", "content": question}
            ],
            
        ) 
        response = completion['choices'][0]['message']['content']

        api_usage_code = extract_code_from_output(response)
        if api_usage_code:
            print(f'------------ api info : {api_description}')
            print(f'------------ api usage example construct --------------\n{api_usage_code}')

            check = True   # True
            if check:
                # 判断对错
                print('-------- Checking API Code Example --------')
                temp_example_path = 'code_example/temp_code/'
                file_path, signal = parse_generate_code(response, temp_example_path)
                check_res = check_withEclipse(file_path)
                error = ignore_warnings(check_res)
                if not error:
                    save_api_example(path, {'api_description': api_description, 'api_example': api_usage_code})
                    return api_usage_code
            else:
                return api_usage_code
        else:
            return None


def label_chunk(chunk, description):
    question = f"Label the following chunk as 'useful', 'ambiguous', or 'useless' based on its relevance to the description. Only output the label without any explanation: '{description}'.\n\nChunk: {chunk}"
    llm_model = "gpt-4-1106-preview"
    completion = openai.ChatCompletion.create(
        model=llm_model, 
        messages=[
            {"role": "user", "content": question}
        ],
    )  
    response = completion['choices'][0]['message']['content']

    return response.split()[0]


def process_chunks(chunks, description):
    result = []
    chunk_note = []

    for doc in chunks:  # simi_search_res
        chunk = doc.page_content
        if chunk in chunk_note:
            continue
        chunk_note.append(chunk)

        print('------- initial search with expand -------')
        print(chunk)

        if not ('(' in chunk and ')' in chunk):
            if 'enum' in chunk or 'Enum' in chunk or (len(chunk.split(' ')) > 1 and chunk.split(' ')[-1].isupper()):
                pass
            if 'Direct Known Subclasses' in chunk or 'All Known Implementing Classes' in chunk:
                pass
            else:
                continue

        source = doc.metadata['source'].split('\\')[-1][:-4]
        main_label = label_chunk(chunk, description)
   
        if main_label in ["useful", "ambiguous"]:   
            sub_chunks = chunk.split("\n\n")
            for sub_chunk in sub_chunks:
                sub_label = label_chunk(sub_chunk, description)
            
                if sub_label in ["useful", "ambiguous"]:   
                    result.append(sub_chunk+": class "+source)

    return result

