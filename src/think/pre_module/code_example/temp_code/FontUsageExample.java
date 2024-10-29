import com.example.fonts.PDCIDFontType0;

public class FontUsageExample {
    public static void main(String[] args) {
        PDCIDFontType0 font = new PDCIDFontType0();
        int charCode = 65; // Unicode for letter 'A'
        float height = font.getHeight(charCode);
        System.out.println("Height of character A: " + height);
    }
}