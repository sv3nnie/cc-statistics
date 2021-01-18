package application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import application.controllers.CourseDatabase;
import application.controllers.ContentDatabase;
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

public class AddContentToCourse implements IUI {
    private CourseDatabase courseDatabase = new CourseDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private ContentDatabase contentDatabase = new ContentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");
        ComboBox courseComboBox = new ComboBox();
        ArrayList<String> courses = courseDatabase.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        ComboBox contentComboBox = new ComboBox();
        ArrayList<String> contents = contentDatabase.getAvailableContent();
        for (String content : contents) {
            contentComboBox.getItems().add(content);
        }
        Label output = new Label();
        Button back = new Button("Back");
        Text textCourse = new Text("Select Course");
        Text textContent = new Text("Select Content");

        back.setOnAction((event) -> controller.switchScene("coursemenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textCourse, 0, 0);
        gridPane.add(courseComboBox, 1, 0);
        gridPane.add(textContent, 0, 1);
        gridPane.add(contentComboBox, 1, 1);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        VBox vbox = new VBox();
        Button addEnrollment = new Button("Add Enrollment to Student");
        addEnrollment.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addEnrollment.setMaxWidth(200);
        addEnrollment.setOnAction((event) -> {
            String selectedContent = String.valueOf(contentComboBox.getSelectionModel().getSelectedItem());
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            if (selectedContent != "null" && selectedCourse != "null") {
                // set coursename in content to coursename or module coursename to coursename
                // here
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
