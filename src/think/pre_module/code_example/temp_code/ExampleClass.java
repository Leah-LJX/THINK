import org.apache.commons.imaging.formats.tiff.constants.TagInfo;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;

public class ExampleClass {
    public TiffField tiffField;
    public TagInfo tagInfo;

    public void exampleMethod() {
        JpegImageMetadata jpegImageMetadata = new JpegImageMetadata(null, null);
        tiffField = jpegImageMetadata.findEXIFValueWithExactMatch(tagInfo);
    }
}