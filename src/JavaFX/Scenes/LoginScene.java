package JavaFX.Scenes;


import JavaFX.Controller.MainSceneController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class LoginScene
{
    private Stage stage;
    public LoginScene(Stage stage)
    {
        this.stage = stage;
    }
    public void createLoginScene()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 475, 250));
            stage.show();
            root.getStylesheets().add("JavaFX/css/layout.css");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
