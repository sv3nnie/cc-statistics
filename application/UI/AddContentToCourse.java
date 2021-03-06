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

    // connection to the required databases for this class
    private CourseDatabase courseDatabase = new CourseDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private ContentDatabase contentDatabase = new ContentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");
        ComboBox<String> courseComboBox = new ComboBox<String>();
        ArrayList<String> courses = courseDatabase.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        ComboBox<String> contentComboBox = new ComboBox<String>();
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
        Button addContent = new Button("Add Content to Course");
        addContent.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addContent.setMaxWidth(200);
        addContent.setOnAction((event) -> {
            String selectedContent = String.valueOf(contentComboBox.getSelectionModel().getSelectedItem());
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            if (selectedContent != "null" && selectedCourse != "null") {
                try {
                    contentDatabase.editModule(selectedContent, selectedCourse);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                output.setText("Please select a course and content");
            }
        });
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        vbox.getChildren().add(addContent);
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
