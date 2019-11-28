package JavaFX.Controller;

import JavaFX.Scenes.CreateCompanyScene;
import JavaFX.Scenes.EditCompanyScene;
import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Trip;
import Project.Singleton.CompanySingleton;
import Project.Singleton.TripSingleton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainSceneController
{
    @FXML
    private ListView<String> companyList;
    @FXML
    private Label closeButton;

    @FXML
    private ListView<String> tripListView;
    @FXML
    private TextField searchTripField;

    @FXML
    private Button AddTripButton;
    @FXML
    private Button EditTripButton;

    @FXML
    private Label selectedAgentLabel;
    //TODO - GET LIST OF CLIENTS FROM XML OR JSON
    //protected List<String> clients = new ArrayList<>();

    private List<Company> companies;
    private Agent selectedAgent;

    public void initialize()
    {
        companies = CompanySingleton.getCompanyList();
        TripSingleton.populateTripList(companies, selectedAgent);

        for(Company company : companies)
        {
            companyList.getItems().add(company.getCompanyName());
        }

        companyList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                tripListView.setVisible(true);
                searchTripField.setVisible(true);
                AddTripButton.setVisible(true);
                EditTripButton.setVisible(true);
                int index = companyList.getItems().indexOf(companyList.getSelectionModel().getSelectedItem());
                tripListView.getItems().clear();
                tripListView.getItems().addAll(companies.get(index).getTripList());
                /**
                 * Call JSON Writer???? - ADD TRIP TO SELECTED COMPANY.
                 */
            }
        });
        tripListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {

            }
        });
    }

    public void CloseButtonClicked(MouseEvent e)
    {
        Platform.exit();
    }
    public void CreateCompanyClicked(MouseEvent e)
    {
        CreateCompanyScene companyScene = new CreateCompanyScene(this);
        companyScene.createCompanyScene(e);
    }
    public void EditCompanyClicked(MouseEvent e)
    {
        String selectedItem = companyList.getSelectionModel().getSelectedItem();
        if(selectedItem == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setTitle(null);
            alert.setContentText("Please select a Company from the List!");
            alert.showAndWait();
        }
        else
        {
            EditCompanyScene companyScene = new EditCompanyScene(this);
            companyScene.editCompanyScene(e);
        }
    }
    public void addCompanyToListView(Company company)
    {
        companyList.getItems().add(company.getCompanyName());
        companies.add(company);
        for( Company i : companies)
        {
            System.out.println(i.getCompanyName());
        }
    }
    public void editCompanyListView(String input)
    {
        int index = companyList.getItems().indexOf(companyList.getSelectionModel().getSelectedItem());
        companyList.getItems().set(index, input);
        for(int i = 0; i < companyList.getItems().size(); i++)
        {
            System.out.println(companyList.getItems().get(i));
        }
    }
    public void AddTrip(MouseEvent e)
    {
        int index = companyList.getItems().indexOf(companyList.getSelectionModel().getSelectedItem());
        Trip tempTrip = new Trip(companies.get(index));
        companies.get(index).addToTripsList(tempTrip);
        tempTrip.createTrip();
        tripListView.getItems().add(tempTrip.getUniqueID());
    }
    public void EditTrip(MouseEvent e)
    {


    }

    public void setAgent(Agent agent)
    {
        selectedAgent = agent;
    }
    public Agent getAgent()
    {
        return selectedAgent;
    }

    public Label getSelectedAgentLabel()
    {
        return selectedAgentLabel;
    }

    public void setSelectedAgentLabelText(String text)
    {
        this.selectedAgentLabel.setText(text);
    }
}
