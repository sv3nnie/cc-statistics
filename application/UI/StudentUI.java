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
import application.controllers.UIController;
import application.entity.*;
import application.logic.Gender;

public class StudentUI implements IUI {

    public Scene getUI(UIController uiController) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(400, 200);
        layout.setStyle("-fx-background-color: #EEF5FC;");

        Text textEmail = new Text("Email");
        Text textName = new Text("Name");
        Text textDoB = new Text("Date of Birth");
        Text textGender = new Text("Gender");
        Text textCity = new Text("City");
        Text textAddress = new Text("Address");
        Text textCountry = new Text("Country");
        Text textPostalCode = new Text("Postalcode");

        TextField fieldEmail = new TextField();
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
        ComboBox genders = new ComboBox();
        genders.getItems().addAll(Gender.values());

        TextField fieldCity = new TextField();
        TextField fieldAddress = new TextField();
        TextField fieldCountry = new TextField();
        TextField fieldPostalCode = new TextField();
        fieldEmail.setPromptText("Email address");
        fieldName.setPromptText("Name");
        fieldDay.setPromptText("Day");
        fieldMonth.setPromptText("Month");
        fieldYear.setPromptText("Year");
        fieldCity.setPromptText("City");
        fieldAddress.setPromptText("Address");
        fieldCountry.setPromptText("Country");
        fieldPostalCode.setPromptText("Postalcode");
        Label output = new Label();
        Button submit = new Button("Submit");

        submit.setOnAction((event) -> {
            if (fieldEmail.getText().isEmpty() || fieldName.getText().isEmpty() || fieldDay.getText().isEmpty()
                    || fieldMonth.getText().isEmpty() || fieldYear.getText().isEmpty()
                    || genders.getSelectionModel().isEmpty() || fieldCountry.getText().isEmpty()
                    || fieldCity.getText().isEmpty() || fieldAddress.getText().isEmpty()
                    || fieldPostalCode.getText().isEmpty()) {
                output.setText("Verify all fields");
            } else {
                output.setText(Student.addStudent(fieldEmail.getText(), fieldName.getText(),
                        Integer.valueOf(fieldDay.getText()), Integer.valueOf(fieldMonth.getText()),
                        Integer.valueOf(fieldYear.getText()), String.valueOf(genders.getValue()),
                        fieldCountry.getText(), fieldCity.getText(), fieldAddress.getText(),
                        fieldPostalCode.getText()));
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textEmail, 0, 0);
        gridPane.add(fieldEmail, 1, 0);
        gridPane.add(textName, 0, 1);
        gridPane.add(fieldName, 1, 1);
        gridPane.add(textDoB, 0, 2);
        gridPane.add(fieldDay, 1, 2);
        gridPane.add(fieldMonth, 1, 2);
        gridPane.add(fieldYear, 1, 2);
        gridPane.add(textGender, 0, 3);
        gridPane.add(genders, 1, 3);
        gridPane.add(textCountry, 0, 4);
        gridPane.add(fieldCountry, 1, 4);
        gridPane.add(textCity, 0, 5);
        gridPane.add(fieldCity, 1, 5);
        gridPane.add(textAddress, 0, 6);
        gridPane.add(fieldAddress, 1, 6);
        gridPane.add(textPostalCode, 0, 7);
        gridPane.add(fieldPostalCode, 1, 7);
        VBox texts = new VBox();
        texts.getChildren().add(submit);
        texts.getChildren().add(output);
        texts.setAlignment(Pos.BASELINE_CENTER);
        texts.setSpacing(10);
        texts.setPadding(new Insets(0, 10, 10, 10));
        // Styling nodes
        submit.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        submit.setMaxWidth(200);
        GridPane.setMargin(fieldMonth, new Insets(0, 0, 0, 45));
        GridPane.setMargin(fieldYear, new Insets(0, 0, 0, 90));

        layout.setTop(gridPane);
        layout.setBottom(texts);

        Scene scene = new Scene(layout);
        return scene;
    }

}
