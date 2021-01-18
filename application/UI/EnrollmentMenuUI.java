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

public class EnrollmentMenuUI implements IUI {

    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #EEF5FC;");
        layout.setMinSize(500, 150);
        Text title = new Text("CC-Statistics");
        Text subtitle = new Text("Enrollment Management");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button addEnrollment = new Button("Add new enrollment");
        Button removeEnrollment = new Button("Remove enrollment");
        addEnrollment.setMinWidth(200);
        addEnrollment.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        removeEnrollment.setMinWidth(200);
        removeEnrollment.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addEnrollment.setOnAction((event) -> controller.switchScene("addenrollment"));
        removeEnrollment.setOnAction((event) -> controller.switchScene("removeenrollment"));
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setOnAction((event) -> controller.switchScene("main"));
        bottom.getChildren().add(back);

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 0, 10));
        bottom.setPadding(new Insets(0, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(addEnrollment, 0, 1);
        gridPane.add(removeEnrollment, 1, 1);
        
        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}