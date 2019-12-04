package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Reservation.TransportType;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class AddPackageState implements iTripWriter
{
    private static MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        mainScene = context.getMainScene();
        context.setStateIndex(1);
        load();
        mainScene.getPackageList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                mainScene.getPackageList().refresh();
            }
        });
    }
    @Override
    public void load()
    {
        if(DataController.getSelectedTrip().getReservation().getArrivingOn() != null)
        {
            mainScene.getPackageStartingDate().setValue(LocalDate.parse(DataController.getSelectedTrip().getReservation().getArrivingOn()));
        }
        mainScene.getPackageList().getItems().setAll(DataController.getSelectedTrip().getReservation().getPackages());
        mainScene.getPackageComboFrom().getItems().setAll(DataController.getPlaceList());
        mainScene.getPackageComboTo().getItems().setAll(DataController.getPlaceList());
        mainScene.getPackageComboVehicle().getItems().setAll(TransportType.values());
    }
}
