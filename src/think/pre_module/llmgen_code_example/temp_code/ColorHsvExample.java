import java.awt.Color;

public class ColorHsvExample {
    public static void main(String[] args) {
        float hue = 0.5f;
        float saturation = 0.8f;
        float value = 0.9f;
        
        ColorHsv hsvColor = new ColorHsv(hue, saturation, value);
        
        float newHue = hsvColor.getHue();
        float newSaturation = hsvColor.getSaturation();
        float newValue = hsvColor.getValue();
        
        System.out.println("Hue: " + newHue);
        System.out.println("Saturation: " + newSaturation);
        System.out.println("Value: " + newValue);
    }
}