import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSummary;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class GifCreator {

    public static void createAnimatedGif(String[] imagePaths, String gifOutputPath, int[] frameDelays) {
        List<BufferedImage> images = new ArrayList<>();
        for (String path : imagePaths) {
            try {
                File file = new File(path);
                BufferedImage image = ImageIO.read(file);
                images.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            OutputStream os = Imaging.getBufferedOutputStream(new File(gifOutputPath));
            Imaging.writeImage(os, images, Imaging.Format.GIF, getGifParams(frameDelays));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GifImagingParameters getGifParams(int[] frameDelays) {
        GifImagingParameters params = new GifImagingParameters();
        for (int delay : frameDelays) {
            params.addFrameDelay(delay);
        }
        return params;
    }

    public static class GifImagingParameters {
        public final List<Integer> frameDelays = new ArrayList<>();

        public void addFrameDelay(int delay) {
            frameDelays.add(delay);
        }

        public int getDelay(int frameIndex) {
            return frameDelays.get(frameIndex);
        }
    }
}