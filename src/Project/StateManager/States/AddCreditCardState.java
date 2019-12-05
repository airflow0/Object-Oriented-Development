package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Payment.CreditCard;

import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

import java.nio.channels.DatagramChannel;

public class AddCreditCardState implements iTripWriter
{
    private MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        mainScene = context.getMainScene();
        context.setStateIndex(4);
        load();
    }

    @Override
    public void load()
    {

        if(!DataController.getSelectedTrip().getReservation().getPackages().isEmpty())
        {
            DataController.getSelectedTrip().getPayment().calculatePayment(DataController.getSelectedTrip().getReservation());
        }
        if(DataController.getSelectedTrip().getPayment().getCredit() == null)
        {
            System.out.println("Credit is null!");
            mainScene.getCreditCardNum().setText("");
            mainScene.getCreditCardDate().setText("");
            mainScene.getCreditCardCSV().setText("");
            mainScene.getCreditCardAmountDueLabel().setText("Amount due: $" + Double.toString(DataController.getSelectedTrip().getPayment().getTotalPrice()));
        }
        else
        {

            mainScene.getCreditCardNum().setText(DataController.getSelectedTrip().getPayment().getCredit().getCreditCardNumber());
            mainScene.getCreditCardDate().setText(DataController.getSelectedTrip().getPayment().getCredit().getCreditCardDate());
            mainScene.getCreditCardCSV().setText(DataController.getSelectedTrip().getPayment().getCredit().getCreditCardCSV());
            mainScene.getCreditCardAmountDueLabel().setText("Amount due: $" + Double.toString(DataController.getSelectedTrip().getPayment().getTotalPrice()));
        }

    }
}
