import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class DataProcessing {
    public static void main(String[] args) {
        double[][] rawData = {
            {1.2, 2.3, 3.4},
            {4.5, 5.6, 6.7},
            {7.8, 8.9, 9.0}
        };
        RealMatrix matrix = new Array2DRowRealMatrix(rawData);
        int[] validRows = {0, 2};
        
        double columnTotal = DataUtils.calculateColumnTotal(matrix, 1, validRows);
        System.out.println("Total of column 1 in valid rows: " + columnTotal);
    }
}