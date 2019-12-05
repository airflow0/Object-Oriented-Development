package Project.Decorator;

public abstract class ItineraryDecorator implements Itinerary
{
    protected Itinerary itineraryToBeDecorated;
    public ItineraryDecorator(Itinerary itineraryToBeDecorated)
    {
        this.itineraryToBeDecorated = itineraryToBeDecorated;
    }
    @Override
    public String getDescription()
    {
        return null;
    }
}
