import java.util.ArrayList;
import java.util.List;

public class ChunkSample {
    public String[] phrases;

    public ChunkSample(String[] phrases) {
        this.phrases = phrases;
    }

    public String nicePrint() {
        // Implementation of nicePrint method
    }

    public static void main(String[] args) {
        String[] examplePhrases = {"[NP Rockwell_NNP ]", "[VP said_VBD ]", "[NP the_DT agreement_NN ]",
                "[VP calls_VBZ ]", "[SBAR for_IN ]", "[NP it_PRP ]", "[VP to_TO supply_VB ]",
                "[NP 200_CD additional_JJ so-called_JJ shipsets_NNS ]", "[PP for_IN ]", "[NP the_DT planes_NNS ]", "._."
        };

        ChunkSample chunk = new ChunkSample(examplePhrases);
        String niceString = chunk.nicePrint();
        System.out.println(niceString);
    }
}