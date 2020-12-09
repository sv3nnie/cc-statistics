// import java.time.LocalDate;
// import java.util.Calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // Regex to verify valid e-mails
    private static final String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n"
            + ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    // Regex to verify valid URLs
    private static final String regexURL = "https?://" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{1,256}\\.[a-z]"
            + "{1,256}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)" + "[a-zA-Z0-9@:%._\\+~#?&//=]";

    public static void main(String[] args) {
        System.out.println(validateURL("http://g."));
        System.out.println(validateURL("http://g.g"));
        System.out.println(validateURL("http://g.g.g"));
    }

    // validate if the given e-mail is a valid e-mail
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
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
    // public static LocalDate verifyDate(int day, int month, int year) {
    // int dateYear = Calendar.getInstance().get(Calendar.YEAR);
    // LocalDate date = LocalDate.of(year, month, day);
    // return date;
    // }
}
