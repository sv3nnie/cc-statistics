package application.controllers;

import application.UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class UIController extends Application {

    private Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        MainUI mainUI = new MainUI();
        stage.setTitle(
                "Sven Colijn (2168497) | Lenty Sprangers (2168025) | Tieu Verhoeven (studentnummer) | Pim van Gool (studentnummer)");
        stage.setScene(mainUI.getUI(this));
        stage.show();
    }

    public void switchScene(String scene) {
        switch (scene) {
            case "main":
                stage.setScene((new MainUI().getUI(this)));
                break;
            case "studentmenu":
                stage.setScene((new StudentMenuUI().getUI(this)));
                break;
            case "addstudent":
                stage.setScene((new AddStudentUI().getUI(this)));
                break;
            case "viewstudent":
                stage.setScene((new ViewStudentUI().getUI(this)));
                break;
            default:
                break;
        }
    }

}
