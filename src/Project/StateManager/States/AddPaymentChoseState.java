package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddPaymentChoseState implements iTripWriter
{
    private MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        mainScene = context.getMainScene();
        load();
    }

    @Override
    public void load()
    {
        mainScene.getChoosePersonCombo().getItems().setAll(DataController.getSelectedCompany().getPeople());
        mainScene.setReservationUI(true);
    }
}
