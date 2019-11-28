package JavaFX.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;


/*
TODO - Optimize StageController ( Clean and scrub )
 */
public class StageController
{
    private double xOff = 0;
    private double yOff = 0;
    private Stage temporaryStage;
    public StageController()
    {

    }
    public StageController(Stage stage)
    {
        this.temporaryStage = stage;
    }
    public void createTitleStage()
    {
        try
        {
            Parent root = loadParent("/fxml/LoginScene.fxml");
            temporaryStage.initStyle(StageStyle.UNDECORATED);

            temporaryStage.setScene(new Scene(root, 475, 250));
            temporaryStage.show();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void mainSceneStage(MouseEvent mouseEvent) throws IOException
    {
        Parent mainScene = loadParent("/fxml/MainScene.fxml");
        Scene nextScene = new Scene(mainScene);
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        temporaryStage = currentStage;
        currentStage.setScene(nextScene);
        currentStage.show();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        currentStage.setX((bounds.getWidth() - currentStage.getWidth())/2);
        currentStage.setY((bounds.getHeight() - currentStage.getHeight())/2);


    }
    public Parent loadParent(String sceneLocation) throws IOException
    {

        Parent scene = FXMLLoader.load(getClass().getResource(sceneLocation));
        scene.getStylesheets().add("JavaFX/css/layout.css");
        scene.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOff = event.getSceneX();
                yOff = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                temporaryStage.setX(event.getScreenX() - xOff);
                temporaryStage.setY(event.getScreenY() - yOff);
            }
        });
        return scene;

    }
}
