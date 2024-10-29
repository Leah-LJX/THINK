import java.util.List;

public class ImageFormats {
    public List<String> formats;

    public ImageFormats(List<String> formats) {
        this.formats = formats;
    }

    public List<String> getFormats() {
        return formats;
    }

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> formatList = List.of("JPEG", "PNG", "GIF");
        ImageFormats imageFormats = new ImageFormats(formatList);
        
        System.out.println("Supported image formats: " + imageFormats.getFormats());
    }
}