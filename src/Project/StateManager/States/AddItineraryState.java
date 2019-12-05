package Project.StateManager.States;

import JavaFX.Controller.MainSceneController;
import Project.Decorator.Decorators.ThankYouDecorator;
import Project.Decorator.Itinerary;
import Project.Decorator.simpleItinerary;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddItineraryState implements iTripWriter
{
    private MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        context.setStateIndex(7);
        mainScene = context.getMainScene();
        load();
    }

    @Override
    public void load()
    {
        Itinerary itinerary = new ThankYouDecorator(new simpleItinerary());
        mainScene.getItineraryTextArea().setText(itinerary.getDescription());
    }
}
