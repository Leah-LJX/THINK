import com.sun.media.jai.codecimpl.TiffImageMetadata;
import javax.media.jai.RationalNumber;

public class GPSInfoUsageExample {
    public static void main(String[] args) {
        String latitudeRef = "N";
        String longitudeRef = "E";
        RationalNumber latitudeDegrees = new RationalNumber(39, 1);
        RationalNumber latitudeMinutes = new RationalNumber(30, 1);
        RationalNumber latitudeSeconds = new RationalNumber(25, 1);
        RationalNumber longitudeDegrees = new RationalNumber(98, 1);
        RationalNumber longitudeMinutes = new RationalNumber(23, 1);
        RationalNumber longitudeSeconds = new RationalNumber(56, 1);

        TiffImageMetadata.GPSInfo gpsInfo = new TiffImageMetadata.GPSInfo(latitudeRef, longitudeRef, latitudeDegrees, latitudeMinutes, latitudeSeconds, longitudeDegrees, longitudeMinutes, longitudeSeconds);
    }
}