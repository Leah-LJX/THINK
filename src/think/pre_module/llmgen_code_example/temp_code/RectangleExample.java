import org.apache.commons.math3.util.RectangleEdge;

public class RectangleExample {
    public static void main(String[] args) {
        RectangleEdge edge = RectangleEdge.TOP;
        int hash = edge.hashCode();
        System.out.println("Hash code for RectangleEdge.TOP: " + hash);
    }
}