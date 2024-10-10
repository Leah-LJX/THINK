package findErrors;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.jdt.core.compiler.batch.BatchCompiler;

public class Compiler {

    public static String compileWithEclipse(String filePath, String classpath) {
    	String commandLine = "-classpath "+classpath+" -source 1.8 "+filePath;
        
    	StringWriter errorStrWriter = new StringWriter();
    	PrintWriter errorWriter = new PrintWriter(errorStrWriter);
        boolean result = BatchCompiler.compile(commandLine, new PrintWriter(System.out), errorWriter, null);

        if (result) {
            System.out.println("Compilation successful");
        } else {
            System.err.println("Compilation failed with exit code: " + result);
        }
        return errorStrWriter.toString();
    }
}

