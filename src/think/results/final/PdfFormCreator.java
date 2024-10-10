import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDChoice;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PdfFormCreator {
    public static void createFillablePdfForm(String outputPath, Map<String, String> fieldTypes) {
        // Create a new PDDocument
        PDDocument document = new PDDocument();
        try {
            // Create a blank page and add it to the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a new AcroForm
            PDAcroForm acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            // Iterate over the field types and create fields accordingly
            for (Map.Entry<String, String> entry : fieldTypes.entrySet()) {
                String fieldName = entry.getKey();
                String fieldType = entry.getValue();

                PDField field;
                switch (fieldType.toLowerCase()) {
                    case "text":
                        field = new PDTextField(acroForm);
                        field.setPartialName(fieldName);
                        acroForm.getFields().add(field);
                        break;

                    case "checkbox":
                        field = new PDCheckBox(acroForm);
                        field.setPartialName(fieldName);
                        acroForm.getFields().add(field);
                        break;

                    case "dropdown":
                        field = new PDComboBox(acroForm);
                        ((PDChoice) field).setOptions(Arrays.asList("Option 1", "Option 2", "Option 3"));
                        field.setPartialName(fieldName);
                        acroForm.getFields().add(field);
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
                }
            }

            // Save the document to the specified output path
            document.save(outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the document to free resources
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        Map<String, String> fieldTypes = new HashMap<>();
        fieldTypes.put("name", "text");
        fieldTypes.put("subscribe", "checkbox");
        fieldTypes.put("gender", "dropdown");

        createFillablePdfForm("output.pdf", fieldTypes);
    }
}