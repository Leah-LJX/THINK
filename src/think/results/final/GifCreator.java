import {'ImageTypeSpecifier': 'javax.imageio.ImageTypeSpecifier'};
import {'ImageTypeSpecifier': 'javax.imageio.ImageTypeSpecifier'};
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class GifCreator {

    public static void createAnimatedGif(String[] imagePaths, String gifOutputPath, int[] frameDelays) throws IOException, ImageReadException {
        if (imagePaths.length != frameDelays.length) {
            throw new IllegalArgumentException("The number of image paths and frame delays must be the same.");
        }

        ImageWriter gifWriter = null;
        ImageOutputStream ios = null;
        try {
            gifWriter = getGifWriter();
            ios = ImageIO.createImageOutputStream(new File(gifOutputPath));
            gifWriter.setOutput(ios);
            gifWriter.prepareWriteSequence(null);

            for (int i = 0; i < imagePaths.length; i++) {
                BufferedImage image = Imaging.getBufferedImage(new File(imagePaths[i]));
                IIOImage temp = new IIOImage(image, null, getMetadata(gifWriter, frameDelays[i]));
                gifWriter.writeToSequence(temp, null);
            }
            gifWriter.endWriteSequence();
        } finally {
            if (ios != null) {
                ios.close();
            }
            if (gifWriter != null) {
                gifWriter.dispose();
            }
        }
    }

    public static ImageWriter getGifWriter() {
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("gif");
        if (iter.hasNext()) {
            return iter.next();
        }
        throw new IllegalStateException("No GIF Image Writers found");
    }

    public static IIOMetadata getMetadata(ImageWriter writer, int delayTime) throws IOException {
        ImageWriteParam param = writer.getDefaultWriteParam();
        IIOMetadata metadata = writer.getDefaultImageMetadata(ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB), param);

        String metaFormatName = metadata.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(metaFormatName);

        IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");

        graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
        graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(delayTime / 10)); // delayTime in 1/100 of a second
        graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

        metadata.setFromTree(metaFormatName, root);

        return metadata;
    }

    public static IIOMetadataNode getNode(IIOMetadataNode root, String nodeName) {
        int nNodes = root.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (root.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                return (IIOMetadataNode) root.item(i);
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        root.appendChild(node);
        return node;
    }

    public static void main(String[] args) {
        String[] imagePaths = {"image1.png", "image2.png", "image3.png"};
        String gifOutputPath = "output.gif";
        int[] frameDelays = {500, 1000, 500}; // delays in milliseconds

        try {
            createAnimatedGif(imagePaths, gifOutputPath, frameDelays);
            System.out.println("GIF created successfully.");
        } catch (IOException | ImageReadException e) {
            e.printStackTrace();
        }
    }
}