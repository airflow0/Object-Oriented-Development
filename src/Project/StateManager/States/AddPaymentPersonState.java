package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddPaymentPersonState implements iTripWriter
{
    private MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        mainScene = context.getMainScene();
        context.setStateIndex(2);
        load();

    }

    @Override
    public void load()
    {
        mainScene.getChoosePersonCombo().getItems().setAll(DataController.getSelectedCompany().getPeople());
        mainScene.getChoosePersonCombo().getSelectionModel().select(DataController.getSelectedTrip().getPayment().getPerson());
        mainScene.setPackageUI(true);
    }
}
