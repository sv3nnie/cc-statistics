package application.UI;

import application.controllers.UIController;
import javafx.scene.Scene;

//interface for the getUI method used in every UI class
public interface IUI {

    public Scene getUI(UIController controller);
}
