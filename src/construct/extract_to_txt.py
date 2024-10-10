import requests
from bs4 import BeautifulSoup
import re
import os

def listdir(path, list_name):
    for file in os.listdir(path):
        file_path = os.path.join(path, file)
        file_path = file_path.replace('\\', '/')
        if os.path.isdir(file_path):
            if 'class-use' in file_path:
                continue
            listdir(file_path, list_name)
        else:
            list_name.append(file_path)

def clean_html_text(text):
    """Replaces HTML entities with regular spaces and removes extra whitespace."""
    return ' '.join(text.replace(u'\xa0', u' ').split())

def replace_nextline(text):
    text = re.sub(r'(?<!\n)\n(?!\n)', ' ', text)  # 将单个 \n 替换为空格
    text = re.sub(r'\n{2,}', '\n', text)  # 将多个连续的 \n 替换为一个 \n：
    return text

def extract_section_pdf(soup, section_name):
    """Extracts and formats section data if available."""
    header = soup.find('a', attrs={'name': f"{section_name.lower()}.summary"})
    if header:
        table = header.find_next('table', class_='memberSummary')
        if table:
            extracted_data = []
            for row in table.find_all('tr')[1:]:  # Skip header
                if row.find('td', class_='colFirst'): # 如果type存在
                    modifier_type = clean_html_text(row.find('td', class_='colFirst').get_text()).strip()
                    method_details = row.find('td', class_='colLast')
                else:
                    modifier_type = ''
                    method_details = row.find('td', class_='colOne')
                method_name = clean_html_text(method_details.find('code').get_text()).strip()
                description = clean_html_text(method_details.text[len(method_details.find('code').get_text()):]).strip()  # Remove method signature from description
                if modifier_type:
                    extracted_data.append(f"{modifier_type} {method_name}\t{description}")
                else:
                    extracted_data.append(f"{method_name}\t{description}")
            return extracted_data
    return None

def extract_section_imaging(soup, section_name):
    """Extracts and formats section data if available."""
    header = soup.find('a', attrs={'id': f"{section_name.lower()}.summary"})
    if header:
        table = header.find_next('table', class_='memberSummary')
        if table:
            extracted_data = []
            for row in table.find_all('tr')[1:]:  # Skip header
                if row.find('td', class_='colFirst'): # 如果type存在
                    modifier_type = clean_html_text(row.find('td', class_='colFirst').get_text()).strip()
                    if row.find('th', class_='colSecond'):
                        method_name = clean_html_text(row.find('th', class_='colSecond').find('code').get_text()).strip()
                    elif row.find('th', class_='colConstructorName'):
                        method_name = clean_html_text(row.find('th', class_='colConstructorName').find('code').get_text()).strip()
                    description = clean_html_text(row.find('td', class_='colLast').get_text()).strip()
                else:
                    modifier_type = ''
                    #  constructor method
                    method_name = clean_html_text(row.find('th').get_text()).strip()   # row.find('th', class_='colConstructorName')
                    description = clean_html_text(row.find('td', class_='colLast').get_text()).strip()
                if modifier_type:
                    extracted_data.append(f"{modifier_type} {method_name}\t{description}")
                else:
                    extracted_data.append(f"{method_name}\t{description}")
            return extracted_data
    return None

def extract_section_nlp(soup, section_name):
    """Extracts specified sections if available, handling the varied structure of method and constructor summaries."""
    section = soup.find('section', id=section_name)
    if not section:
        return None
    results = []
    if 'method-summary' in section_name or 'field-summary' in section_name:
        # Processing method summary with potential three columns
        rows = section.select('.summary-table > div')[3:]  # Skip header row
        for i in range(0, len(rows), 3):
            if i+2 < len(rows):
                modifier_and_type = clean_html_text(rows[i].text)
                method_name = clean_html_text(rows[i+1].find('code').get_text())
                description = clean_html_text(rows[i+2].text)
                results.append(f"{modifier_and_type} {method_name}\t{description}")
    elif 'constructor-summary' in section_name:
        # Processing constructor summary which may have two columns
        entries = section.select('.summary-table > div')[2:]  # Skip header row
        for i in range(0, len(entries), 2):
            if i+1 < len(entries):
                constructor_signature = clean_html_text(entries[i].find('code').get_text())
                description = clean_html_text(entries[i+1].text)
                results.append(f"{constructor_signature}\t{description}")
    elif 'nested-class-summary' in section_name:
        nested_info = section.find('h2').get_text()
        nested_code = section.find('code').get_text()
        results.append(f"{nested_info}\n{nested_code}")

    return results

def extract_class_info(jar_name, html_content):
    soup = BeautifulSoup(html_content, 'html.parser')

    # Extract class name, type, and package
    if soup.find('h2', class_='title'):
        title_element = soup.find('h2', class_='title')
        title_text = title_element.text.split()
    elif soup.find('h1', class_='title'):               # opennlp
        title_element = soup.find('h1', class_='title')
        title_text = title_element.text.split()

    if soup.find('div', class_='sub-title'):            # opennlp
        api_name = soup.find('div', class_='sub-title').a.text.strip() + '.' + \
                   soup.find('h1', class_='title').text.split()[1]
        type_info = soup.find('h1', class_='title').text.split()[0]
    elif soup.find('div', class_='subTitle'):
        subtitle = soup.find('div', class_='subTitle').text.strip()
        api_name = f"{subtitle}.{title_text[1]}"
        type_info = title_text[0]  # 'Class', 'Interface', 'Exception', etc.

    # Info about extensions and implementations
    if soup.find('div', class_='description'):
        inheritance_info = soup.find('div', class_='description')
    elif soup.find('section', class_='class-description'):
        inheritance_info = soup.find('section', class_='class-description')
    print('inheritance_info', repr(inheritance_info.get_text()))
    info_text = replace_nextline(inheritance_info.get_text().strip())

    # info_text = "\n".join(
    #     [replace_nextline(li.text.strip()) for li in inheritance_info.find_all('li')]) if inheritance_info else "None"

    # Constructing output
    output = [
        f"[API Name] {api_name}",
        f"[Type] {type_info}",
        "[Info]",
        f"{info_text}"
    ]

    if jar_name == 'org.apache.pdfbox.io' or jar_name == 'org.apache.pdfbox':
        # Check and append different summaries if available
        constructor_summary = extract_section_pdf(soup, 'Constructor')
        if constructor_summary:
            output.append("[Constructor Summary]")
            output.extend(constructor_summary)

        field_summary = extract_section_pdf(soup, 'Field')
        if field_summary:
            output.append("[Field Summary]")
            output.extend(field_summary)

        nested_class_summary = extract_section_pdf(soup, 'Nested.Class')
        if nested_class_summary:
            output.append("[Nested Class Summary]")
            output.extend(nested_class_summary)

        method_summary = extract_section_pdf(soup, 'Method')
        if method_summary:
            output.append("[Method Summary]")
            output.extend(method_summary)

        # Methods inherited from java.lang.Object
        # 使用正则表达式查找包含特定字段的属性
        regex = re.compile(r'.*methods.inherited.from.*')
        inherited_methods_section_all = soup.findAll('a', attrs={'name': regex})
        for inherited_methods_section in inherited_methods_section_all:
            if inherited_methods_section:
                inherit_from = clean_html_text(inherited_methods_section.parent.find('h3').get_text())
                inherit_methods = clean_html_text(inherited_methods_section.parent.find('code').get_text())
                output.append(f"[{inherit_from}]")
                output.extend([inherit_methods])

    if jar_name == 'org.apache.commons.imaging':
        enum_constants_summary = extract_section_imaging(soup, 'Enum.Constant')
        if enum_constants_summary:
            output.append("[Enum Constant Summary]")
            output.extend(enum_constants_summary)

        # Check and append different summaries if available
        constructor_summary = extract_section_imaging(soup, 'Constructor')
        if constructor_summary:
            output.append("[Constructor Summary]")
            output.extend(constructor_summary)

        field_summary = extract_section_imaging(soup, 'Field')
        if field_summary:
            output.append("[Field Summary]")
            output.extend(field_summary)

        nested_class_summary = extract_section_imaging(soup, 'Nested Class')
        if nested_class_summary:
            output.append("[Nested Class Summary]")
            output.extend(nested_class_summary)

        method_summary = extract_section_imaging(soup, 'Method')
        if method_summary:
            output.append("[Method Summary]")
            output.extend(method_summary)

        # Methods inherited from java.lang.Object
        # 使用正则表达式查找包含特定字段的属性
        regex = re.compile(r'.*methods.inherited.from.*')
        inherited_methods_section_all = soup.findAll('a', attrs={'id': regex})
        for inherited_methods_section in inherited_methods_section_all:
            if inherited_methods_section:
                inherit_from = clean_html_text(inherited_methods_section.parent.find('h3').get_text())
                inherit_methods = clean_html_text(inherited_methods_section.parent.find('code').get_text())
                output.append(f"[{inherit_from}]")
                output.extend([inherit_methods])

    elif jar_name == 'opennlp.tools':
        # Dynamic section extraction
        sections = {
            "Constructor Summary": "constructor-summary",
            "Field Summary": "field-summary",
            "Nested Class Summary": "nested-class-summary",
            "Method Summary": "method-summary"
        }
        for title, id in sections.items():
            section_data = extract_section_nlp(soup, id)
            if section_data:
                output.append(f"[{title}]")
                output.extend(section_data)

        inherited_methods_section_all = soup.findAll('div', class_='inherited-list')
        for inherited_methods_section in inherited_methods_section_all:
            if inherited_methods_section:
                if not inherited_methods_section.find('h3'):  # h2 for nested-class inherited-list
                    continue
                inherit_from = clean_html_text(inherited_methods_section.find('h3').get_text())
                inherit_methods = clean_html_text(inherited_methods_section.find('code').get_text())
                output.append(f"[{inherit_from}]")
                output.extend([inherit_methods])


    return "\n".join(output)


if __name__ == '__main__':

    file_list = []
    save_file_list = []
    # jar_name = 'org.jfree.chart'
    jar_name = 'org.jfree.data'
    jar_name = 'org.joda.time'
    jar_name = 'org.w3c.dom'
    jar_name = 'org.w3c.dom.svg'
    jar_name = 'org.jfree.chart'

    jar_name = 'org.apache.pdfbox.io'
    jar_name = 'org.apache.pdfbox'
    # jar_name = 'opennlp.tools'
    #
    # jar_name = 'org.apache.commons.imaging'

    content_path = f'{jar_name}/'   # Note: math2 different from math3 and com.xxx.xxx
    save_path = f'mydoc-gpt4-txt/{jar_name}/'    # 'data/javadoc/org.apache.commons.math3.linear/'

    listdir(content_path, file_list)
    for file in file_list:
        file_name = file.split('/')[1:] # [3:]
        file_name = '/'.join(file_name)
        save_file_name = os.path.join(save_path, file_name[:-5]+'.txt')   # .html to .txt
        # save_file_name = save_file_name.replace('\\', '/')
        save_file_list.append(save_file_name)


    for i in range(len(file_list)):  # len(file_list)
        print(file_list[i])
        if 'package' in file_list[i] or 'class-use' in file_list[i]:
            continue
        # Example usage:
        # To read from a local HTML file:
        with open(file_list[i], "r", encoding="utf-8") as file:
            html_content = file.read()
        info_text = extract_class_info(jar_name, html_content)

        print(save_file_list[i])
        directory = '/'.join(save_file_list[i].split('/')[:-1]) + '/'
        print(f'directory: {directory}')
        print(f"if path exist: {os.path.exists(directory)}")

        if not os.path.exists(directory):
            os.makedirs(directory)
            with open(save_file_list[i], 'w', encoding='utf-8') as f:
                f.write(info_text)
        else:
            with open(save_file_list[i], 'w', encoding='utf-8') as f:
                # print(f'res_content: {repr(res_content)}')
                # print(info_text)
                f.write(info_text)
    print('-----------------')
