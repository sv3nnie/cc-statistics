package application.UI;

import java.util.ArrayList;

import application.controllers.CourseDatabase;
import application.controllers.StudentDatabase;
import application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class AddEnrollmentUI implements IUI {

    private StudentDatabase studentDatabase = new StudentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseDatabase courseDatabase = new CourseDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");
        ComboBox studentsComboBox = new ComboBox();
        ArrayList<String> students = studentDatabase.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        ComboBox courseComboBox = new ComboBox();
        ArrayList<String> courses = courseDatabase.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        Label output = new Label();
        Button back = new Button("Back");
        Button addEnrollment = new Button("Add Enrollment");
        Text textCourse = new Text("Select Course");
        Text textStudent = new Text("Select Student");

        back.setOnAction((event) -> controller.switchScene("studentmenu"));
        addEnrollment.setOnAction((event) -> controller.switchScene("enrollmentmenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textCourse, 0, 0);
        gridPane.add(studentsComboBox, 1, 0);
        gridPane.add(textStudent, 0, 1);
        gridPane.add(courseComboBox, 1, 1);
        // Styling nodes
        //delete.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        //delete.setMaxWidth(200);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);

        Scene scene = new Scene(layout);
        return scene;
    }
    
}
