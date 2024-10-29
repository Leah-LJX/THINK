import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class DataUtils {
    public static void main(String[] args) {
        double[][] data = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        
        RealMatrix matrix = new Array2DRowRealMatrix(data);
        
        int[] validRows = {0, 1, 2};
        
        double total = calculateColumnTotal(matrix, 1, validRows);
        System.out.println("Total for column 1: " + total);
    }

    public static double calculateColumnTotal(RealMatrix data, int column, int[] validRows) {
        double total = 0.0;
        for (int row : validRows) {
            total += data.getEntry(row, column);
        }
        return total;
    }
}