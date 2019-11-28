package JavaFX.Scenes;

import JavaFX.Controller.CreateCompanyController;
import JavaFX.Controller.EditCompanyController;
import JavaFX.Controller.MainSceneController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class EditCompanyScene
{
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    private MainSceneController controller;
    public EditCompanyScene(MainSceneController controller)
    {
        stage = new Stage();
        this.controller = controller;
    }
    public void editCompanyScene(Event e)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditCompanyScene.fxml"));
        try
        {
            Parent root = (Parent) loader.load();
            EditCompanyController tempController = loader.getController();
            tempController.setMainScene(controller);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 450, 150));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) e.getSource()).getScene().getWindow());
            stage.show();
            root.getStylesheets().add("JavaFX/css/layout.css");
            root.setOnMousePressed(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
