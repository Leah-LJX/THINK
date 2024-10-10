import java.time.chrono.Chronology;

public class BaseDateTime {
    protected Chronology chronology;

    public BaseDateTime(Chronology chronology){
        this.chronology = checkChronology(chronology);
    }

    protected Chronology checkChronology(Chronology chronology){
        // implementation to check and potentially alter the specified chronology
        return chronology;
    }
}