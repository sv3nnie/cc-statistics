package application.UI;

import java.text.DecimalFormat;
import java.util.ArrayList;

import application.controllers.CourseDatabase;
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

public class AverageProgressionUI implements IUI {

    // connection to the required databases for this class
    private CourseDatabase courseDatabase = new CourseDatabase(
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
        Label output = new Label();
        Button back = new Button("Back");
        Text textCourse = new Text("Select Course");
        Text textRelated = new Text("Average Progression");

        back.setOnAction((event) -> controller.switchScene("coursemenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textCourse, 0, 0);
        gridPane.add(courseComboBox, 1, 0);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        output.setPrefHeight(50);
        layout.setTop(gridPane);
        VBox vbox = new VBox();
        courseComboBox.setOnAction((event) -> {
            String selected = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            String coursesoutput = "";
            if (selected != "null") {
                ArrayList<String> contentnames = courseDatabase.getContent(selected);
                ArrayList<Double> progression = courseDatabase
                        .getAverageProgress(courseDatabase.getContentIds(selected));
                DecimalFormat format = new DecimalFormat("#0.0");
                for (int i = 0; i < contentnames.size(); i++) {
                    coursesoutput = coursesoutput + contentnames.get(i) + " | " + "Average progression: "
                            + format.format(progression.get(i)) + "\n";
                }
                output.setText(coursesoutput);
            }
        });
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        vbox.getChildren().add(textRelated);
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
