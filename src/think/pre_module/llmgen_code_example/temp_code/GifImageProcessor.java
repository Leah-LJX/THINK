import com.example.GifImageMetadataItem;

public class GifImageProcessor {
    public void processGifMetadata(GifImageMetadataItem metadataItem) {
        int delay = metadataItem.getDelay();
        System.out.println("Delay: " + delay);
    }
}