package findErrors;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.jdt.core.compiler.batch.BatchCompiler;

public class EclipseCompiler {

    public static void main(String[] args) {
        // 指定要编译的 Java 文件路径
        String filePath = "code/InnerProductCalculator.java";

        // 执行 Eclipse 编译
        String classpath = ".;lib/commons-cli-1.2.jar;lib/commons-io-2.5.jar;lib/commons-lang-2.6.jar;lib/commons-lang3-3.4.jar;lib/commons-logging-1.2.jar;lib/commons-math-2.2.jar;lib/commons-math3-3.5.jar;lib/og-analytics-2.17.0.jar";
        String result = compileWithEclipse(filePath, classpath);
       
        System.out.println(result);
//        System.out.println(result.split("\n").length);
//        for(String each: result.split("\n")) {
//        	System.out.println("-----"+each);
//        }
    }

    public static String compileWithEclipse(String filePath, String classpath) {
    	String commandLine = "-classpath "+classpath+" -source 1.8 "+filePath;
        
    	StringWriter errorStrWriter = new StringWriter();
    	// 创建一个PrintWriter, 关联到StringWriter
    	PrintWriter errorWriter = new PrintWriter(errorStrWriter);
    	// 执行编译
        boolean result = BatchCompiler.compile(commandLine, new PrintWriter(System.out), errorWriter, null);  // new PrintWriter(System.err)

        if (result) {
            System.out.println("Compilation successful");
        } else {
            System.err.println("Compilation failed with exit code: " + result);
        }
        return errorStrWriter.toString();
    }
}

