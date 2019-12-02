package JavaFX.Controller;

import Data.DataController;
import JavaFX.Controller.MainScene.CompanyController;
import JavaFX.Controller.MainScene.TripController;
import JavaFX.Scenes.CreateCompanyScene;
import JavaFX.Scenes.EditCompanyScene;
import Project.Person.Company;
import Project.Person.Person;
import Project.Person.Trip;
import Project.Reservation.Place;
import Project.Reservation.Package;
import Project.Reservation.TransportType;
import Project.StateManager.States.AddPackageState;
import Project.StateManager.States.AddPaymentChoseState;
import Project.StateManager.States.AddPaymentSelectState;
import Project.StateManager.States.AddReservationState;
import Project.StateManager.TripContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

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
    @FXML private ListView<Person> travelerListView;
    @FXML private Button travelerAddButton;
    @FXML private Button travelerNextButton;
    @FXML private Button travelerAddPerson;
    @FXML private ComboBox<Person> travelerComboBox;
    @FXML private TextField travelerNameTextField;
    @FXML private Label TravelerNewPersonLabel;

    //Package Section
    @FXML private Label packageLabel;
    @FXML private ComboBox<Place> packageComboFrom;
    @FXML private ComboBox<Place> packageComboTo;
    @FXML private ComboBox<TransportType> packageComboVehicle;
    @FXML private ListView<Package> packageList;
    @FXML private Button packageAddButton;
    @FXML private Button packageNextButton;
    @FXML private TextField packageHoursOfTravel;
    @FXML private Label packageLabelTo;
    @FXML private TextField packagePrice;


    //Reservation Section
    @FXML Label reservationLabel;
    @FXML DatePicker reservationDate;
    @FXML Button reservationNext;

    //Choose Person
    @FXML Label choosePersonLabel;
    @FXML ComboBox<Person> choosePersonCombo;
    @FXML Button choosePersonNext;

    //Select Payment
    @FXML Label selectPaymentType;
    @FXML Button selectCreditCard;
    @FXML Button selectCheck;

    private TripContext stateManager;
    private Alert alert;
    public void initalize()
    {
        alert = new Alert(AlertType.ERROR);
        CompanyController companyController = new CompanyController(this);
        companyController.initalize();
        setTripVisible(false);
        setTravelerVisible(false);
        setPackageVisible(false);
        setReservationUIVisible(false);
        setPaymentPersonUIVisible(false);
        setPaymentTypeUIVisible(false);
        stateManager = new TripContext(this);


    }
    /*
        Load from DataController

     */


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
        companyList.getItems().get(DataController.getSelectedCompanyIndex()).setCompanyName(input);
        for(int i = 0; i < companyList.getItems().size(); i++)
        {
            System.out.println(companyList.getItems().get(i));
        }
    }

    public void AddTrip(MouseEvent e)
    {
        Trip tempTrip = new Trip(DataController.getCompanies().get(DataController.getSelectedCompanyIndex()), DataController.getSelectedAgent());
        DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).addToTripsList(tempTrip);
        tempTrip.createTrip();
        tripListView.getItems().add(tempTrip);
    }

    /*

     */

    // traveller
    public void travelerAddButtonClicked(MouseEvent e)
    {
        if(travelerComboBox.getSelectionModel().isEmpty())
        {
            alert.setTitle("Error");
            alert.setHeaderText("Traveler Add Person Error");
            alert.setContentText("Must select a person from the list!");
            alert.showAndWait();
        }
        else
        {
            Person temp = travelerComboBox.getValue();
            DataController.getSelectedTrip().addTraveler(temp);
            travelerListView.getItems().add(temp);
            DataController.saveTraveler();
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
    public void travelerAddPersonClicked(MouseEvent e)
    {
        Alert.AlertType alertAlertType;

        if(travelerNameTextField.getText().isEmpty())
        {
            alert.setTitle("Error");
            alert.setHeaderText("Traveler Add Person Error");
            alert.setContentText("Cannot be empty!");
            alert.showAndWait();
        }
        else
        {
            Person temp = new Person(travelerNameTextField.getText());
            travelerComboBox.getItems().add(temp);
            DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).addPerson(temp);
            DataController.savePersonList();
            travelerNameTextField.setText("");


        }
    }

    public void packageAddClicked()
    {
        Alert.AlertType alertAlertType;
        Alert alert = new Alert(AlertType.ERROR);

        if(packageComboFrom.getSelectionModel().isEmpty() || packageComboTo.getSelectionModel().isEmpty() || packageComboVehicle.getSelectionModel().isEmpty())
        {
            alert.setTitle("Selection error!");
            alert.setHeaderText("");
            alert.setContentText("Please select a package from the drop down!");
            alert.showAndWait();
        }
        else if( packageHoursOfTravel.getText().isEmpty())
        {
            alert.setTitle("Error!");
            alert.setHeaderText("");
            alert.setContentText("Amout of hours to Travel cannot be blank!");
            alert.showAndWait();
        }
        else if(isNumeric(packagePrice.getText()) == false && !packagePrice.getText().isEmpty())
        {
            alert.setTitle("Error!");
            alert.setHeaderText("");
            alert.setContentText("Enter number for Price!");
            alert.showAndWait();
        }
        else if(isNumeric(packageHoursOfTravel.getText()) == false && !packageHoursOfTravel.getText().isEmpty())
        {
            alert.setTitle("Error!");
            alert.setHeaderText("");
            alert.setContentText("Enter number for Hours!");
            alert.showAndWait();
        }
        else
        {
            int selectedPackageIndex = packageComboFrom.getSelectionModel().getSelectedIndex();
            //public Package(Place travelFrom, Place travelTo, double price, int hoursOfTravel, TransportType type)
            Place from = packageComboFrom.getValue();
            Place to = packageComboTo.getValue();
            double price = Double.parseDouble(packagePrice.getText());
            int hours = Integer.parseInt(packageHoursOfTravel.getText());
            TransportType type = packageComboVehicle.getValue();
            Package pack = new Package(from, to, price, hours, type);
            DataController.getSelectedTrip().addToReservation(pack);
            DataController.getSelectedTrip().saveReservations();
            packageList.getItems().add(pack);

            packageComboFrom.getSelectionModel().select(to);
            packageComboTo.getSelectionModel().clearSelection();
            packagePrice.setText("");
            packageHoursOfTravel.setText("");
            packageComboVehicle.getSelectionModel().clearSelection();



        }
    }
    public void packagedNextClicked(MouseEvent e)
    {

        setPackageUI(true);
        setReservationUIVisible(true);
        stateManager.setState(new AddReservationState(), this);
        stateManager.jumpState();
    }
    public void reservationNextClicked(MouseEvent e)
    {

        setReservationUI(true);
        setPaymentPersonUIVisible(true);
        stateManager.setState(new AddPaymentChoseState(), this);
        stateManager.jumpState();
    }

    public void choosePersonNextClicked(MouseEvent e)
    {

        setPaymentPersonUI(true);
        setPaymentTypeUIVisible(true);
        stateManager.setState(new AddPaymentSelectState(), this);
        stateManager.jumpState();
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

    public Label getPackageLabel()
    {
        return packageLabel;
    }

    public void setPackageLabel(Label packageLabel)
    {
        this.packageLabel = packageLabel;
    }

    public ComboBox getPackageComboFrom()
    {
        return packageComboFrom;
    }

    public void setPackageComboFrom(ComboBox packageComboFrom)
    {
        this.packageComboFrom = packageComboFrom;
    }

    public ComboBox getPackageComboTo()
    {
        return packageComboTo;
    }

    public void setPackageComboTo(ComboBox packageComboTo)
    {
        this.packageComboTo = packageComboTo;
    }

    public ListView getPackageList()
    {
        return packageList;
    }

    public void setPackageList(ListView packageList)
    {
        this.packageList = packageList;
    }

    public Button getPackageAddButton()
    {
        return packageAddButton;
    }

    public void setPackageAddButton(Button packageAddButton)
    {
        this.packageAddButton = packageAddButton;
    }

    public Button getPackageNextButton()
    {
        return packageNextButton;
    }

    public void setPackageNextButton(Button packageNextButton)
    {
        this.packageNextButton = packageNextButton;
    }

    public TextField getPackageHoursOfTravel()
    {
        return packageHoursOfTravel;
    }

    public void setPackageHoursOfTravel(TextField packageHoursOfTravel)
    {
        this.packageHoursOfTravel = packageHoursOfTravel;
    }

    public ComboBox getPackageComboVehicle()
    {
        return packageComboVehicle;
    }

    public void setPackageComboVehicle(ComboBox packageComboVehicle)
    {
        this.packageComboVehicle = packageComboVehicle;
    }

    public TextField getPackagePrice()
    {
        return packagePrice;
    }

    public void setPackagePrice(TextField packagePrice)
    {
        this.packagePrice = packagePrice;
    }

    public Label getTravelerLabel()
    {
        return travelerLabel;
    }

    public void setTravelerLabel(Label travelerLabel)
    {
        this.travelerLabel = travelerLabel;
    }

    public ListView<Person> getTravelerListView()
    {
        return travelerListView;
    }

    public void setTravelerListView(ListView<Person> travelerListView)
    {
        this.travelerListView = travelerListView;
    }



    public void setTravelerNextButton(Button travelerNextButton)
    {
        this.travelerNextButton = travelerNextButton;
    }

    public Button getTravelerAddPerson()
    {
        return travelerAddPerson;
    }

    public void setTravelerAddPerson(Button travelerAddPerson)
    {
        this.travelerAddPerson = travelerAddPerson;
    }

    public ComboBox<Person> getTravelerComboBox()
    {
        return travelerComboBox;
    }

    public void setTravelerComboBox(ComboBox<Person> travelerComboBox)
    {
        this.travelerComboBox = travelerComboBox;
    }

    public TextField getTravelerNameTextField()
    {
        return travelerNameTextField;
    }

    public void setTravelerNameTextField(TextField travelerNameTextField)
    {
        this.travelerNameTextField = travelerNameTextField;
    }

    public Label getChoosePersonLabel()
    {
        return choosePersonLabel;
    }

    public void setChoosePersonLabel(Label choosePersonLabel)
    {
        this.choosePersonLabel = choosePersonLabel;
    }

    public ComboBox<Person> getChoosePersonCombo()
    {
        return choosePersonCombo;
    }

    public void setChoosePersonCombo(ComboBox<Person> choosePersonCombo)
    {
        this.choosePersonCombo = choosePersonCombo;
    }

    public Button getChoosePersonNext()
    {
        return choosePersonNext;
    }

    public void setChoosePersonNext(Button choosePersonNext)
    {
        this.choosePersonNext = choosePersonNext;
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
        travelerComboBox.setVisible(state);
        travelerNameTextField.setVisible(state);
        travelerAddButton.setVisible(state);
        travelerAddPerson.setVisible(state);
        travelerNextButton.setVisible(state);
        travelerListView.setVisible(state);
        TravelerNewPersonLabel.setVisible(state);

    }
    public void setPackageVisible(boolean state)
    {
        packageLabel.setVisible(state);
        packageList.setVisible(state);
        packageComboFrom.setVisible(state);
        packageComboTo.setVisible(state);
        packageComboVehicle.setVisible(state);
        packagePrice.setVisible(state);
        packageHoursOfTravel.setVisible(state);
        packageLabelTo.setVisible(state);
        packageAddButton.setVisible(state);
        packageNextButton.setVisible(state);
    }
    public void setReservationUIVisible(boolean state)
    {
        reservationLabel.setVisible(state);
        reservationDate.setVisible(state);
        reservationNext.setVisible(state);
    }
    public void setPaymentPersonUIVisible(boolean state)
    {
        choosePersonCombo.setVisible(state);
        choosePersonLabel.setVisible(state);
        choosePersonNext.setVisible(state);
    }
    public void setPaymentTypeUIVisible(boolean state)
    {
        selectPaymentType.setVisible(state);
        selectCreditCard.setVisible(state);
        selectCheck.setVisible(state);
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
        travelerComboBox.setDisable(state);
        travelerNameTextField.setDisable(state);
        travelerAddButton.setDisable(state);
        travelerAddPerson.setDisable(state);
        travelerNextButton.setDisable(state);
        travelerListView.setDisable(state);
        TravelerNewPersonLabel.setDisable(state);

    }
    public void setPackageUI(boolean state)
    {
        packageLabel.setDisable(state);
        packageList.setDisable(state);
        packageComboFrom.setDisable(state);
        packageComboTo.setDisable(state);
        packageComboVehicle.setDisable(state);
        packagePrice.setDisable(state);
        packageHoursOfTravel.setDisable(state);
        packageLabelTo.setDisable(state);
        packageAddButton.setDisable(state);
        packageNextButton.setDisable(state);
    }
    public void setReservationUI(boolean state)
    {
        reservationLabel.setDisable(state);
        reservationDate.setDisable(state);
        reservationNext.setDisable(state);
    }
    public void setPaymentPersonUI(boolean state)
    {
        choosePersonCombo.setDisable(state);
        choosePersonLabel.setDisable(state);
        choosePersonNext.setDisable(state);
    }
    public void setPaymentTypeUI(boolean state)
    {
        selectPaymentType.setDisable(state);
        selectCreditCard.setDisable(state);
        selectCheck.setDisable(state);
    }
    public Button getTravelerNextButton()
    {
        return travelerNextButton;
    }

    public TripContext getStateManager()
    {
        return stateManager;
    }
    public boolean isNumeric(String temp)
    {
        if(temp == null)
        {
            return false;
        }
        try
        {
            double d = Double.parseDouble(temp);

        } catch (NumberFormatException ex)
        {
            return false;
        }
        return true;
    }
}
