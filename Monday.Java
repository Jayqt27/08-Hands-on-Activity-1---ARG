import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Main class named Monday extending a hypothetical Weekday class
public class Monday extends Weekday {

    // Method to validate time in the format HH:mm
    static boolean isValidTime(String time) {
        try {
            // Parse using DateTimeFormatter with pattern HH:mm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            LocalTime.parse(time, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Monday monday = new Monday();
        Scanner scanner = new Scanner(System.in);
        String time;

        // Loop to get valid time input from the user
        while (true) {
            System.out.print("Enter the alarm time in military format (HH:MM): ");
            time = scanner.nextLine();

            // Validate the input time format
            if (isValidTime(time)) {
                monday.setAlarm(time); // Set the alarm if valid
                break;
            } else {
                System.out.println("Invalid time format! Please try again using HH:MM (e.g., 08:00 or 23:45).");
            }
        }

        // Show the alarm details after setting it
        monday.showAlarm();
        scanner.close();
    }
}
