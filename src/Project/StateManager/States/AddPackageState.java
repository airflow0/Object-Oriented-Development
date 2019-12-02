package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import JavaFX.Scenes.MainScene;
import Project.Reservation.TransportType;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.xml.crypto.Data;

public class AddPackageState implements iTripWriter
{
    private static MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        mainScene = context.getMainScene();
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
        mainScene.getPackageList().getItems().addAll(DataController.getSelectedTrip().getReservations());
        mainScene.getPackageComboFrom().getItems().addAll(DataController.getPlaceList());
        mainScene.getPackageComboTo().getItems().addAll(DataController.getPlaceList());
        mainScene.getPackageComboVehicle().getItems().addAll(TransportType.values());
    }
}
