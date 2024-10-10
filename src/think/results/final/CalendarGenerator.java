import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CalendarGenerator {

    public static void generatePdfCalendar(String year, String theme, Map<String, List<String>> holidays, Map<String, String> events, String outputPath) {
        int yearInt = Integer.parseInt(year);
        try (PDDocument document = new PDDocument()) {
            // Iterate through each month
            for (int month = 1; month <= 12; month++) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    drawMonth(contentStream, yearInt, month, theme, holidays, events);
                }
            }
            
            document.save(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawMonth(PDPageContentStream contentStream, int year, int month, String theme, Map<String, List<String>> holidays, Map<String, String> events) throws IOException {
        String monthName = LocalDate.of(year, month, 1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        
        // Draw month title
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
        contentStream.newLineAtOffset(220, 750);
        contentStream.showText(monthName + " " + year);
        contentStream.endText();

        // Draw days of the week
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        for (int i = 0; i < days.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(50 + i * 80, 700);
            contentStream.showText(days[i]);
            contentStream.endText();
        }

        // Draw days
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int dayOfWeekOffset = firstDayOfMonth.getDayOfWeek().getValue() % 7;
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        int dayCounter = 1;
        for (int week = 0; week < 6; week++) {
            for (int day = 0; day < 7; day++) {
                int x = 50 + day * 80;
                int y = 650 - week * 50;
                if (week == 0 && day < dayOfWeekOffset) {
                    continue;
                }
                if (dayCounter <= daysInMonth) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String dayString = Integer.toString(dayCounter);
                    contentStream.showText(dayString);
                    
                    // Check for holidays
                    String currentDate = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayCounter);
                    if (holidays.containsKey(currentDate)) {
                        contentStream.setNonStrokingColor(255, 0, 0); // Red for holidays
                        contentStream.showText("*"); // Mark holiday
                        contentStream.setNonStrokingColor(0, 0, 0); // Reset color
                    }
                    
                    // Check for events
                    if (events.containsKey(currentDate)) {
                        contentStream.newLineAtOffset(0, -15);
                        contentStream.showText(events.get(currentDate));
                    }
                    contentStream.endText();
                    dayCounter++;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        Map<String, List<String>> holidays = new HashMap<>();
        holidays.put("2024-01-01", Arrays.asList("New Year's Day"));
        holidays.put("2024-12-25", Arrays.asList("Christmas Day"));
        
        Map<String, String> events = new HashMap<>();
        events.put("2024-01-15", "Meeting with team");
        events.put("2024-02-14", "Valentine's Day Dinner");
        
        generatePdfCalendar("2024", "default", holidays, events, "output_calendar.pdf");
    }
}