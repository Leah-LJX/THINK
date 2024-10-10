# flake8: noqa
from langchain.prompts.prompt import PromptTemplate

EXAMPLES = [
    """Question: In the following java code 
    ```
    import org.joda.time.LocalTime;
    import org.joda.time.format.DateTimeFormat;
    import org.joda.time.format.DateTimeFormatter;
    public class Main{{
        public static void main(String[] args) {{
            String timeString = "13:45:30";
            DateTimeFormatter formatter = DateTimeFormatter.forPattern("HH:mm:ss");  
            LocalTime localTime = LocalTime.parse(timeString, formatter);
            System.out.println("Parsed LocalTime: " + localTime);
        }}
    }}
    ```, the line `DateTimeFormatter formatter = DateTimeFormatter.forPattern("HH:mm:ss");` reports error `The method forPattern(String) is undefined for the type DateTimeFormatter`, which means the method is called incorrectly.
    How to solve the error?
Thought: The error occurs because the DateTimeFormatter class does not have a method named `forPattern(String)`. As the method is a staic method, we need to find which class has the method `forPattern(String)` or to find which method in the class `DateTimeFormatter` works like `forPattern(String)`.
Action: Search[which class has the static method `forPattern(String)` in class `org.joda.time.format.DateTimeFormatter`, or method similar to `forPattern(String)` in class `org.joda.time.format.DateTimeFormatter`]
Observation: [Class] org.joda.time.format.DateTimeFormat\n static DateTimeFormatter forPattern(String pattern) Factory to create a formatter from a pattern string.
Thought: I know th correct answer. The class `DateTimeFormat` has the method `forPattern(String pattern)`. We need to convert the class `DateTimeFormatter` to class `DateTimeFormat`.
```java
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Main{{
    public static void main(String[] args) {{
        String timeString = "13:45:30";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");  
        LocalTime localTime = LocalTime.parse(timeString, formatter);
        System.out.println("Parsed LocalTime: " + localTime);
    }}
}}
```
In this modified code, we use the `DateTimeFormat` class to create a formatter for the specified pattern, which make it without any errors.
Action: Finish[```java
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Main{{
    public static void main(String[] args) {{
        String timeString = "13:45:30";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");  
        LocalTime localTime = LocalTime.parse(timeString, formatter);
        System.out.println("Parsed LocalTime: " + localTime);
    }}
}}
```]
""",
    """Question: In the following java code 
    ```
    import org.apache.commons.math3.linear.Array2DRowRealMatrix;
    import org.apache.commons.math3.linear.RealMatrix;
    import org.apache.commons.math3.linear.RealVector;
    
    public class Matrix {{
        public static RealMatrix getOuterProduct(RealVector vector1, RealVector vector2) {{
            double[] data1 = vector1.toArray();
            double[] data2 = vector2.toArray();
            
            return new Array2DRowRealMatrix(data1).outerProduct(new Array2DRowRealMatrix(data2));
        }}
    }}
	```, the line `return new Array2DRowRealMatrix(data1).outerProduct(new Array2DRowRealMatrix(data2));` reports error `The method outerProduct(Array2DRowRealMatrix) is undefined for the type Array2DRowRealMatrix`, which means the method is called incorrectly.
	How to solve these errors?
Thought: We need to search if there is a method like the `outerProduct` in the class `Array2DRowRealMatrix`.
Action: Search[which method is similar to `outerProduct(Array2DRowRealMatrix)` in class `org.apache.commons.math3.linear.Array2DRowRealMatrix`]
Observation: Array2DRowRealMatrix multiply(Array2DRowRealMatrix m) Returns the result of postmultiplying this by m.
Thought: I know th correct answer. There is no method similar to the method `outerProduct`, but it return the method `multiply`. As the matrix's outer product is computed by one matrix multiply its transpose, we can search if there are methods about "matrix's multuply" and "matrix's transpose" to archieve it.  The following is a solution we provided.
```java
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Matrix {{
    public static RealMatrix getOuterProduct(RealVector vector1, RealVector vector2) {{
        double[] data1 = vector1.toArray();
        double[] data2 = vector2.toArray();
        return new Array2DRowRealMatrix(data1).multiply(new Array2DRowRealMatrix(data2).transpose());
    }}
}}
```
Action: Finish[```java
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Matrix {{
    public static RealMatrix getOuterProduct(RealVector vector1, RealVector vector2) {{
        double[] data1 = vector1.toArray();
        double[] data2 = vector2.toArray();
        return new Array2DRowRealMatrix(data1).multiply(new Array2DRowRealMatrix(data2).transpose());
    }}
}}
```]
""",
]

SUFFIX = """\nQuestion: {input}
{agent_scratchpad}"""

METHODCALL_PROMPT = PromptTemplate.from_examples(
    EXAMPLES, SUFFIX, ["input", "agent_scratchpad"]   # , validate_template=False
)
