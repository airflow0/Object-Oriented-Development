package Project.Decorator.Decorators;

import Data.DataController;
import Project.Decorator.Itinerary;
import Project.Decorator.ItineraryDecorator;
import Project.Person.Trip;

public class ThankYouDecorator extends ItineraryDecorator
{
    public ThankYouDecorator(Itinerary itineraryToBeDecorated)
    {
        super(itineraryToBeDecorated);
    }
    @Override
    public String getDescription()
    {
        Trip selectedTrip = DataController.getSelectedTrip();

        return selectedTrip.getNote().getThankYouString() + "\n";
    }
}
