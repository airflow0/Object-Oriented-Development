package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Person.Person;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;


public class AddTravelerState implements iTripWriter
{
    private static MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {

        mainScene = context.getMainScene();
        context.setStateIndex(0);
        load();

    }

    @Override
    public void load()
    {


        System.out.println("[LOAD TRAVLER METHOD]");
        System.out.println("Company Index: " + DataController.getSelectedCompanyIndex());
        System.out.println("Trip index: " + DataController.getSelectedTripIndex());
        mainScene.getTravelerComboBox().getItems().setAll(DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).getPeople());
        mainScene.getTravelerListView().getItems().setAll(DataController.getSelectedTrip().getTravelers());
    }
}
