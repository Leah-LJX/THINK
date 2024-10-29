import some.library.PhotometricInterpreterYCbCr;

public class YCbCrExample {
    public static void main(String[] args) {
        int Y = 150;
        int Cb = 50;
        int Cr = 100;

        int rgbValue = PhotometricInterpreterYCbCr.convertYCbCrtoRGB(Y, Cb, Cr);
        System.out.println("RGB value: " + rgbValue);
    }
}