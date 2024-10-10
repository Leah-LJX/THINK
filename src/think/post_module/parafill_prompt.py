# flake8: noqa
from langchain.prompts.prompt import PromptTemplate

EXAMPLES = [
    """Question: In the following java code 
    ```
    import org.apache.commons.math.linear.SingularValueDecomposition; 
    import org.apache.commons.math.linear.SingularValueDecompositionImpl;
    import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult;
    public class Matrix {{ 
        public SVDecompositionCommonsResult evaluate(double[][] matrix) {{ 
            SingularValueDecomposition svd = new SingularValueDecompositionImpl(matrix);
            return new SVDecompositionCommonsResult(svd); 
        }}
	}}
	```, the line `SingularValueDecomposition svd = new SingularValueDecompositionImpl(matrix);` reports error `The constructor SingularValueDecompositionImpl(double[][]) is undefined`, which means the parameters are incorrectly populated.
	How to solve this error?
Thought: We need to search constructors of the class `org.apache.commons.math.linear.SingularValueDecompositionImpl` to find the correct parameter type.
Action: Search[constructor of class org.apache.commons.math.linear.SingularValueDecomposition]
Observation: SingularValueDecompositionImpl(RealMatrix matrix)	 Calculates the compact Singular Value Decomposition of the given matrix.
Thought: The parameter type required of the constructor is `RealMatrix`. However, the matrix is `double[][]` type. We need to write some code for converting the `double[][]` type to `RealMatrix` type. The following is a solution we provided.
```java
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult; 

public class Matrix {{
    public SVDecompositionCommonsResult evaluate(double[][] matrix) {{
        // Convert double[][] to RealMatrix
        RealMatrix realMatrix = new Array2DRowRealMatrix(matrix);

        // Use SingularValueDecompositionImpl constructor with RealMatrix
        SingularValueDecomposition svd = new SingularValueDecompositionImpl(realMatrix);

        return new SVDecompositionCommonsResult(svd);
    }}
}}
```
In this modified code, we use Array2DRowRealMatrix to convert your double[][] array to a RealMatrix. The RealMatrix instance is then used as a parameter for the SingularValueDecompositionImpl constructor without any errors.
Action: Finish[```java
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult; 

public class Matrix {{
    public SVDecompositionCommonsResult evaluate(double[][] matrix) {{
        // Convert double[][] to RealMatrix
        RealMatrix realMatrix = new Array2DRowRealMatrix(matrix);

        // Use SingularValueDecompositionImpl constructor with RealMatrix
        SingularValueDecomposition svd = new SingularValueDecompositionImpl(realMatrix);

        return new SVDecompositionCommonsResult(svd);
    }}
}}
```]
""",
    """Question: In the following java code 
    ```
    import org.apache.commons.math.linear.SingularValueDecomposition; 
    import org.apache.commons.math.linear.SingularValueDecompositionImpl;
    import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult;
    public class Matrix {{
        public SVDecompositionCommonsResult evaluate(double[][] arg0) {{
            SingularValueDecompositionImpl svd = new SingularValueDecompositionImpl(arg0);
		    return new SVDecompositionCommonsResult(svd.getSingularValues());
        }}
	}}
	```, the line `SingularValueDecompositionImpl svd = new SingularValueDecompositionImpl(arg0);` reports error `The constructor SingularValueDecompositionImpl(double[][]) is undefined`, the line `return new SVDecompositionCommonsResult(svd.getSingularValues());` reports error `The constructor SVDecompositionCommonsResult(double[]) is undefined`, which means the parameters are incorrectly populated.
	How to solve these errors?
Thought: We need to search class `SingularValueDecompositionImpl` from the package `org.apache.commons.math.linear` and class `SVDecompositionCommonsResult` from package `com.opengamma.analytics.math.linearalgebra`, to find these constructors.
Action: Search[constructor of class org.apache.commons.math.linear.SingularValueDecompositionImpl, constructor of  class com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult]
Observation: SingularValueDecompositionImpl(RealMatrix matrix)	 Calculates the compact Singular Value Decomposition of the given matrix.
SVDecompositionCommonsResult(SingularValueDecomposition svd)
Thought: The parameter type required of the `SingularValueDecompositionImpl` constructor is `RealMatrix`. However, the arg0 is `double[][]` type. 
The parameter type required of the `SVDecompositionCommonsResult` constructor is `SingularValueDecomposition`. However, the svd.getSingularValues() is `double[]` type. We need to modify the code to solve these errors. The following is a solution we provided.
```java
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult;

public class Matrix {{
    public SVDecompositionCommonsResult evaluate(double[][] arg0) {{
        RealMatrix realMatrix = new Array2DRowRealMatrix(arg0);
        SingularValueDecomposition svd = new SingularValueDecompositionImpl(realMatrix);
        return new SVDecompositionCommonsResult(svd);
    }}
}}
```
In this modified code, we use Array2DRowRealMatrix to convert your `double[][]` array to a `RealMatrix`. And then use the `SingularValueDecomposition` object directly for the `SVDecompositionCommonsResult` constructor without any errors.
Action: Finish[```java
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult;

public class Matrix {{
    public SVDecompositionCommonsResult evaluate(double[][] arg0) {{
        RealMatrix realMatrix = new Array2DRowRealMatrix(arg0);
        SingularValueDecomposition svd = new SingularValueDecompositionImpl(realMatrix);
        return new SVDecompositionCommonsResult(svd);
    }}
}}
```]
""",
    """Question: In the following java code 
    ```
    import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
    import com.opengamma.analytics.math.util.wrapper.CommonsMathWrapper;
    import org.apache.commons.math.linear.RealMatrix;
    public class Matrix {{ 
        public SVDecompositionCommonsResult invert(DoubleMatrix2D arg0) {{ 
            RealMatrix realMatrix = CommonsMathWrapper.wrap(arg0);
		    RealMatrix inverseMatrix = realMatrix.inverse();
		    return CommonsMathWrapper.unwrap(new Array2DRowRealMatrix(inverseMatrix.getData()));
        }}
	}}
	```, the line `return CommonsMathWrapper.unwrap(new Array2DRowRealMatrix(inverseMatrix.getData()));` reports error `The method unwrap(RealMatrix) in the type CommonsMathWrapper is not applicable for the arguments (Array2DRowRealMatrix)`, which means the parameters are incorrectly populated.
	How to solve these errors?
Thought: The error message indicates that the correct method signature for `upwrap` is `unwrap(RealMatrix)`. It shows that the correct argument type of the method `upwrap(RealMatrix)` is `RealMatrix`. However, the code `new Array2DRowRealMatrix(inverseMatrix.getData())` returned is `Array2DRowRealMatrix`. 
We need to adjust the type of `RealMatrix` to the method `unwrap`. The following is a solution we provided.
```java
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.util.wrapper.CommonsMathWrapper;
import org.apache.commons.math.linear.RealMatrix;
import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult;

public class Matrix {{
    public SVDecompositionCommonsResult invert(DoubleMatrix2D arg0) {{
        RealMatrix realMatrix = CommonsMathWrapper.wrap(arg0);
        RealMatrix inverseMatrix = realMatrix.inverse();

        // Pass RealMatrix directly to unwrap
        return CommonsMathWrapper.unwrap(inverseMatrix);
    }}
}}
```
In this modified code, we pass the `RealMatrix` directly to the `CommonsMathWrapper.unwrap` method without any errors.
Action: Finish[```java
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.util.wrapper.CommonsMathWrapper;
import org.apache.commons.math.linear.RealMatrix;
import com.opengamma.analytics.math.linearalgebra.SVDecompositionCommonsResult;

public class Matrix {{
    public SVDecompositionCommonsResult invert(DoubleMatrix2D arg0) {{
        RealMatrix realMatrix = CommonsMathWrapper.wrap(arg0);
        RealMatrix inverseMatrix = realMatrix.inverse();

        // Pass RealMatrix directly to unwrap
        return CommonsMathWrapper.unwrap(inverseMatrix);
    }}
}}
```]
""",
    """Question: In the following java code
    ```
    import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

    public class StatisticsExample {{
        public static void main(String[] args) {{
            DescriptiveStatistics stats = new DescriptiveStatistics();
    
            String numberStr = "5.0";
            stats.addValue(numberStr); 
        }}
    }}
    ```, the line `stats.addValue(numberStr);` reports error `The method addValue(double) in the type DescriptiveStatistics is not applicable for the arguments (String)`, which means the parameters are incorrectly populated.
    How to solve these errors?
Thought: The method `addValue` expects an argument of type double, and passing the string directly will result in a compilation error. We need a method to safely convert a `String` to type `double`.
Action: Search[Convert String type to double]
Observation: double NumberUtils.toDouble(String arg0, double arg1)
Thought: The class `NumberUtils` provides the `toDouble method`, which converts a `String` to a `double`. The following is a solution we provided.
```java
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.lang3.math.NumberUtils;

public class StatisticsExample {{
    public static void main(String[] args) {{
        DescriptiveStatistics stats = new DescriptiveStatistics();
        String numberStr = "5.0";
        double number = NumberUtils.toDouble(numberStr, 0.0);  // 安全转换
        stats.addValue(number);  // 正确调用
    }}
}}
```
Action: Finish[```java
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.lang3.math.NumberUtils;

public class StatisticsExample {{
    public static void main(String[] args) {{
        DescriptiveStatistics stats = new DescriptiveStatistics();
        String numberStr = "5.0";
        double number = NumberUtils.toDouble(numberStr, 0.0);  // 安全转换
        stats.addValue(number);  // 正确调用
    }}
}}
```]
""",
]


SUFFIX = """\nQuestion: {input}
{agent_scratchpad}"""

PARAFILL_PROMPT = PromptTemplate.from_examples(
    EXAMPLES, SUFFIX, ["input", "agent_scratchpad"]   # , validate_template=False
)
