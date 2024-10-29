import java.util.Date;

public class IntervalChecker {
    protected void checkInterval(long start, long end) {
        if (start >= 0 && end >= 0 && start < end) {
            System.out.println("Interval is valid");
        } else {
            System.out.println("Invalid interval");
        }
    }
    
    public static void main(String[] args) {
        long start = new Date().getTime(); // Replace with actual start time
        long end = start + 10000; // Replace with actual end time
        IntervalChecker checker = new IntervalChecker();
        checker.checkInterval(start, end);
    }
}