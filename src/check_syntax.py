import os

import jpype
import re
from react_test.extract_code import save_code


from enum import Enum

class Signal(Enum):
    EMPTY_BODY = 1
    PLAIN_TEXT = 2
    NORMAL_CODE = 3
    SNIPPET_CODE = 4

def init_jvm():
    if jpype.isJVMStarted():
        return
    jpype.startJVM(classpath=["lib/check-syntax.jar"])

def stop_jvm():
    jpype.shutdownJVM()

def check_compileAPI(generate_code_path):
    init_jvm()
    SyntaxChecker = jpype.JClass("findErrors.SyntaxChecker")
    check_object = SyntaxChecker()

    check_res = check_object.getMessage(generate_code_path)

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

    classpath = ".;" + load_classpath("/apache-maven-3.6.2/m2/repository/")
    root_lib = "lib"
    classpath = classpath + f";{root_lib}/junit-4.12.jar;{root_lib}/org.w3c.dom.svg-1.1.0.v201011041433.jar"
    check_res = check_object.compileWithEclipse(generate_code_path, classpath)
    return str(check_res)

def is_body_empty(body):
    body = re.sub(r'//.*?$|/\*.*?\*/', '', body, flags=re.DOTALL | re.MULTILINE)
    body = re.sub(r'\s+', '', body)
    return len(body) == 0

def is_empty_body(code):
    class_pattern = re.compile(r'\bclass\b\s+\w+\s*{([^}]*)}', re.DOTALL)
    method_pattern = re.compile(r'\b(?:public|private|protected|static|final|native|synchronized|abstract|transient)\b[^;]*?\b\w+\s*\([^)]*\)\s*{([^{}]*)}')

    classes = class_pattern.findall(code)
    for i, class_body in enumerate(classes, 1):
        if is_body_empty(class_body):
            return True
        else:
            methods = method_pattern.findall(code)
            for i, method_body in enumerate(methods, 1):
                if is_body_empty(method_body):
                    return True
                else:
                    return False


def parse_generate_code(response, save_path):

    if not os.path.exists(save_path):
        os.makedirs(save_path)


    if len(re.findall(r"```java(.*?)```", response, re.DOTALL)) > 0:
        action_input = re.findall(r"```java(.*?)```", response, re.DOTALL)[0].strip()
        action_input = action_input.replace("private", "public")
        if is_empty_body(action_input):
            return (None, Signal.EMPTY_BODY) 
    else:
        action_input = response
        class_pattern = r'\bpublic\s+class\s+\w+\s*\{'
        if re.search(class_pattern, action_input):
            action_input = response
            print(f"The response is not with code block.")
        else:
            print(f"The response is not a Java solution. Response:\n{action_input}")
            return (None, Signal.PLAIN_TEXT)  


    if len(re.findall(r"public class (.*?)\{", action_input, re.DOTALL)) > 0:
        file_name = re.findall(r"class (.*?)\{", action_input, re.DOTALL)[0].strip() # + ".java"


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
    else:
        file_name = "TempSnippet.java"
        save_code(save_path + file_name, action_input)
        print(f"No class file_name: {file_name}")
        return (None, Signal.SNIPPET_CODE)

    if 'class' in file_name:
        file_name = file_name.split('class')[1].strip()

    file_path = save_path + file_name
    # print(file_path)
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

    generate_code_path = "test/Matrix.java"
    check_res = check_withEclipse(generate_code_path)
    without_warning_res = ignore_warnings(check_res)
    if not without_warning_res:
        print("no compile errors")
    else:
        print(without_warning_res)
    stop_jvm()
