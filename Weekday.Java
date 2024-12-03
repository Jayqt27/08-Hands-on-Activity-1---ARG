import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Define the Alarm interface
interface Alarm {
    void setAlarm(String time);
    void showAlarm();
}

// Abstract Weekday class implementing Alarm interface
abstract class Weekday implements Alarm {
    protected String time; // Protected field to store the alarm time

    @Override
    public void setAlarm(String time) {
        this.time = time;
    }
}

// Monday class extending Weekday
public class Monday extends Weekday {

    @Override
    public void showAlarm() {
        try {
            // Formatter for parsing and displaying time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            
            // Parse and obtain the alarm time
            LocalTime alarmTime = LocalTime.parse(time, formatter);
            
            // Get current time and date details
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime todayAlarm = LocalDateTime.of(LocalDate.now(), alarmTime);
            LocalDateTime tomorrowAlarm = todayAlarm.plusDays(1);

            // Compare current time with alarm time and display appropriate message
            if (todayAlarm.isBefore(now)) {
                System.out.println("\u001B[32mAlarm is set for Tomorrow at " + tomorrowAlarm.format(formatter));
            } else {
                System.out.println("\u001B[32mI'll wake you up later at " + todayAlarm.format(formatter));
            }
            System.out.println("\u001B[0m");
        } catch (DateTimeParseException e) {
            System.out.println("\u001B[31mInvalid time format. Please use HH:MM format.");
        }
    }

    public static void main(String[] args) {
        Monday monday = new Monday();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the alarm time in HH:MM format: ");
            String inputTime = scanner.nextLine();

            if (isValidTime(inputTime)) {
                monday.setAlarm(inputTime);
                break;
            } else {
                System.out.println("\u001B[31mInvalid time format! Please try again using HH:MM.");
            }
        }

        monday.showAlarm();
        scanner.close();
    }

    // Utility method to validate the time input format
    private static boolean isValidTime(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            LocalTime.parse(time, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
