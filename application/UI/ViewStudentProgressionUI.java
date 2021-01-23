package application.UI;

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

public class ViewStudentProgressionUI implements IUI {

    // connection to the required databases for this class
    private StudentDatabase studentDatabase = new StudentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentDatabase enrollmentDatabase = new EnrollmentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseDatabase courseDatabase = new CourseDatabase(
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
        Label output = new Label();
        Button back = new Button("Back");
        Text textStudent = new Text("Select Student");
        Text textEnrollment = new Text("Select Course");

        back.setOnAction((event) -> controller.switchScene("studentmenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textStudent, 0, 0);
        gridPane.add(studentsComboBox, 1, 0);
        gridPane.add(textEnrollment, 0, 1);
        gridPane.add(courseComboBox, 1, 1);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);

        studentsComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            courseComboBox.getSelectionModel().clearSelection();
            courseComboBox.getItems().clear();
            output.setText("");
            if (selected != "null") {
                ArrayList<String> courses = enrollmentDatabase.getAvailableCertificates(selected);
                courses = enrollmentDatabase.getAvailableCertificates(selected);
                for (String course : courses) {
                    courseComboBox.getItems().add(course);
                }
            }
        });
        courseComboBox.setOnAction((event) -> {
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            String selectedStudent = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            String end = "";
            if (selectedCourse != "null" && selectedStudent != "null") {
                ArrayList<Integer> results = courseDatabase.getContentIds(selectedCourse);
                ArrayList<String> contents = courseDatabase.getContent(selectedCourse);
                ArrayList<Double> progress = new ArrayList<>();
                for (int result : results) {
                    progress.add(courseDatabase.getProgress(result, selectedStudent));
                }
                for (int i = 0; i < results.size(); i++) {
                    end = end + contents.get(i) + " | Progress (%): " + progress.get(i) + "\n";
                }
                output.setText(end);
            }
        });
        VBox vbox = new VBox();
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        output.setPrefHeight(100);
        vbox.getChildren().add(output);
        vbox.getChildren().add(back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
