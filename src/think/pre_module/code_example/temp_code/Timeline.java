import java.util.List;

public class Timeline {
    public List<Long> values;

    public Timeline(List<Long> values) {
        this.values = values;
    }

    public boolean containsDomainValue(long millisecond) {
        return values.contains(millisecond);
    }
}