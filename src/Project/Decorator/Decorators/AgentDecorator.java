package Project.Decorator.Decorators;

import Project.Decorator.Itinerary;
import Project.Decorator.ItineraryDecorator;

public class AgentDecorator extends ItineraryDecorator
{
    public AgentDecorator(Itinerary itineraryToBeDecorated)
    {
        super(itineraryToBeDecorated);
    }
    @Override
    public String getDescription()
    {
        return null;
    }
}
