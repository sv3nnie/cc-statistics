package application.entity;

import java.sql.SQLException;
import java.time.LocalDate;

import application.utils.ValidationUtils;
import application.controllers.StudentDatabase;

public class Student {

    private static StudentDatabase studentDatabase = new StudentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // add a new student to the database including all the needed validations
    public static String addStudent(String email, String name, int dayDate, int monthDate, int yearDate, String gender,
            String country, String city, String address, String postalCode) throws SQLException {
        if (!ValidationUtils.validateEmail(email)) {
            return "Invalid e-mail";
        }
        if (!ValidationUtils.validateDate(dayDate, monthDate, yearDate)) {
            return "Invalid date";
        }
        if (!ValidationUtils.validatePostalCode(postalCode)) {
            return "Invalid postalcode (1000 AA)";
        }
        // set input date to valid local date
        LocalDate date = LocalDate.of(yearDate, monthDate, dayDate);
        // Build query for database
        String query = "INSERT INTO Student(EmailAddress, Name, DateOfBirth, Gender, City, Address, Country, PostalCode) VALUES (\'"
                + email + "\',\'" + name + "\',\'" + date + "\',\'" + gender + "\',\'" + city + "\',\'" + address
                + "\',\'" + country + "\',\'" + postalCode + "\')";
        // Check if student already exists in database (scuffed way)
        if (studentDatabase.checkDuplicate(email)) {
            return "A student already exists with this e-mailaddress";
        }
        // Add to database
        studentDatabase.addStudent(query);
        return "Student has been added";
    }

}
