import java.time.LocalTime;

public class LocalTimeExample {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        int second = time.getSecond();
        System.out.println("Second of minute: " + second);
    }
}