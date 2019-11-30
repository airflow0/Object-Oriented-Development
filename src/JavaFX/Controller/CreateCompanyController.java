package JavaFX.Controller;

import Project.Person.Company;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateCompanyController
{
    @FXML
    private TextField companyNameInput;
    private MainSceneController mainScene;

    public void CloseButtonClicked(MouseEvent e)
    {
        Node src = (Node) e.getSource();
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();

    }
    public void AddToCompanyList(MouseEvent e) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(companyNameInput.getText().isEmpty())
        {
            alert.setTitle("Error!");
            alert.setTitle(null);
            alert.setContentText("Company name input cannot be empty!");
            alert.showAndWait();
        }
        else if(containsIllegal(companyNameInput.getText()))
        {
            alert.setTitle("Error!");
            alert.setTitle(null);
            alert.setContentText("Illegal Character in Company name! Please try again.");
            alert.showAndWait();
        }
        else
        {
            Company createCompany = new Company(companyNameInput.getText());
            createCompany.createCompany();
            mainScene.addCompanyToListView(createCompany);
        }
    }
    public void setMainScene(MainSceneController scene)
    {
        mainScene = scene;
    }
    public boolean containsIllegal(String input)
    {
        Pattern patter = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher match = patter.matcher(input);
        return match.find();
    }

}
