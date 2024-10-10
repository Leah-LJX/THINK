import java.awt.Color;
import java.awt.Font;

public class AppearanceStyle {
    public Color textColor;
    public Color backgroundColor;
    public Font font;
    
    public AppearanceStyle(Color textColor, Color backgroundColor, Font font) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.font = font;
    }

    public static void main(String[] args) {
        Color textColor = Color.BLACK;
        Color backgroundColor = Color.WHITE;
        Font font = new Font("Arial", Font.PLAIN, 12);
        
        AppearanceStyle style = new AppearanceStyle(textColor, backgroundColor, font);
    }
}