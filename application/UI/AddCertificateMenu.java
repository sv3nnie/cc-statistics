package application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import application.controllers.EnrollmentDatabase;
import application.controllers.StudentDatabase;
import application.controllers.UIController;
import application.utils.ValidationUtils;
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

public class AddCertificateMenu implements IUI {

    // connection to the required databases for this class
    private StudentDatabase studentDatabase = new StudentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentDatabase enrollmentDatabase = new EnrollmentDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 150);
        layout.setStyle("-fx-background-color: #EEF5FC;");
        ComboBox<String> studentsComboBox = new ComboBox<String>();
        ArrayList<String> students = studentDatabase.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        ComboBox<String> enrollmentComboBox = new ComboBox<String>();
        Label output = new Label();
        Button back = new Button("Back");
        Text textStudent = new Text("Select Student");
        Text textEnrollment = new Text("Select Enrollment");
        Text textGrade = new Text("Grade");
        TextField fieldGrade = new TextField();
        Text textEmployee = new Text("Employee");
        TextField fieldEmployee = new TextField();

        back.setOnAction((event) -> controller.switchScene("certificatemenu"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textStudent, 0, 0);
        gridPane.add(studentsComboBox, 1, 0);
        gridPane.add(textEnrollment, 0, 1);
        gridPane.add(enrollmentComboBox, 1, 1);
        gridPane.add(textGrade, 0, 2);
        gridPane.add(fieldGrade, 1, 2);
        gridPane.add(textEmployee, 0, 3);
        gridPane.add(fieldEmployee, 1, 3);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);

        studentsComboBox.setOnAction((event) -> {
            ArrayList<String> enrollments = new ArrayList<>();
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            enrollmentComboBox.getSelectionModel().clearSelection();
            enrollmentComboBox.getItems().clear();
            if (selected != "null") {
                enrollments = enrollmentDatabase.getAvailableCertificates(selected);
                for (String enrollment : enrollments) {
                    enrollmentComboBox.getItems().add(enrollment);
                }
            }
        });
        VBox vbox = new VBox();
        Button addCertificate = new Button("Add Certificate to Student");
        addCertificate.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addCertificate.setMaxWidth(200);
        addCertificate.setOnAction((event) -> {
            String selectedStudent = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            String selectedEnrollment = String.valueOf(enrollmentComboBox.getSelectionModel().getSelectedItem());
            if (selectedStudent != "null" && selectedEnrollment != "null" && fieldEmployee.getText().length() > 0
                    && fieldGrade.getText().length() > 0
                    && ValidationUtils.validateRating(Double.valueOf(fieldGrade.getText()))) {
                try {
                    enrollmentDatabase.addCertificate(selectedStudent, selectedEnrollment, fieldEmployee.getText(),
                            Double.valueOf(fieldGrade.getText()));
                    output.setText("Certificate has been added to " + selectedStudent);
                    enrollmentComboBox.getSelectionModel().clearSelection();
                    enrollmentComboBox.getItems().clear();
                    studentsComboBox.getSelectionModel().clearSelection();
                } catch (NumberFormatException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                output.setText("Please verify all fields");
            }

        });
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setMaxWidth(200);
        vbox.getChildren().add(addCertificate);
        vbox.getChildren().add(back);
        vbox.getChildren().add(output);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
