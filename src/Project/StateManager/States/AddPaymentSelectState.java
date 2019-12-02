package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddPaymentSelectState implements iTripWriter
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
        mainScene.setPaymentPersonUI(true);

    }
}
