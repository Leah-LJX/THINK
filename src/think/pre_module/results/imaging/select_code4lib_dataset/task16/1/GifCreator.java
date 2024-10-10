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
            Imaging.writeImageSequence(os, Imaging.GIF, images.toArray(new BufferedImage[0]), frameDelays);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}