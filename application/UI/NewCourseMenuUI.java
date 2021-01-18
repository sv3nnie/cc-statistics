package application.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

import application.controllers.ContentDatabase;
import application.controllers.CourseDatabase;
import application.controllers.UIController;

public class NewCourseMenuUI implements IUI {
    private ContentDatabase contentDatabase = new ContentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseDatabase courseDatabase = new CourseDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");

        Text textCourseName = new Text("Course Name");
        Text textSubject = new Text("Subject");
        Text textDifficulty = new Text("Difficulty");
        Text textIntroduction = new Text("Introduction Text");
        Text textModule = new Text("Select First Module");

        TextField fieldCourseName = new TextField();
        TextField fieldSubject = new TextField();
        ComboBox difficulty = new ComboBox();
        ArrayList<String> difficulties = new ArrayList<>();
        difficulties.add("Easy");
        difficulties.add("Medium");
        difficulties.add("Hard");
        for (String difficulty1 : difficulties) {
            difficulty.getItems().add(difficulty1);
        }
        TextField fieldIntroduction = new TextField();
        ComboBox modules = new ComboBox();
        ArrayList<String> modulelist = contentDatabase.getAvailableModules();
        for (String module : modulelist) {
            modules.getItems().add(module);
        }
        fieldCourseName.setPromptText("Course Name");
        fieldSubject.setPromptText("Subject");
        fieldIntroduction.setPromptText("Introduction Text");
        Label output = new Label();
        Button submit = new Button("Submit");
        Button back = new Button("Back");

        submit.setOnAction((event) -> {
            String selectedDifficulty = String.valueOf(difficulty.getSelectionModel().getSelectedItem());
            String selectedModule = String.valueOf(modules.getSelectionModel().getSelectedItem());
            System.out.println(selectedDifficulty);
            if (fieldCourseName.getText().isEmpty() || fieldSubject.getText().isEmpty()
                    || fieldIntroduction.getText().isEmpty() || selectedDifficulty == "null"
                    || selectedModule == "null") {
                output.setText("Verify all fields");
            } else {
                if (!courseDatabase.checkDuplicate(fieldCourseName.getText())) {
                    try {
                        courseDatabase.addCourse(fieldCourseName.getText(), fieldSubject.getText(), selectedDifficulty,
                                fieldIntroduction.getText());
                        contentDatabase.editModule(selectedModule, fieldCourseName.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    output.setText("Succesfully added new course");
                }
            }
        });

        back.setOnAction((event) -> controller.switchScene("coursemenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textCourseName, 0, 0);
        gridPane.add(fieldCourseName, 1, 0);
        gridPane.add(textSubject, 0, 1);
        gridPane.add(fieldSubject, 1, 1);
        gridPane.add(textDifficulty, 0, 2);
        gridPane.add(difficulty, 1, 2);
        gridPane.add(textIntroduction, 0, 3);
        gridPane.add(fieldIntroduction, 1, 3);
        gridPane.add(textModule, 0, 4);
        gridPane.add(modules, 1, 4);
        VBox texts = new VBox();
        texts.getChildren().add(submit);
        texts.getChildren().add(back);
        texts.getChildren().add(output);
        texts.setAlignment(Pos.BASELINE_CENTER);
        texts.setSpacing(10);
        texts.setPadding(new Insets(0, 10, 10, 10));
        // Styling nodes
        submit.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        submit.setMaxWidth(200);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        layout.setBottom(texts);

        Scene scene = new Scene(layout);
        return scene;
    }

}
