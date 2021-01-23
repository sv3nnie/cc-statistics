package application.controllers;

import application.UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class UIController extends Application {

    private Stage stage = null;

    // start the application and show the mainUI
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        MainUI mainUI = new MainUI();
        stage.setTitle(
                "Sven Colijn (2168497) | Lenty Sprangers (2168025) | Tieu Verhoeven (studentnummer) | Pim van Gool (studentnummer)");
        stage.setScene(mainUI.getUI(this));
        stage.show();
    }

    // method to switch between all the different UI's
    public void switchScene(String scene) {
        switch (scene) {
            case "main":
                stage.setScene((new MainUI().getUI(this)));
                break;
            // course related UI's
            case "coursemenu":
                stage.setScene((new CourseMenuUI().getUI(this)));
                break;
            case "addcontenttocourse":
                stage.setScene((new AddContentToCourse().getUI(this)));
                break;
            case "newcoursemenu":
                stage.setScene((new NewCourseMenuUI().getUI(this)));
                break;
            case "relatedcoursemenu":
                stage.setScene((new RelatedCoursesMenuUI().getUI(this)));
                break;
            // student related UI's
            case "studentmenu":
                stage.setScene((new StudentMenuUI().getUI(this)));
                break;
            case "addstudentmenu":
                stage.setScene((new AddStudentUI().getUI(this)));
                break;
            case "viewstudentmenu":
                stage.setScene((new ViewStudentUI().getUI(this)));
                break;
            // enrollment related UI's
            case "enrollmentmenu":
                stage.setScene((new EnrollmentMenuUI().getUI(this)));
                break;
            case "addenrollment":
                stage.setScene((new AddEnrollmentUI().getUI(this)));
                break;
            // certificate related UI's
            case "certificatemenu":
                stage.setScene((new CertificateMenuUI().getUI(this)));
                break;
            case "addcertificatemenu":
                stage.setScene((new AddCertificateMenu().getUI(this)));
                break;
            default:
                break;
        }
    }

}
