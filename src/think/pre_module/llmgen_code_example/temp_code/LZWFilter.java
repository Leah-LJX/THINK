import java.io.InputStream;
import java.io.OutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;

public class LZWFilter extends FilterOutputStream {
    public LZWFilter(OutputStream out) {
        super(out);
    }

    public void compress(InputStream in) {
        // implementation here
    }

    public void decompress(InputStream in) {
        // implementation here
    }
}