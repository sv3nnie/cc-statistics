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

public class StudentMenuUI implements IUI {

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #EEF5FC;");
        layout.setMinSize(500, 150);
        Text title = new Text("CC-Statistics");
        Text subtitle = new Text("Student Management");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button addStudent = new Button("Add new student");
        Button viewStudent = new Button("View student");
        Button manageStudentProgession = new Button("Manage student progression");
        Button viewStudentProgression = new Button("View student progression");
        addStudent.setMinWidth(200);
        addStudent.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        viewStudent.setMinWidth(200);
        viewStudent.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        manageStudentProgession.setMinWidth(200);
        manageStudentProgession.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        viewStudentProgression.setMinWidth(200);
        viewStudentProgression.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addStudent.setOnAction((event) -> controller.switchScene("addstudentmenu"));
        viewStudent.setOnAction((event) -> controller.switchScene("viewstudentmenu"));
        manageStudentProgession.setOnAction((event) -> controller.switchScene("managestudentprogressionmenu"));
        viewStudentProgression.setOnAction((event) -> controller.switchScene("viewstudentprogressionmenu"));
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setOnAction((event) -> controller.switchScene("main"));
        bottom.getChildren().add(back);

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.setSpacing(10);

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(addStudent, 0, 1);
        gridPane.add(viewStudent, 1, 1);
        gridPane.add(manageStudentProgession, 0, 2);
        gridPane.add(viewStudentProgression, 1, 2);

        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}