import some.package.POSTaggerCrossValidatorTool;

public class POSTaggerExample {
    public static void main(String[] args) {
        POSTaggerCrossValidatorTool posTagger = new POSTaggerCrossValidatorTool();
        String shortDescription = posTagger.getShortDescription();
        System.out.println("Short Description: " + shortDescription);
    }
}