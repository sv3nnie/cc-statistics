package application.UI;

import application.controllers.CertificateDatabase;
import application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CertificateMenuUI implements IUI {

    // connection to the required databases for this class
    private CertificateDatabase certificateDatabase = new CertificateDatabase(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #EEF5FC;");
        layout.setMinSize(500, 230);
        Text title = new Text("CC-Statistics");
        Text subtitle = new Text("Certificate Management");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button addCertificate = new Button("Add new certificate");
        addCertificate.setMinWidth(400);
        addCertificate.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        addCertificate.setOnAction((event) -> controller.switchScene("addcertificatemenu"));
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        Text textTop3 = new Text("Top 3 given certificates:");
        Text textTop3Result = new Text("");
        textTop3Result.setText(certificateDatabase.getTop3());
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #191923; -fx-text-fill: white;");
        back.setOnAction((event) -> controller.switchScene("main"));
        bottom.getChildren().add(addCertificate);
        bottom.getChildren().add(back);
        bottom.getChildren().add(textTop3);
        bottom.getChildren().add(textTop3Result);
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.setSpacing(10);

        layout.setTop(vBox);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}