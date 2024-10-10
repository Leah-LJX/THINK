# THINK

**Tackling the API Hallucination via Injecting Knowledge**

This project addresses API hallucination by leveraging a structured approach to knowledge injection. The following sections guide you through the installation process and provide an overview of the project's directory structure.

## Installation

### Environment

Ensure the following requirements are met before proceeding with the installation:
- **Python** 3.10+
- **JDK** 1.8+

### Steps

1. Clone the repository:
```
git clone https://github.com/your-username/your-repo-name.git
```
2. Navigate to the **THINK** directory:
```
cd src/think
```
3. Install the required dependencies:
```
pip install -r requirements.txt
```
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

### `lib`

The `lib` directory contains:

- **Syntax Verification Scripts**: Compilation scripts to validate code syntax and ensure correctness.
- **Jar Files**: This includes both the third-party libraries (DOM, Chart, Imaging, Joda, PDFBox, OpenNLP) and any other relevant supporting libraries.

These resources ensure the required dependencies are available for code validation and compilation during experimentation.

### `src`

The `src` directory contains the core source code for the project, broken down as follows:

- **`process_libs.py`**: This script is responsible for generating baseline results from the experiments.
- **`check_syntax.py`**: A verification script used to validate experimental results for syntactic correctness.
- **`construct/`**: A directory dedicated to building and managing the API knowledge base. It includes:
    - **API knowledge Data**: Extracted API knowledge based on Java documentation.
    - **Code Extraction Scripts**: Scripts to extract API-relevant knowledge from the raw Java documentation.
    - **Vector Embedding Scripts**: Code to embed API knowledge into a vectorized knowledge base.
    - **Vector Knowledge Base Files**: Finalized vector files forming the API knowledge base.
- **`think/`**: This directory contains code and resources for the two-phase strategy discussed in the research:
    - `pre_module/`: Scripts and resources aimed at implementing the pre-execution phase.
      #### Files
    - **`rag.py`**
      - **Description**: The main script for executing the retrieval strategy.
      - **Functionality**: 
        - Manages **task decomposition**, breaking down complex tasks into simpler, manageable sub-tasks.
        - Constructs **code examples** to support the retrieval process, helping to ensure the relevance and context of the retrieved data.
        - Acts as the central file for orchestrating the retrieval strategy, which is essential for collecting accurate and contextually appropriate information.
    
    - **`label_rag.py`**
      - **Description**: A script dedicated to filtering the results retrieved by `rag.py`.
      - **Functionality**: 
        - Applies **filtering operations** to the retrieval outputs, aiming to enhance the precision of the results.
        - Ensures that the retrieval strategy returns results that are **accurate** and **relevant** to the task requirements, reducing noise and improving data quality for subsequent processes.


    - `post_module/`: Scripts and resources aimed at implementing the post-execution phase.
       #### Files
        - **`error_template.py`**
          - **Description**: The main file responsible for executing the error correction workflow.
          - **Functionality**:
            - Implements a comprehensive error correction process that includes:
              - **Inference and Behavior-Based Corrections**: Adapts and corrects errors based on inferred behavior and program logic.
              - **Rule-Based Corrections**: Applies predefined rules to handle common or predictable errors systematically.
              - **LLM-Based Corrections**: Utilizes large language models (LLMs) to suggest and apply corrections based on contextual understanding and semantic analysis.
        
        - **`base.py`**
          - **Description**: Manages the detailed logic for inference and behavior-based strategies.
          - **Functionality**:
            - Defines core methods and strategies for error correction based on behavior patterns.
            - Serves as the foundation for the error correction process by leveraging inference techniques.
            - Integrates with **`methodcall_prompt.py`** and **`parafill_prompt.py`**, which provide templated prompt ideas to guide the inference and behavior-based corrections:
              - **`methodcall_prompt.py`**: Supplies prompts focused on method calls and their behavior, assisting the error correction by contextualizing function usage.
              - **`parafill_prompt.py`**: Offers template prompts for parameter filling, aiding in the accurate correction of parameter-related issues.
 
