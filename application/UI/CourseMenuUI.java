package application.UI;

import application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CourseMenuUI implements IUI {

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
        Button newModule = new Button("New Module");
        Button newContent = new Button("New Content");
        Button newCourse = new Button("New Course");
        Button editCourse = new Button("Add Content To Existing Course");
        newCourse.setMinWidth(200);
        newCourse.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        newModule.setMinWidth(200);
        newModule.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        newContent.setMinWidth(200);
        newContent.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        editCourse.setMinWidth(200);
        editCourse.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");

        newModule.setOnAction((event) -> controller.switchScene("newmodulemenu"));
        newContent.setOnAction((event) -> controller.switchScene("newcontentmenu"));
        newCourse.setOnAction((event) -> controller.switchScene("newcoursemenu"));
        editCourse.setOnAction((event) -> controller.switchScene("addcontenttocourse"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(newModule, 0, 1);
        gridPane.add(newContent, 1, 1);
        gridPane.add(newCourse, 0, 2);
        gridPane.add(editCourse, 1, 2);
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setOnAction((event) -> controller.switchScene("main"));
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.getChildren().add(back);

        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}
