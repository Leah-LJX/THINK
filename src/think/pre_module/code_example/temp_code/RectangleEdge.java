import org.apache.commons.math3.util.Precision;

public class RectangleEdge {
    public double length;
    public double width;

    public RectangleEdge(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RectangleEdge that = (RectangleEdge) o;
        return Precision.equals(length, that.length) &&
                Precision.equals(width, that.width);
    }
}