package JavaFX.Controller;

import Data.DataController;
import JavaFX.Scenes.CreateCompanyScene;
import JavaFX.Scenes.EditCompanyScene;
import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;
import Project.Singleton.CompanySingleton;
import Project.Singleton.TripSingleton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import javax.xml.crypto.Data;
import javax.xml.soap.Text;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainSceneController
{
    //Company section
    @FXML
    private ListView<String> companyList;

    //Top Bar Navigation
    @FXML private Label closeButton;

    //Trip Section
    @FXML private Label tripLabel;
    @FXML private ListView<String> tripListView;
    @FXML private TextField searchTripField;
    @FXML private Button AddTripButton;
    @FXML private Button EditTripButton;

    //User section
    @FXML private Label selectedAgentLabel;

    //Traveler Section
    @FXML private Label travelerLabel;
    @FXML private TextArea travelerTextArea;
    @FXML private Button travelerSaveButton;
    @FXML private Button travelerNextButton;


    private Trip selectedTrip;
    private int selectedCompanyIndex = 0;
    private int selectedTripIndex = 0;
    private DataController controller;

    public void initalize()
    {
        loadCompany();
        selectedAgentLabel.setText("Welcome back, " + DataController.getSelectedAgent().getName());
        companyList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {


                tripListView.setVisible(true);
                searchTripField.setVisible(true);
                AddTripButton.setVisible(true);
                tripLabel.setVisible(true);
                EditTripButton.setVisible(true);

                selectedCompanyIndex = companyList.getItems().indexOf(companyList.getSelectionModel().getSelectedItem());
                selectedTripIndex = 0;
                tripListView.getItems().clear();
                System.out.println("Selected Company Index: " + selectedCompanyIndex);
                loadTrip();

                travelerLabel.setVisible(false);
                travelerTextArea.setVisible(false);
                travelerNextButton.setVisible(false);
                travelerSaveButton.setVisible(false);
                travelerSaveButton.setDisable(true);
                travelerTextArea.setText("");

            }
        });
        tripListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {

                travelerLabel.setVisible(true);
                travelerTextArea.setVisible(true);
                travelerNextButton.setVisible(true);
                travelerSaveButton.setVisible(true);

                selectedTripIndex = 0;
                selectedTripIndex = tripListView.getItems().indexOf(tripListView.getSelectionModel().getSelectedItem());
                System.out.println("Selected Trip Index: " + selectedTripIndex);
                if(selectedTripIndex != -1)
                {
                    DataController.setSelectedTrip(DataController.getCompanies().get(selectedCompanyIndex).getTrip(selectedTripIndex));
                    loadTraveler();
                }


            }
        });

        travelerTextArea.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                travelerSaveButton.setDisable(false);


            }
        });
    }
    /*
        Load from DataController

     */

    public void loadCompany()
    {
        for(Company company : DataController.getCompanies())
        {
            companyList.getItems().add(company.getCompanyName());
        }
    }

    public void loadTrip()
    {

        tripListView.getItems().addAll(DataController.getCompanies().get(selectedCompanyIndex).getTripListString());
    }
    public void loadTraveler()
    {

        travelerTextArea.setText("");
        String textArea = "";
        for(Traveler traveler : DataController.getSelectedTrip().getTravelers())
        {
            textArea = textArea + traveler.getName() + "\n";
        }
        travelerTextArea.setText(textArea);
    }


    /*
        Button Mouse Event
     */
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
        DataController.getCompanies().add(company);
        for( Company i : DataController.getCompanies())
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
        Trip tempTrip = new Trip(DataController.getCompanies().get(index), DataController.getSelectedAgent());
        DataController.getCompanies().get(index).addToTripsList(tempTrip);
        tempTrip.createTrip();
        tripListView.getItems().add(tempTrip.getUniqueID());
    }
    public void EditTrip(MouseEvent e)
    {


    }

    // traveller
    public void travelerSaveButtonClicked(MouseEvent e)
    {

        Alert.AlertType alertAlertType;
        Alert alert = new Alert(AlertType.ERROR);
        if(travelerTextArea.getText().isEmpty())
        {
            alert.setTitle("Error");
            alert.setHeaderText("Error in creating Travelers");
            alert.setContentText("There must be at least one name in a each line!");
            alert.showAndWait();
        }
        else
        {
            List<Traveler> tempList = new ArrayList<>();
            for(String name : travelerTextArea.getText().split("\n"))
            {

                Traveler traveler = new Traveler(name, selectedTrip);
                tempList.add(traveler);

                System.out.println(name);
            }
            DataController.getSelectedTrip().setTravelers(tempList);
            DataController.getSelectedTrip().saveTraveler();

        }
    }
}
