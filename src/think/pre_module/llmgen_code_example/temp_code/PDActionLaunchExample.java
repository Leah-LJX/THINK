import org.apache.pdfbox.pdmodel.interactive.action.PDActionLaunch;

public class PDActionLaunchExample {
    public static void main(String[] args) {
        // create an instance of PDActionLaunch
        PDActionLaunch action = new PDActionLaunch();
        
        // get the file name to be launched
        String fileName = action.getF();
        
        System.out.println("File name to be launched: " + fileName);
    }
}