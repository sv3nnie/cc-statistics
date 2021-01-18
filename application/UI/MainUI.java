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

public class MainUI implements IUI {

    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #EEF5FC;");
        layout.setMinSize(500, 150);
        Text title = new Text("CC-Statistics");
        Text subtitle = new Text("Home");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button student = new Button("Student");
        Button course = new Button("Course");
        Button enrollment = new Button("Enrollment");
        Button certificate = new Button("Certificate");
        student.setMinWidth(200);
        student.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        course.setMinWidth(200);
        course.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        enrollment.setMinWidth(200);
        enrollment.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        certificate.setMinWidth(200);
        certificate.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");

        student.setOnAction((event) -> controller.switchScene("studentmenu"));
        course.setOnAction((event) -> controller.switchScene("coursemenu"));
        enrollment.setOnAction((event) -> controller.switchScene("enrollmentmenu"));
        certificate.setOnAction((event) -> controller.switchScene("certificatemenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(student, 0, 1);
        gridPane.add(course, 1, 1);
        gridPane.add(enrollment, 0, 2);
        gridPane.add(certificate, 1, 2);

        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button stats = new Button("Statistics");
        stats.setMinWidth(400);
        stats.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        stats.setOnAction((event) -> controller.switchScene("main"));
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.getChildren().add(stats);

        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}
