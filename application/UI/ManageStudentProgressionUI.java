package application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import application.controllers.CourseDatabase;
import application.controllers.EnrollmentDatabase;
import application.controllers.StudentDatabase;
import application.controllers.UIController;
import application.utils.ValidationUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ManageStudentProgressionUI implements IUI {

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
        ComboBox<String> modulesComboBox = new ComboBox<String>();
        Label output = new Label();
        Button back = new Button("Back");
        Text textStudent = new Text("Select Student");
        Text textEnrollment = new Text("Select Course");
        Text textModule = new Text("Select Module");
        Text textProgression = new Text("Progression (in %)");
        TextField fieldProgression = new TextField();

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
        gridPane.add(textModule, 0, 2);
        gridPane.add(modulesComboBox, 1, 2);
        gridPane.add(textProgression, 0, 3);
        gridPane.add(fieldProgression, 1, 3);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);

        studentsComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            courseComboBox.getSelectionModel().clearSelection();
            courseComboBox.getItems().clear();
            if (selected != "null") {
                ArrayList<String> courses = enrollmentDatabase.getAvailableCertificates(selected);
                courses = enrollmentDatabase.getAvailableCertificates(selected);
                for (String course : courses) {
                    courseComboBox.getItems().add(course);
                }
            }
        });
        courseComboBox.setOnAction((event) -> {
            String selected = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            modulesComboBox.getSelectionModel().clearSelection();
            modulesComboBox.getItems().clear();
            if (selected != "null") {
                ArrayList<String> modules = courseDatabase.getContent(selected);
                for (String module : modules) {
                    modulesComboBox.getItems().add(module);
                }
            }
        });
        VBox vbox = new VBox();
        Button setProgression = new Button("Set Progression");
        setProgression.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        setProgression.setMaxWidth(200);
        setProgression.setOnAction((event) -> {
            String selectedStudent = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            String selectedContent = String.valueOf(modulesComboBox.getSelectionModel().getSelectedItem());
            if (selectedStudent != "null" && selectedCourse != "null" && selectedContent != "null") {
                double percentage = Double.valueOf(fieldProgression.getText());
                if (ValidationUtils.validatePercentage(percentage)) {
                    String type[] = selectedContent.split("] ");
                    int id = 0;
                    if (type[0].contains("Webcast")) {
                        id = courseDatabase.getContentId(type[1], "Webcast");
                    } else {
                        id = courseDatabase.getContentId(type[1], "Module");
                    }
                    try {
                        courseDatabase.editProgress(selectedStudent, id, percentage);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    output.setText("Succesfully editted progress percentage for selected content");
                } else {
                    output.setText("Please enter a valid percentage");
                }
            } else {
                output.setText("Please verify all fields");
            }

        });
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        vbox.getChildren().add(output);
        vbox.getChildren().add(setProgression);
        vbox.getChildren().add(back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
