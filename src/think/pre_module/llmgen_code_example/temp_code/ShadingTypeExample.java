import org.apache.pdfbox.pdmodel.graphics.shading.PDShadingType2;

public class ShadingTypeExample {

    public static void main(String[] args) {
        PDShadingType2 shading = new PDShadingType2();

        int shadingType = shading.getShadingType();
        System.out.println("Shading Type: " + shadingType);
    }
}