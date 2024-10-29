import org.apache.commons.math3.util.RectangleEdge;

public class RectangleEdgeExample {

    public static void main(String[] args){
        RectangleEdge edge = RectangleEdge.BOTTOM;
        
        int hashCode = edge.hashCode();
        System.out.println("Hash Code: " + hashCode);
    }
}