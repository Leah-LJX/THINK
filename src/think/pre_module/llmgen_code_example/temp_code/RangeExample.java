import java.awt.geom.Range;

public class RangeExample {
    public static void main(String[] args) {
        Range base = new Range(0, 10);
        double delta = 5.0;

        Range shiftedRange = Range.shift(base, delta);
    }
}