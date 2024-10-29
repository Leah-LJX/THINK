import java.util.Date;

public class DateRange {
    public Date startDate;
    public Date endDate;

    public Date getLowerDate() {
        return startDate.before(endDate) ? startDate : endDate;
    }
}