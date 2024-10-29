import java.time.Instant;

public class InstantExample {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        Instant convertedInstant = instant.toInstant();
        System.out.println("Converted Instant: " + convertedInstant);
    }
}