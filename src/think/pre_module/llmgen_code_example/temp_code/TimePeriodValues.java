import java.util.List;

public class TimePeriodValues {
    public List<Long> endTimes;

    public TimePeriodValues(List<Long> endTimes) {
        this.endTimes = endTimes;
    }

    public int getMinEndIndex() {
        // Implementation of finding the index of the time period with the minimum end milliseconds
        int minIndex = 0;
        long minEndTime = endTimes.get(0);
        for (int i = 1; i < endTimes.size(); i++) {
            if (endTimes.get(i) < minEndTime) {
                minEndTime = endTimes.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}