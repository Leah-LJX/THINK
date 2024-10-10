import org.w3c.dom.svg.SVGNumber;
import org.w3c.dom.svg.SVGNumberList;

public class SVGNumberListExample {

    public static void main(String[] args) {

        SVGNumberList numberList = new SVGNumberListImpl();

        SVGNumber newItem = new SVGNumberImpl(5);
        int index = 2;

        SVGNumber insertedItem = numberList.insertItemBefore(newItem, index);

        System.out.println("Item inserted: " + insertedItem.getValue());
    }

    public static class SVGNumberImpl implements SVGNumber {

        public final float value;

        public SVGNumberImpl(float value) {
            this.value = value;
        }

        @Override
        public float getValue() {
            return value;
        }

        // other methods

    }

    public static class SVGNumberListImpl implements SVGNumberList {

        // implement SVGNumberList methods

    }

}