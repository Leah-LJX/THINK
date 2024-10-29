import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.ArrayList;
import java.io.File;

public class ChartDeleter implements HttpSessionBindingListener {
    public ArrayList<File> files = new ArrayList<>();

    public void valueUnbound(HttpSessionBindingEvent event) {
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
}