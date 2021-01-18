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
        Button newContent = new Button("New Content");
        Button editContent = new Button("Edit Content");
        Button newModule = new Button("New Module");
        Button editModule = new Button("Edit Module");
        newContent.setMinWidth(200);
        newContent.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        editContent.setMinWidth(200);
        editContent.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        newModule.setMinWidth(200);
        newModule.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        editModule.setMinWidth(200);
        editModule.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");

        newContent.setOnAction((event) -> controller.switchScene("newcontentmenu"));
        editContent.setOnAction((event) -> controller.switchScene("editcontentmenu"));
        newModule.setOnAction((event) -> controller.switchScene("newmodulemenu"));
        editModule.setOnAction((event) -> controller.switchScene("editmodulemenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(newContent, 0, 1);
        gridPane.add(editContent, 1, 1);
        gridPane.add(newModule, 0, 2);
        gridPane.add(editModule, 1, 2);

        layout.setBottom(gridPane);
        layout.setTop(vBox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
