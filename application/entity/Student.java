package application.entity;

import java.time.LocalDate;

import application.utils.ValidationUtils;

public class Student {

    public static String addStudent(String email, String name, int dayDate, int monthDate, int yearDate, String gender,
            String country, String city, String address, String postalCode) {
        if (!ValidationUtils.validateEmail(email)) {
            return "Invalid e-mail";
        }
        if (!ValidationUtils.validateDate(dayDate, monthDate, yearDate)) {
            return "Invalid date";
        }
        if (!ValidationUtils.validatePostalcode(postalCode)) {
            return "Invalid postalcode (0000 AA)";
        }
        // set input date to valid local date
        // LocalDate date = LocalDate.of(dayDate, monthDate, dayDate);
        // add to database here
        return "Student has been added";

    }
}
