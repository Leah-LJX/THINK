from enum import Enum

class Error(Enum):
    IMPORT_ERROR = 1
    PARA_FILL_ERROR = 2
    METHOD_CALL_ERROR = 3
    TYPE_MISMATCH_ERROR = 4
    EXCEPTION_ERROR = 5
    NONSTATIC_ERROR = 6
    VARDEF_ERROR = 7
