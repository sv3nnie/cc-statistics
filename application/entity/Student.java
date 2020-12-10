package application.entity;

import java.time.LocalDate;

import application.logic.*;

public class Student {

    public static String addStudent(String email, String name, int dayDate, int monthDate, int yearDate, String gender,
            String country, String city, String address, String postalCode) {
        if (!Validation.validateEmail(email)) {
            return "Invalid e-mail";
        }
        if (!Validation.validateDate(dayDate, monthDate, yearDate)) {
            return "Invalid date";
        }
        if (!Validation.validatePostalcode(postalCode)) {
            return "Invalid postalcode (0000 AA)";
        }
        // set input date to valid local date
        // LocalDate date = LocalDate.of(dayDate, monthDate, dayDate);
        // add to database here
        return "Student has been added";

    }

    public static void main(String[] args) {
        System.out.println(
                addStudent("g@g.com", "Sven", 11, 11, 2011, "Male", "Breda", "Kappa 123", "Nederland", "3000 PX"));
    }

}
