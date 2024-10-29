import com.example.PDBorderEffectDictionary;

public class BorderExample {
    public static void main(String[] args) {
        PDBorderEffectDictionary dictionary = new PDBorderEffectDictionary();
        String style = dictionary.getStyle();
        System.out.println("Border style: " + style);
    }
}