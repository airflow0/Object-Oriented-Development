package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Person.Traveler;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class AddTravelerState implements iTripWriter
{
    private static MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {

        mainScene = context.getMainScene();
        load();

    }

    @Override
    public void load()
    {
        mainScene.getTravelerTextArea().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                mainScene.setTravelerSaveButton(false);
                System.out.println("Test");
            }
        });
        mainScene.setTravelerTextArea("");
        String textArea = "";
        System.out.println("[LOAD TRAVLER METHOD]");
        System.out.println("Company Index: " + DataController.getSelectedCompanyIndex());
        System.out.println("Trip index: " + DataController.getSelectedTripIndex());
        for(Traveler traveler : DataController.getCompanies().get(DataController.getSelectedCompanyIndex()).getTripList().get(DataController.getSelectedTripIndex()).getTravelers())
        {
            textArea = textArea + traveler.getName() + "\n";

        }
        mainScene.setTravelerTextArea(textArea);
    }
}
