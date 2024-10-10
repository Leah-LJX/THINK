import re
import os
import json

def extract_code_from_output(text):
    if text and len(re.findall(r"```java(.*?)```", text, re.DOTALL)) > 0:
        gen_code = re.findall(r"```java(.*?)```", text, re.DOTALL)[0].strip()
        return gen_code

def save_code(path, content):
    if not os.path.exists(os.path.dirname(path)):
        try:
            os.makedirs(os.path.dirname(path))
        except OSError as e:
            print("Error:", e)

    try:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
    except OSError as e:
        print("Error:", e)

def save_api_example(path, content):
    if not os.path.exists(os.path.dirname(path)):
        try:
            os.makedirs(os.path.dirname(path))
        except OSError as e:
            print("创建路径失败:", e)

    with open(path, 'a', encoding='utf-8') as f:
        # f.write(content)
        # print(json.dumps(content) + "\n\n")
        f.write(json.dumps(content) + "\n")


def get_api_code_example(json_file_path, api_des, api_code_example=None):
    """Retrieve the API code example if it exists, otherwise save and return the new example."""
    if os.path.exists(json_file_path):
        with open(json_file_path, 'r', encoding='utf-8') as file:
            for line in file:
                if not line.strip():
                    continue
                item = json.loads(line)
                if item['api_description'].strip() == api_des.strip():
                    try:
                        temp = item['api_example']
                    except Exception as e:
                        print('========= item des:', item)
                    return item['api_example']
    else:
        return ''

    # else:
    #     if api_code_example:
    #         api_data[api_des] = api_code_example
    #         save_api_example(json_file, api_data)
    #         return api_code_example
    #     else:
    #         return None

path = '../llmgen_code_example/code_example.jsonl'
api_des = "- API Name: `static PDDocument Loader.loadPDF(File file): class Loader`, API Description: Parses a PDF."
res = get_api_code_example(path, api_des)
print(res)