import java.lang.Cloneable;

public class PublicCloneable implements Cloneable {
    
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}