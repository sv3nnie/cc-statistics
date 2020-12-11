package application.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import application.utils.ValidationUtils;
import application.controllers.Database;

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
        LocalDate date = LocalDate.of(yearDate, monthDate, dayDate);
        // Build query for database
        String query = "INSERT INTO Student(EmailAddress, Name, DateOfBirth, Gender, City, Address, Country, PostalCode) VALUES (\'"
                + email + "\',\'" + name + "\',\'" + date + "\',\'" + gender + "\',\'" + city + "\',\'" + address
                + "\',\'" + country + "\',\'" + postalCode + "\')";
        // Check if student already exists in database (scuffed way)
        if (Database.checkQuery("SELECT EmailAddress FROM Student WHERE EmailAddress = \'" + email + "\'")) {
            return "A student already exists with this e-mailaddress";
        }
        // Add to database
        Database.insertQuery(query);
        return "Student has been added";
    }

    public static ArrayList getStudents() {
        return Database.selectQueryArrayList("SELECT * FROM Student");
    }
}
