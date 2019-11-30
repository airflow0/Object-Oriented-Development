package JavaFX.Controller.MainScene;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Person.Trip;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.xml.crypto.Data;

public class TripController
{
    private static MainSceneController mainScene;

    public TripController(MainSceneController mainScene)
    {
        this.mainScene = mainScene;
    }
    public void initialize()
    {
        mainScene.getTripListView().getItems().clear();
        mainScene.getTripListView().getItems().addAll(DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).getTripList());
        mainScene.getTripListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Trip>()
        {
            @Override
            public void changed(ObservableValue<? extends Trip> observable, Trip oldValue, Trip newValue)
            {
                mainScene.setTravelerVisible(true);

                DataController.setSelectedTripIndex(mainScene.getTripListView().getItems().indexOf(mainScene.getTripListView().getSelectionModel().getSelectedItem()));

                System.out.println("Selected Trip Index: " + DataController.getSelectedTripIndex());
                if(DataController.getSelectedTripIndex() != -1)
                {
                    DataController.setSelectedTrip(DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).getTrip(DataController.getSelectedTripIndex()));
                    //loadTraveler();
                    //TravelerController travelerController = new TravelerController(mainScene);
                    //travelerController.initialize();
                    //initalize Traveler State
                    mainScene.getStateManager().jumpState();
                }
            }
        });
    }
    public static void addTrip()
    {
        Trip tempTrip = new Trip(DataController.getCompanies().get(DataController.getSelectedCompanyIndex()), DataController.getSelectedAgent());
        DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).addToTripsList(tempTrip);
        tempTrip.createTrip();
        mainScene.getTripListView().getItems().add(tempTrip);
    }
}
