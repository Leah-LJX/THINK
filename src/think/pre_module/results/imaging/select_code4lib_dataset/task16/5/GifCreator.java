import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
                BufferedImage image = Imaging.getBufferedImage(file);
                images.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (OutputStream os = new FileOutputStream(gifOutputPath)) {
            Imaging.writeImageSequence(os, Imaging.GIF, images, frameDelays);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}