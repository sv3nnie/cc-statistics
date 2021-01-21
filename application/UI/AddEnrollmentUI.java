package application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import application.controllers.CourseDatabase;
import application.controllers.EnrollmentDatabase;
import application.controllers.StudentDatabase;
import application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddEnrollmentUI implements IUI {

    // connection to the required databases for this class
    private StudentDatabase studentDatabase = new StudentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseDatabase courseDatabase = new CourseDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentDatabase enrollmentDatabase = new EnrollmentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");
        ComboBox<String> studentsComboBox = new ComboBox<String>();
        ArrayList<String> students = studentDatabase.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        ComboBox<String> courseComboBox = new ComboBox<String>();
        ArrayList<String> courses = courseDatabase.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        Label output = new Label();
        Button back = new Button("Back");
        Text textCourse = new Text("Select Course");
        Text textStudent = new Text("Select Student");

        back.setOnAction((event) -> controller.switchScene("enrollmentmenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textCourse, 0, 0);
        gridPane.add(courseComboBox, 1, 0);
        gridPane.add(textStudent, 0, 1);
        gridPane.add(studentsComboBox, 1, 1);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        VBox vbox = new VBox();
        Button addEnrollment = new Button("Add Enrollment to Student");
        addEnrollment.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addEnrollment.setMaxWidth(200);
        addEnrollment.setOnAction((event) -> {
            String selectedStudent = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            System.out.println(selectedStudent);
            if (selectedStudent != "null" && selectedCourse != "null") {
                if (!enrollmentDatabase.checkDuplicate(selectedStudent, selectedCourse)) {
                    try {
                        enrollmentDatabase.addEnrollment(selectedStudent, selectedCourse);
                        output.setText("Succesfully added enrollment for " + selectedStudent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    output.setText("Student is already enrolled for the selected course");
                }
            } else {
                output.setText("Please select a course and student");
            }

        });
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        vbox.getChildren().add(addEnrollment);
        vbox.getChildren().add(back);
        vbox.getChildren().add(output);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
