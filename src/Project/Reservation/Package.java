package Project.Reservation;
import Project.Reservation.TransportType;
import Project.Reservation.Place;
public class Package
{
    private Place travelTo;
    private Place travelFrom;
    private double price;
    private int hoursOfTravel;
    private TransportType type;

    public Package()
    {

    }

    public Package(Place travelFrom, Place travelTo, double price, int hoursOfTravel, TransportType type)
    {
        this.travelTo = travelTo;
        this.travelFrom = travelFrom;
        this.price = price;
        this.hoursOfTravel = hoursOfTravel;
        this.type = type;
    }

    public Place getTravelTo()
    {
        return travelTo;
    }

    public void setTravelTo(Place travelTo)
    {
        this.travelTo = travelTo;
    }

    public Place getTravelFrom()
    {
        return travelFrom;
    }

    public void setTravelFrom(Place travelFrom)
    {
        this.travelFrom = travelFrom;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getHoursOfTravel()
    {
        return hoursOfTravel;
    }

    public void setHoursOfTravel(int hoursOfTravel)
    {
        this.hoursOfTravel = hoursOfTravel;
    }
    @Override
    public String toString()
    {
        return travelFrom.getPlace() + " to " + travelTo.getPlace();
    }
}
