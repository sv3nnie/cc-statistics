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

import application.controllers.StudentDatabase;
import application.controllers.UIController;

public class ViewStudentUI implements IUI {

    // connection to the required databases for this class
    private StudentDatabase studentDatabase = new StudentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");

        Text textStudents = new Text("Student");
        ComboBox<String> studentsComboBox = new ComboBox<String>();
        ArrayList<String> students = studentDatabase.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        Label output = new Label();
        Button delete = new Button("Delete Student");
        Button back = new Button("Back");
        Button addCertificate = new Button("Add Certificate");
        Text textName = new Text("Name");
        Text textDoB = new Text("Date of Birth");
        Text textGender = new Text("Gender");
        Text textCity = new Text("City");
        Text textAddress = new Text("Address");
        Text textCountry = new Text("Country");
        Text textPostalCode = new Text("Postalcode");
        Text allCertificates = new Text("");
        TextField fieldName = new TextField();
        TextField fieldDoB = new TextField();
        TextField fieldGender = new TextField();
        TextField fieldCity = new TextField();
        TextField fieldAddress = new TextField();
        TextField fieldCountry = new TextField();
        TextField fieldPostalCode = new TextField();
        fieldName.setEditable(false);
        fieldDoB.setEditable(false);
        fieldGender.setEditable(false);
        fieldCity.setEditable(false);
        fieldAddress.setEditable(false);
        fieldCountry.setEditable(false);
        fieldPostalCode.setEditable(false);

        studentsComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            if (selected != "null") {
                allCertificates.setText(studentDatabase.getCertificates(selected));
                fieldName.setText(studentDatabase.getName(selected));
                fieldDoB.setText(studentDatabase.getInfo(selected, "DateOfBirth"));
                fieldGender.setText(studentDatabase.getInfo(selected, "Gender"));
                fieldCity.setText(studentDatabase.getInfo(selected, "City"));
                fieldAddress.setText(studentDatabase.getInfo(selected, "Address"));
                fieldCountry.setText(studentDatabase.getInfo(selected, "Country"));
                fieldPostalCode.setText(studentDatabase.getInfo(selected, "PostalCode"));
            }
        });

        back.setOnAction((event) -> controller.switchScene("studentmenu"));
        addCertificate.setOnAction((event) -> controller.switchScene("addcertificatemenu"));

        delete.setOnAction((event) -> {
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            if (selected != "null") {
                try {
                    studentDatabase.removeStudent("DELETE FROM Student WHERE EmailAddress = " + "\'" + selected + "\'");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                output.setText("Student has been deleted");
                System.out.println(selected);
                studentsComboBox.getItems().clear();
                ArrayList<String> newStudents = studentDatabase.getStudents();
                for (String student : newStudents) {
                    studentsComboBox.getItems().add(student);
                }
            } else {
                output.setText("No student has been selected");
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
        gridPane.add(fieldDoB, 1, 2);
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
        VBox vbox = new VBox();
        Text textCertificates = new Text("Certificates");
        addCertificate.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addCertificate.setMaxWidth(200);
        vbox.getChildren().add(textCertificates);
        vbox.getChildren().add(allCertificates);
        vbox.getChildren().add(addCertificate);
        vbox.getChildren().add(delete);
        vbox.getChildren().add(back);
        vbox.getChildren().add(output);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20, 10, 10, 10));
        // Styling nodes
        delete.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        delete.setMaxWidth(200);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
