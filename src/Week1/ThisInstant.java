package Week1;

import java.util.Calendar;

public class ThisInstant {
    public static void main(String[] args) {

        long millis = System.currentTimeMillis(); // Unix timestamp in milliseconds
        System.out.println("Current Unix time in ms is " + millis); // prints a Unix timestamp in milliseconds
        System.out.println("Current Unix time in s is " + millis / 1000); // prints the same Unix timestamp in seconds
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
    }
}
