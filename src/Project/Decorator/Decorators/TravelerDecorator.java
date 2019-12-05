package Project.Decorator.Decorators;

import Project.Decorator.Itinerary;
import Project.Decorator.ItineraryDecorator;

public class TravelerDecorator extends ItineraryDecorator
{
    public TravelerDecorator(Itinerary itineraryToBeDecorated)
    {
        super(itineraryToBeDecorated);
    }
    @Override
    public String getDescription()
    {
        return null;
    }
}
