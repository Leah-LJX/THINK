# THINK

**Tackling the API Hallucination via Injecting Knowledge**

This project addresses API hallucination by leveraging knowledge injection with two phases: pre-execution ehancement and post-execution optimization. The following sections guide you through the installation process and provide an overview of the project's directory structure.

## Overview
![overview_v3_00](https://github.com/user-attachments/assets/7fdfeefc-fef6-4e04-9d64-a0b91e746c17)

## Installation

### Environment

Ensure the following requirements are met before proceeding with the installation:
- **Python** 3.10+
- **JDK** 1.8+

### Steps
1. Install the required dependencies:
```
pip install -r requirements.txt
```
2. Run
- For baseline method:
```
python process_libs.py
```
- For the pre_execution component:
```
cd src/think/pre_module/
python rag.py
```
- For the post_execution component (this may need rely on the framework **langchain**):
```
cd src/think/post_module/
python error_template.py
```
Or try:
```
cd think/langchain/agents/react/
python error_template.py
```
**Note**: Ensure you configure the OpenAI API key within the code where needed and adjust file paths according to your local setup. Additionally, download the required jar to `lib` for verify the correctness of program generated.
Due to file size limitations, the runnable compilation validator could not be uploaded. We've included the compilation scripts in the `lib` directory—please package them into an executable JAR file if needed.



## Project Directory Structure

The project is organized into the following main directories, each serving a specific purpose essential to the setup, execution, and verification processes.

### `benchmark`

The `benchmark` directory contains datasets utilized for experimental purposes, covering diverse domains:

- **DOM**: For Document Object Model processing.
- **Chart**: For data visualization and chart handling.
- **Imaging**: For image manipulation and processing.
- **Joda**: For date and time handling.
- **PDFBox**: For handling PDF document operations.
- **OpenNLP**: For natural language processing tasks.

Each benchmark category focuses on a distinct domain, including document processing, data visualization, image processing, PDF handling, date and time management, and NLP, providing a wide-ranging suite for testing and validation.

The `benchmark` directory stores data in JSONL format, with each line representing a single record structured as `<task description, signature, package>`.

### `lib`

The `lib` directory contains:

- Syntax Verification Scripts: Compilation scripts to validate code syntax and ensure correctness.
- Jar Files: This put both the third-party libraries (DOM, Chart, Imaging, Joda, PDFBox, OpenNLP) and any other relevant supporting libraries.

These resources ensure the required dependencies are available for code validation and compilation during experimentation.

### `src`

The `src` directory contains the core source code for the project, broken down as follows:

- `process_libs.py`: This script is responsible for generating baseline results from the experiments.
- `check_syntax.py`: Verification script used to validate experimental results for syntactic correctness.
- `construct/`: Directory dedicated to building and managing the API knowledge base. It includes:
    - `extract_to_txt.py`: Scripts to extract API-relevant knowledge from the raw Java documentation.
    - `embedding_javadoc.py`: Code to embed API knowledge into a vectorized knowledge base.
    - `javatext/`: Extracted API knowledge based on Java documentation.
    - `vector_store/`: Finalized vector files forming the API knowledge base.
      
- `think/`: This directory contains code and resources for the two-phase strategy discussed in the paper:
    - `pre_module/`: Scripts and resources aimed at implementing the pre-execution phase.
        - `rag.py`: The main script for executing the retrieval strategy. Manages **task decomposition**, breaking down complex tasks into simpler sub-tasks. Constructs code examples to support the generation of retrieval enhancement.
        - `label_rag.py`: A script dedicated to filtering the results retrieved. Applies **labeling** to the retrieval outputs, aiming to enhance the precision of the results.
    - `post_module/`: Scripts and resources aimed at implementing the post-execution phase.
        - `error_template.py`: The main file responsible for executing the error correction workflow. Implements a comprehensive error correction process that includes:
            - **Reasoning and Acting Corrections**: Adapts and corrects errors based on inferred cause of these errors.
            - **Rule-Based Corrections**: Applies predefined rules to handle common or predictable errors systematically.
            - **LLM-Based Corrections**: Utilizes large language models to correct simpler errors without API knowledge.        
        - `base.py`: Manages the detailed logic for the react-based strategy based on Thought-Action-Observation model. Integrates with `methodcall_prompt.py` and `parafill_prompt.py`, which provide templated prompt ideas to guide the reasoning and acting corrections:
            - `methodcall_prompt.py`: Supplies template prompts focused on method calls.
            - `parafill_prompt.py`: Offers template prompts for parameter filling.

 ### Contributors
