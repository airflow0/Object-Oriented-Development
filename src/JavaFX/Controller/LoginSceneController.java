package JavaFX.Controller;

import Data.DataController;
import JavaFX.Scenes.MainScene;
import Project.Person.Agent;
import Project.Singleton.AgentSingleton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginSceneController implements Initializable
{
    @FXML private Label loginButton;
    @FXML private ComboBox<String> agentComboBox;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //LOAD ALL AGENTS :)
        DataController.setAgents(AgentSingleton.getAgentList());
        for(Agent agent : DataController.getAgents())
        {
            agentComboBox.getItems().add(agent.getName());
        }

    }
    public void loginTextClick(MouseEvent mouseEvent) throws IOException
    {
        if(agentComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You must select an agent!");
            alert.showAndWait();
        }
        else
        {
            int index = agentComboBox.getSelectionModel().getSelectedIndex();
            DataController.setSelectedAgent(DataController.getAgentByIndex(index));
            System.out.println("[AGENT SELECTED]: " + DataController.getSelectedAgent().getName());
            DataController.load();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            MainScene scene = new MainScene();
            scene.createMainStage();
        }
    }

    public void loginClose(MouseEvent mouseEvent)
    {
        Platform.exit();
    }
}
