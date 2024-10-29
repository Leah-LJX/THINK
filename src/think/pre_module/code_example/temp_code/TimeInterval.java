import java.util.Date;

public class TimeInterval extends AbstractInterval {
    public long start;
    public long end;

    public TimeInterval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public void validateInterval() {
        checkInterval(start, end);
    }
}