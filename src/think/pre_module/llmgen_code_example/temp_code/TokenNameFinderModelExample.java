import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.maxent.MaxentModel;
import java.util.Map;

public class TokenNameFinderModelExample {

    public static void main(String[] args) {
        String languageCode = "en";
        MaxentModel nameFinderModel = new MaxentModel();
        Map<String, Object> resources = null;
        Map<String, String> manifestInfoEntries = null;

        TokenNameFinderModel model = new TokenNameFinderModel(languageCode, nameFinderModel, resources, manifestInfoEntries);
    }
}