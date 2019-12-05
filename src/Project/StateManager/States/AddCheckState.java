package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddCheckState implements iTripWriter
{
    private MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        context.setStateIndex(5);
        mainScene = context.getMainScene();
        load();
    }

    @Override
    public void load()
    {
        if(!DataController.getSelectedTrip().getReservation().getPackages().isEmpty())
        {
            DataController.getSelectedTrip().getPayment().calculatePayment(DataController.getSelectedTrip().getReservation());
        }
        else
        {
        }
        if(DataController.getSelectedTrip().getPayment().getCheck() == null)
        {
            System.out.println("Check is null!");
            mainScene.getCreditCardNum().setText("");
            mainScene.getCreditCardDate().setText("");
            mainScene.getCreditCardAmountDueLabel().setText("Amount due: $" + Double.toString(DataController.getSelectedTrip().getPayment().getTotalPrice()));
        }
        else
        {

            mainScene.getCreditCardNum().setText(DataController.getSelectedTrip().getPayment().getCheck().getAccountNumber());
            mainScene.getCreditCardDate().setText(DataController.getSelectedTrip().getPayment().getCheck().getRoutingNumber());
            mainScene.getCreditCardAmountDueLabel().setText("Amount due: $" + Double.toString(DataController.getSelectedTrip().getPayment().getTotalPrice()));
        }
    }
}
