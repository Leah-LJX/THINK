import java.util.ArrayList;
import java.util.List;

public class TextTitle {
    public List<TitleChangeListener> listeners = new ArrayList<>();
    public String urlText;

    public void setURLText(String text) {
        this.urlText = text;
        sendTitleChangeEvent();
    }

    public void sendTitleChangeEvent() {
        for (TitleChangeListener listener : listeners) {
            listener.onTitleChange(this.urlText);
        }
    }

    public void addTitleChangeListener(TitleChangeListener listener) {
        listeners.add(listener);
    }

    public void removeTitleChangeListener(TitleChangeListener listener) {
        listeners.remove(listener);
    }

    public interface TitleChangeListener {
        void onTitleChange(String newTitle);
    }
}