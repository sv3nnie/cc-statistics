package application.UI;

import java.util.ArrayList;

import application.controllers.UIController;
import application.controllers.WebcastDatabase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CourseMenuUI implements IUI {

    // connection to the required databases for this class
    private WebcastDatabase webcastDatabase = new WebcastDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #EEF5FC;");
        layout.setMinSize(500, 150);
        Text title = new Text("CC-Statistics");
        Text subtitle = new Text("Course Management");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button newCourse = new Button("New Course");
        Button editCourse = new Button("Add Content To Existing Course");
        newCourse.setMinWidth(200);
        newCourse.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        editCourse.setMinWidth(200);
        editCourse.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        Button relatedCourses = new Button("View related courses");
        relatedCourses.setMinWidth(200);
        relatedCourses.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        Button averageProgression = new Button("Average progression per course");
        averageProgression.setMinWidth(200);
        averageProgression.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");

        newCourse.setOnAction((event) -> controller.switchScene("newcoursemenu"));
        editCourse.setOnAction((event) -> controller.switchScene("addcontenttocourse"));
        relatedCourses.setOnAction((event) -> controller.switchScene("relatedcoursemenu"));
        averageProgression.setOnAction((event) -> controller.switchScene("averageprogressionmenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(newCourse, 0, 1);
        gridPane.add(editCourse, 1, 1);
        gridPane.add(relatedCourses, 0, 2);
        gridPane.add(averageProgression, 1, 2);
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setOnAction((event) -> controller.switchScene("main"));
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.getChildren().add(back);
        Label top3Webcasts = new Label();
        ArrayList<String> casts = webcastDatabase.getWebcastViewCount();
        String webcasts = "";
        Label webcastsText = new Label("Top 3 webcasts:");
        webcastsText.setPadding(new Insets(10, 0, 0, 0));
        for (String webcast : casts) {
            webcasts = webcasts + webcast + "\n";
        }
        top3Webcasts.setText(webcasts);
        bottom.getChildren().add(webcastsText);
        bottom.getChildren().add(top3Webcasts);
        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}
