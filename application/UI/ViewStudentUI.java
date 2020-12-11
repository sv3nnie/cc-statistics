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

import java.util.ArrayList;

import application.controllers.Database;
import application.controllers.UIController;
import application.entity.*;

public class ViewStudentUI implements IUI {

    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(400, 200);
        layout.setStyle("-fx-background-color: #EEF5FC;");

        Text textStudents = new Text("Student");
        ComboBox studentsComboBox = new ComboBox();
        ArrayList<String> students = Student.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        Label output = new Label();
        Button delete = new Button("Delete");
        Button back = new Button("Back");
        Text textName = new Text("Name");
        Text textDoB = new Text("Date of Birth");
        Text textGender = new Text("Gender");
        Text textCity = new Text("City");
        Text textAddress = new Text("Address");
        Text textCountry = new Text("Country");
        Text textPostalCode = new Text("Postalcode");

        TextField fieldName = new TextField();
        TextField fieldDay = new TextField();
        fieldDay.setPrefWidth(40);
        fieldDay.setMaxWidth(40);
        TextField fieldMonth = new TextField();
        fieldMonth.setPrefWidth(40);
        fieldMonth.setMaxWidth(40);
        TextField fieldYear = new TextField();
        fieldYear.setPrefWidth(60);
        fieldYear.setMaxWidth(60);
        TextField fieldGender = new TextField();
        TextField fieldCity = new TextField();
        TextField fieldAddress = new TextField();
        TextField fieldCountry = new TextField();
        TextField fieldPostalCode = new TextField();
        fieldName.setEditable(false);
        fieldDay.setEditable(false);
        fieldMonth.setEditable(false);
        fieldYear.setEditable(false);
        fieldGender.setEditable(false);
        fieldCity.setEditable(false);
        fieldAddress.setEditable(false);
        fieldCountry.setEditable(false);
        fieldPostalCode.setEditable(false);

        studentsComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            if (selected != null) {
                fieldName.setText(
                        Database.selectQuery("SELECT Name FROM Student WHERE EmailAddress = \'" + selected + "\'"));
                System.out.println(
                        Database.selectQuery("SELECT Name FROM Student WHERE EmailAddress = \'" + selected + "\'"));
                output.setText("Student has been deleted");
            }
        });

        back.setOnAction((event) -> controller.switchScene("studentmenu"));

        delete.setOnAction((event) -> {
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            if (selected != null) {
                Database.selectQuery("DELETE FROM Student WHERE EmailAddress = " + "\'" + selected + "\'");
                studentsComboBox.getItems().clear();
                ArrayList<String> newStudents = Student.getStudents();
                for (String student : newStudents) {
                    studentsComboBox.getItems().add(student);
                }
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textStudents, 0, 0);
        gridPane.add(studentsComboBox, 1, 0);
        gridPane.add(textName, 0, 1);
        gridPane.add(fieldName, 1, 1);
        gridPane.add(textDoB, 0, 2);
        gridPane.add(fieldDay, 1, 2);
        gridPane.add(fieldMonth, 1, 2);
        gridPane.add(fieldYear, 1, 2);
        gridPane.add(textGender, 0, 3);
        gridPane.add(fieldGender, 1, 3);
        gridPane.add(textCountry, 0, 4);
        gridPane.add(fieldCountry, 1, 4);
        gridPane.add(textCity, 0, 5);
        gridPane.add(fieldCity, 1, 5);
        gridPane.add(textAddress, 0, 6);
        gridPane.add(fieldAddress, 1, 6);
        gridPane.add(textPostalCode, 0, 7);
        gridPane.add(fieldPostalCode, 1, 7);
        VBox texts = new VBox();
        texts.getChildren().add(delete);
        texts.getChildren().add(back);
        texts.getChildren().add(output);
        texts.setAlignment(Pos.BASELINE_CENTER);
        texts.setSpacing(10);
        texts.setPadding(new Insets(0, 10, 10, 10));
        // Styling nodes
        delete.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        delete.setMaxWidth(200);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        GridPane.setMargin(fieldMonth, new Insets(0, 0, 0, 45));
        GridPane.setMargin(fieldYear, new Insets(0, 0, 0, 90));

        layout.setTop(gridPane);
        layout.setBottom(texts);

        Scene scene = new Scene(layout);
        return scene;
    }

}
