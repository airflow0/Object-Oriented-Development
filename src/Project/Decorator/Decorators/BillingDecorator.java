package Project.Decorator.Decorators;

import Project.Decorator.Itinerary;
import Project.Decorator.ItineraryDecorator;

public class BillingDecorator extends ItineraryDecorator
{
    public BillingDecorator(Itinerary itineraryToBeDecorated)
    {
        super(itineraryToBeDecorated);
    }
    @Override
    public String getDescription()
    {
        return null;
    }
}
