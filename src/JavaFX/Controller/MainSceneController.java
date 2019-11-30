package JavaFX.Controller;

import Data.DataController;
import JavaFX.Controller.MainScene.CompanyController;
import JavaFX.Controller.MainScene.TripController;
import JavaFX.Scenes.CreateCompanyScene;
import JavaFX.Scenes.EditCompanyScene;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;
import Project.StateManager.States.AddPackageState;
import Project.StateManager.TripContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainSceneController
{
    //Company section
    @FXML private ListView<Company> companyList;
    @FXML private Button createCompanyButton;
    @FXML private Button editCompanyButton;

    //Top Bar Navigation
    @FXML private Label closeButton;

    //Trip Section
    @FXML private Label tripLabel;
    @FXML private ListView<Trip> tripListView;
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

    //Package Section
    @FXML private Label packageLabel;
    @FXML private ComboBox packageCombo;
    @FXML private ListView packageList;
    @FXML private Button packageAddButton;
    @FXML private Button packageNextButton;



    private Trip selectedTrip;
    private int selectedCompanyIndex = 0;

    private int selectedPackageIndex = 0;
    private TripContext stateManager;

    public void initalize()
    {

        CompanyController companyController = new CompanyController(this);
        companyController.initalize();
        loadPreloadedPackages();
        stateManager = new TripContext(this);


    }
    /*
        Load from DataController

     */
    public void loadPreloadedPackages()
    {
        packageCombo.getItems().addAll(DataController.getPreloadedPackages());
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
        String selectedItem = companyList.getSelectionModel().getSelectedItem().getCompanyName();
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
        companyList.getItems().add(company);
        DataController.getCompanies().add(company);
        for( Company i : DataController.getCompanies())
        {
            System.out.println(i.getCompanyName());
        }
    }
    public void editCompanyListView(String input)
    {
        companyList.getItems().get(selectedCompanyIndex).setCompanyName(input);
        for(int i = 0; i < companyList.getItems().size(); i++)
        {
            System.out.println(companyList.getItems().get(i));
        }
    }
    public void AddTrip(MouseEvent e)
    {
        TripController.addTrip();
    }


    // traveller
    public void travelerSaveButtonClicked(MouseEvent e)
    {
        Alert.AlertType alertAlertType;
        Alert alert = new Alert(Alert.AlertType.ERROR);
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

                Traveler traveler = new Traveler(name, DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).getTrip(DataController.getSelectedTripIndex()));
                tempList.add(traveler);

                System.out.println(name);
            }
            DataController.getSelectedTrip().setTravelers(tempList);
            DataController.getSelectedTrip().saveTraveler();
            travelerTextArea.setDisable(false);
            travelerNextButton.setDisable(false);
        }
    }
    public void travelerNextButtonClicked(MouseEvent e)
    {
        setCompanyUI(true);
        setTripUI(true);
        setTravelerUI(true);
        setPackageVisible(true);
        stateManager.setState(new AddPackageState(), this);
        stateManager.jumpState();


    }
    public void packageAddClicked()
    {
        Alert.AlertType alertAlertType;
        Alert alert = new Alert(AlertType.ERROR);
        if(packageCombo.getSelectionModel().isEmpty())
        {
            alert.setTitle("Selection error!");
            alert.setHeaderText("");
            alert.setContentText("Please select a package from the drop down!");
            alert.showAndWait();
        }
        else
        {
            selectedPackageIndex = packageCombo.getSelectionModel().getSelectedIndex();
            packageList.getItems().add(DataController.getPreloadedPackages().get(selectedPackageIndex));
        }
    }
    public void packagedNextClicked()
    {

    }

    public Label getSelectedAgentLabel()
    {
        return selectedAgentLabel;
    }

    public void setSelectedAgentLabel(Label selectedAgentLabel)
    {
        this.selectedAgentLabel = selectedAgentLabel;
    }

    public ListView<Company> getCompanyList()
    {
        return companyList;
    }

    public void setCompanyList(ListView<Company> companyList)
    {
        this.companyList = companyList;
    }

    public ListView<Trip> getTripListView()
    {
        return tripListView;
    }

    public void setTripListView(ListView<Trip> tripListView)
    {
        this.tripListView = tripListView;
    }

    public TextArea getTravelerTextArea()
    {
        return travelerTextArea;
    }

    public void setTravelerTextArea(String text)
    {
        this.travelerTextArea.setText(text);
    }

    public void setTripVisible(boolean state)
    {
        tripListView.setVisible(state);
        searchTripField.setVisible(state);
        AddTripButton.setVisible(state);
        tripLabel.setVisible(state);
        EditTripButton.setVisible(state);
    }

    public void setTravelerVisible(boolean state)
    {
        travelerLabel.setVisible(state);
        travelerTextArea.setVisible(state);
        travelerNextButton.setVisible(state);
        travelerSaveButton.setVisible(state);
        if(state == false)
        {
            travelerTextArea.setText("");
        }
        else
        {
            //TODO implement
        }
    }
    public void setPackageVisible(boolean state)
    {
        packageLabel.setVisible(state);
        packageList.setVisible(state);
        packageCombo.setVisible(state);
        packageAddButton.setVisible(state);
        packageNextButton.setVisible(state);
    }
    public void setCompanyUI(boolean state)
    {
        companyList.setDisable(state);
        createCompanyButton.setDisable(state);
        editCompanyButton.setDisable(state);

    }
    public void setTripUI(boolean state)
    {
        tripLabel.setDisable(state);
        tripListView.setDisable(state);
        searchTripField.setDisable(state);
        AddTripButton.setDisable(state);
        EditTripButton.setDisable(state);

    }
    public void setTravelerUI(boolean state)
    {
        travelerLabel.setDisable(state);
        travelerTextArea.setDisable(state);
        travelerSaveButton.setDisable(state);
        travelerNextButton.setDisable(state);
    }
    public void setPackageUI(boolean state)
    {

    }

    public void setTravelerSaveButton(boolean state)
    {
        travelerSaveButton.setDisable(state);
    }

    public Button getTravelerNextButton()
    {
        return travelerNextButton;
    }

    public TripContext getStateManager()
    {
        return stateManager;
    }
}
