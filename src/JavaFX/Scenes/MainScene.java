package JavaFX.Scenes;

import JavaFX.Controller.MainSceneController;
import Project.Person.Agent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainScene
{
    private Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    public MainScene()
    {
        stage = new Stage();
    }
    public void createMainStage(Agent agent)
    {

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
            Parent root = (Parent) loader.load();
            MainSceneController controller = loader.getController();
            controller.setAgent(agent);
            controller.setSelectedAgentLabelText("Agent: " + agent.getName());
            controller.initialize();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 1920, 1080));
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
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}
