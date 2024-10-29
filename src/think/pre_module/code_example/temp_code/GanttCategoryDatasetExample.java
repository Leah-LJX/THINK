import org.jfree.data.category.GanttCategoryDataset;

public class GanttCategoryDatasetExample {
    public static void main(String[] args) {
        GanttCategoryDataset dataset = new GanttCategoryDataset(); // create an instance of the GanttCategoryDataset

        int row = 1; // initialize the row
        int column = 2; // initialize the column
        int subinterval = 3; // initialize the subinterval

        Number percentComplete = dataset.getPercentComplete(row, column, subinterval); // get the percentage complete value
        System.out.println("Percent Complete: " + percentComplete);
    }
}