# THINK

**Tackling the API Hallucination via Injecting Knowledge**

This project addresses API hallucination by leveraging a structured approach to knowledge injection. The following sections guide you through the installation process and provide an overview of the project's directory structure.

## A Installation

### A.1 Environment

Ensure the following requirements are met before proceeding with the installation:
- **Python** 3.10+
- **JDK** 1.8+

### A.2 Steps

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
## B Project Directory Structure

The project is organized into the following main directories, each serving a specific purpose essential to the setup, execution, and verification processes.

### B.1 Directory Overview

#### B.1.1 `benchmark`

The `benchmark` directory contains datasets utilized for experimental purposes, covering diverse domains:

- **DOM**: For Document Object Model processing.
- **Chart**: For data visualization and chart handling.
- **Imaging**: For image manipulation and processing.
- **Joda**: For date and time handling.
- **PDFBox**: For handling PDF document operations.
- **OpenNLP**: For natural language processing tasks.

Each benchmark category focuses on a distinct domain, including document processing, data visualization, image processing, PDF handling, date and time management, and NLP, providing a wide-ranging suite for testing and validation.

#### B.1.2 `lib`

The `lib` directory contains:

- **Syntax Verification Scripts**: Compilation scripts to validate code syntax and ensure correctness.
- **Jar Files**: This includes both the third-party libraries (DOM, Chart, Imaging, Joda, PDFBox, OpenNLP) and any other relevant supporting libraries.

These resources ensure the required dependencies are available for code validation and compilation during experimentation.

#### B.1.3 `src`

The `src` directory contains the core source code for the project, broken down as follows:

- **`process_libs.py`**: This script is responsible for generating baseline results from the experiments.
- **`check_syntax.py`**: A verification script used to validate experimental results for syntactic correctness.
- **`construct/`**: A directory dedicated to building and managing the API knowledge base. It includes:
    - **API knowledge Data**: Extracted API knowledge based on Java documentation.
    - **Code Extraction Scripts**: Scripts to extract API-relevant knowledge from the raw Java documentation.
    - **Vector Embedding Scripts**: Code to embed API knowledge into a vectorized knowledge base.
    - **Vector Knowledge Base Files**: Finalized vector files forming the API knowledge base.
- **`think/`**: This directory contains code and resources for the two-phase strategy discussed in the research:
    - `pre_module`: Scripts and resources aimed at implementing the pre-execution phase.
    - `post_module`: Scripts and resources aimed at implementing the post-execution phase.
