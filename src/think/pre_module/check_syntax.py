import os

import jpype
import re
from extract_code import save_code

def init_jvm():
    if jpype.isJVMStarted():
        return
    rootPath = "D:\\BaiduSyncdisk\\LangChain\\GPT-Test\\lib\\"
    jpype.startJVM(classpath=[rootPath + "../lib/check-syntax.jar"])

def stop_jvm():
    jpype.shutdownJVM()

def check_compileAPI(generate_code_path):
    init_jvm()
    SyntaxChecker = jpype.JClass("findErrors.SyntaxChecker")
    check_object = SyntaxChecker()

    check_res = check_object.getMessage(generate_code_path)  # java.util.ArrayList 类型

    py_check_res = [str(item) for item in check_res]

    return py_check_res

def listdir(path, list_name):
    for file in os.listdir(path):
        file_path = os.path.join(path, file)
        file_path = file_path.replace('\\\\', '/')
        if os.path.isdir(file_path):
            listdir(file_path, list_name)
        else:
            if file_path.endswith('jar') and 'source' not in file_path and 'javadoc' not in file_path:
                list_name.append(file_path)

def load_classpath(lib_path):
    all_jar = []
    if lib_path == 'lib/':
        jar_list = os.listdir(lib_path)
        for jar in jar_list:
            jar_path = os.path.join(lib_path, jar)
            all_jar.append(jar_path)
    else:
        listdir(lib_path, all_jar)
    return ";".join(all_jar)

def check_withEclipse(generate_code_path):
    init_jvm()
    EclipseCompiler = jpype.JClass("findErrors.EclipseCompiler")
    check_object = EclipseCompiler()

    # classpath = ".;" + load_classpath("lib/") # for codelib4gen
    classpath = ".;" + load_classpath("D:/ProgramFiles/apache-maven-3.6.2/m2/repository/")
    root_lib = "D:/BaiduSyncdisk/workplace/eclipse_workspace/collect/lib"
    classpath = classpath + f";{root_lib}/junit-4.12.jar;{root_lib}/org.w3c.dom.svg-1.1.0.v201011041433.jar"
    # print(classpath)
    check_res = check_object.compileWithEclipse(generate_code_path, classpath)
    return str(check_res)

def is_body_empty(body):
    # 移除所有注释
    body = re.sub(r'//.*?$|/\*.*?\*/', '', body, flags=re.DOTALL | re.MULTILINE)
    # 移除所有空白字符
    body = re.sub(r'\s+', '', body)
    return len(body) == 0

def is_empty_body(code):
    # 正则表达式来匹配类和方法体
    class_pattern = re.compile(r'\bclass\b\s+\w+\s*{([^}]*)}', re.DOTALL)
    method_pattern = re.compile(r'\b(?:public|private|protected|static|final|native|synchronized|abstract|transient)\b[^;]*?\b\w+\s*\([^)]*\)\s*{([^{}]*)}')

    # 查找所有的类体
    classes = class_pattern.findall(code)
    # print(classes)
    for i, class_body in enumerate(classes, 1):
        if is_body_empty(class_body):
            return True
        else:
            # 查找所有的方法体
            methods = method_pattern.findall(code)
            for i, method_body in enumerate(methods, 1):
                if is_body_empty(method_body):
                    return True
                else:
                    return False

from enum import Enum

class Signal(Enum):
    EMPTY_BODY = 1
    PLAIN_TEXT = 2
    NORMAL_CODE = 3
    SNIPPET_CODE = 4


def parse_generate_code(response, save_path):

    if not os.path.exists(save_path):
        os.makedirs(save_path)

    # llm response parse and save to a file
    if len(re.findall(r"```java(.*?)```", response, re.DOTALL)) > 0:
        action_input = re.findall(r"```java(.*?)```", response, re.DOTALL)[0].strip()
        action_input = action_input.replace("private", "public")
        # 判断是否是空的方法体
        if is_empty_body(action_input):
            return (None, Signal.EMPTY_BODY)  # 生成的是空方法体， 返回None
    else:
        action_input = response
        class_pattern = r'\bpublic\s+class\s+\w+\s*\{'
        if re.search(class_pattern, action_input):
            action_input = response
            print(f"The response is not with code block.")
        else:
            print(f"The response is not a Java solution. Response:\n{action_input}")
            return (None, Signal.PLAIN_TEXT)   # 纯文本无代码生成, 返回None

    # print('-------action input', action_input)
    # get class_name as file_name
    if len(re.findall(r"public class (.*?)\{", action_input, re.DOTALL)) > 0:
        file_name = re.findall(r"class (.*?)\{", action_input, re.DOTALL)[0].strip()  # + ".java"
        # print('-----filename', file_name)

        if " " in file_name:   # process SynchronizedHashMap<K, V>.java
            if "<" in file_name and ">" in file_name and "extends" not in file_name:
                file_name = file_name.split("<")[0].strip() + ".java"
            elif "<" in file_name and ">" in file_name and "extends" in file_name:
                if file_name.find("extend") < file_name.find("<"):
                    file_name = file_name.split("extends")[0].strip() + ".java"
                else:
                    file_name = file_name.split("<")[0].strip() + ".java"
            elif "extends" in file_name and "implements" not in file_name:
                file_name = file_name.split("extends")[0].strip() + ".java"
            elif "implements" in file_name and "extends" not in file_name:
                file_name = file_name.split("implements")[0].strip() + ".java"
            elif "extends" in file_name and "implements" in file_name:
                if file_name.find("extend") < file_name.find("implements"):
                    file_name = file_name.split("extends")[0].strip() + ".java"
                else:
                    file_name = file_name.split("implements")[0].strip() + ".java"
            else:
                print(f"Cannot parse file_name: {file_name}, save this task to TempNotParse.java")
                file_name = "TempNotParse.java"
                save_code(save_path + file_name, action_input)
                return (save_path + file_name, Signal.NORMAL_CODE)
        else:
            file_name = file_name + ".java"
    else: # 是代码碎片的情况, 直接保存到Temp.java 中
        file_name = "TempSnippet.java"
        save_code(save_path + file_name, f"public class {file_name}{{\n {action_input} \n}}")
        print(f"No class file_name: {file_name}")
        return (None, Signal.SNIPPET_CODE)   # 代码碎片返回None

    # 特殊的报错处理
    if 'class' in file_name:
        file_name = file_name.split('class')[1].strip()

    file_path = save_path + file_name
    save_code(save_path + file_name, action_input)  # "generate_code/"
    return (file_path, Signal.NORMAL_CODE)

def ignore_warnings(check_res):
    res = ""
    for info in check_res.split("----------"):
        if "WARNING" in info or ("warning" in info and "error" not in info) or info == "":  # "warnings" in check_res
            continue
        res = res + info
    return res

if __name__ == '__main__':

    generate_code_path = "D:\\BaiduSyncdisk\\LangChain\\GPT-Test\\generate_code\\Matrix.java"
    # generate_code_path = "generate_code/math4/1/SVDecompositionCommons.java"
    # check_res = check_compileAPI(generate_code_path)
    check_res = check_withEclipse(generate_code_path)
    without_warning_res = ignore_warnings(check_res)
    if not without_warning_res:
        print("ljx no compile errors")
    else:
        print(without_warning_res)
    stop_jvm()
