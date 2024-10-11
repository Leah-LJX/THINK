import re
import os

def extract_code_from_output(text):
    gen_code = re.findall(r"```java(.*?)```", text, re.DOTALL)[0].strip()
    return gen_code

def save_code(path, content):
    if not os.path.exists(os.path.dirname(path)):
        try:
            os.makedirs(os.path.dirname(path))
        except OSError as e:
            print("Creating path failed.", e)

    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)