package application.UI;

import application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainUI implements IUI {

    public Scene getUI(UIController controller) {
        Button student = new Button("New Student");

        student.setOnAction((event) -> controller.switchScene("student"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(student, 0, 0);
        // Styling nodes
        student.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        student.setMaxWidth(200);

        Scene scene = new Scene(gridPane);
        return scene;
    }

}
