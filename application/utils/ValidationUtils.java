package application.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    // Regex to validate valid e-mails
    private static final String regexEmail = "^[a-zA-Z0-9_.!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n"
            + ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    // Regex to validate valid URLs
    private static final String regexURL = "https?://" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{1,256}\\.[a-z]"
            + "{1,256}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)" + "[a-zA-Z0-9@:%._\\+~#?&//=]";
    // Regex to validate postal codes
    private static final String regexPostalCode = "[1-9][0-9]{3}[A-Z]{2}";

    // validate if the given e-mail is a valid e-mail
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validate if the given postalcode is a valid postalcode
    public static boolean validatePostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(regexPostalCode);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    // Format postalCode input.
    // Throws nullPointerException when input is empty.
    // Throws illegalArgumentException when input is invalid.
    public static String formatPostalCode(String postalCode) {
        if (postalCode.equals("")) {
            throw new NullPointerException("Parameter postalCode was empty");
        }
        postalCode = postalCode.replace(" ", "").toUpperCase();
        Pattern pattern = Pattern.compile(regexPostalCode);
        Matcher matcher = pattern.matcher(postalCode);
        if (matcher.matches()) {
            return new StringBuilder(postalCode).insert(4, " ").toString();
        }
        throw new IllegalArgumentException("PostalCode was invalid: " + postalCode);
    }


    // validate if the given URL is a valid URL
    public static boolean validateURL(String url) {
        Pattern pattern = Pattern.compile(regexURL);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    // validate if the certificate rating is valid (1-10)
    public static boolean validateRating(double rating) {
        return rating >= 1 && rating <= 10;

    }

    // validate if the percentage given by user is valid. (1-100)
    public static boolean validatePercentage(double percentage) {
        return percentage >= 1 && percentage <= 100;
    }

    // Verify if the date given by the user is a valid date
    public static boolean validateDate(int day, int month, int year) {

        if (day > 31 || day < 1 || month < 1 || month > 12 || year < 0) {
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
        //we now check if a month with 30 days might have over 30 days which is false
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (day > 30) {
                    return false;
                }
                break;
            //if day > 31 is always false because of previous check in method
        }
        return true;
    }


    // validate if given year is a leap year to validate the date
    public static boolean leapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        return year % 4 == 0;
    }
}
