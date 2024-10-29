import some.package.TiffImageMetadata;
import some.package.RationalNumber;

public class GPSInfoExample {
    public static void main(String[] args) {
        String latitudeRef = "N";
        String longitudeRef = "W";
        RationalNumber latitudeDegrees = new RationalNumber(41, 1);
        RationalNumber latitudeMinutes = new RationalNumber(59, 1);
        RationalNumber latitudeSeconds = new RationalNumber(30, 1);
        RationalNumber longitudeDegrees = new RationalNumber(87, 1);
        RationalNumber longitudeMinutes = new RationalNumber(39, 1);
        RationalNumber longitudeSeconds = new RationalNumber(0, 1);

        TiffImageMetadata.GPSInfo gpsInfo = new TiffImageMetadata.GPSInfo(latitudeRef, longitudeRef, latitudeDegrees, latitudeMinutes, latitudeSeconds, longitudeDegrees, longitudeMinutes, longitudeSeconds);
    }
}