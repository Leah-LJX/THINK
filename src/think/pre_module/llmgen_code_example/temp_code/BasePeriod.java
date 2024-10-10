import org.joda.time.PeriodType;

public class BasePeriod {
    public PeriodType periodType;

    protected PeriodType checkPeriodType(PeriodType type) {
        // API usage example
        BasePeriod basePeriod = new BasePeriod();
        PeriodType validatedPeriodType = basePeriod.checkPeriodType(type);
        return validatedPeriodType;
    }
}