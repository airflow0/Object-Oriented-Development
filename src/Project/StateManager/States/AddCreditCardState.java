package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Payment.CreditCard;
import Project.Payment.iPayment;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddCreditCardState implements iTripWriter
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

    }
}
