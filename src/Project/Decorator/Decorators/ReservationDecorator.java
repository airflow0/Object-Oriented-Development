package Project.Decorator.Decorators;

import Project.Decorator.Itinerary;
import Project.Decorator.ItineraryDecorator;

public class ReservationDecorator extends ItineraryDecorator
{
    public ReservationDecorator(Itinerary itineraryToBeDecorated)
    {
        super(itineraryToBeDecorated);
    }
    @Override
    public String getDescription()
    {
        return null;
    }
}
