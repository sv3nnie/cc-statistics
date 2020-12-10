package application.controllers;

import application.UI.MainUI;
import application.UI.StudentUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class UIController extends Application {

    private Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        MainUI mainUI = new MainUI();

        stage.setScene(mainUI.getUI(this));
        stage.show();
    }

    public void switchScene(String scene) {
        switch (scene) {
            case "main":
                stage.setScene((new MainUI().getUI(this)));
                break;
            case "student":
                stage.setScene((new StudentUI().getUI(this)));
                break;

            default:
                break;
        }
    }

}
