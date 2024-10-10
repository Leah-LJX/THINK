import java.awt.image.BufferedImage;

public class DitheringExample {
    public static void main(String[] args) {
        BufferedImage originalImage = // initialize with original image;
        Palette palette = // initialize with desired palette;

        Dithering.applyFloydSteinbergDithering(originalImage, palette);
    }
}