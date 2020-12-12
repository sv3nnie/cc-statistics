package application.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    // Regex to validate valid e-mails
    private static final String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n"
            + ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    // Regex to validate valid URLs
    private static final String regexURL = "https?://" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{1,256}\\.[a-z]"
            + "{1,256}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)" + "[a-zA-Z0-9@:%._\\+~#?&//=]";
    // Regex to validate postal codes
    private static final String regexPostalcode = "[1-9]" + "[0-9]" + "[0-9]" + "[0-9]" + " " + "[A-Z]" + "[A-Z]";

    // validate if the given e-mail is a valid e-mail
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validate if the given postalcode is a valid postalcode
    public static boolean validatePostalcode(String postalcode) {
        Pattern pattern = Pattern.compile(regexPostalcode);
        Matcher matcher = pattern.matcher(postalcode);
        return matcher.matches();
    }

    // validate if the given URL is a valid URL
    public static boolean validateURL(String url) {
        Pattern pattern = Pattern.compile(regexURL);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    // validate if the cerfiticate rating is valid (1-10)
    public static boolean validateRating(int rating) {
        if (rating < 1 || rating > 10) {
            return false;
        }
        return true;

    }

    // validate if the percentage given by user is valid. (1-100)
    public static boolean validatePercentage(double percentage) {
        if (percentage < 0 || percentage > 100) {
            return false;
        }
        return true;

    }

    // Verify if the date given by the user is a valid date
    public static boolean validateDate(int day, int month, int year) {
        int dateYear = Calendar.getInstance().get(Calendar.YEAR);
        if (day > 31 || day < 1) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (year > dateYear || year < 1900) {
            return false;
        }
        // check date for february
        if (month == 2) {
            if (leapYear(year)) {
                if (day > 29) {
                    return false;
                }
            } else {
                if (day > 28) {
                    return false;
                }
            }
        }
        // Month 1, 3, 5, 7, 8, 10, 12 = max 31 days
        // Month 4, 6, 9, 11 = max 30 days
        int[] days31 = { 1, 3, 5, 7, 8, 10, 12 };
        int[] days30 = { 4, 6, 9, 11 };
        if (Arrays.stream(days31).anyMatch(i -> i == month)) {
            if (day > 31) {
                return false;
            }
        }
        if (Arrays.stream(days30).anyMatch(i -> i == month)) {
            if (day > 30) {
                return false;
            }
        }
        return true;
    }

    // validate if given year is a leap year to validate the date
    public static boolean leapYear(int year) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            } else {
                return false;
            }
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
}