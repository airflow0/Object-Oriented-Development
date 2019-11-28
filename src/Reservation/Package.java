package Reservation;

public class Package
{
    private String travelTo;
    private String travelFrom;
    private double price;
    private int hoursOfTravel;
    private TransportType type;

    public Package(String travelFrom, String travelTo, double price, int hoursOfTravel, TransportType type)
    {
        this.travelTo = travelTo;
        this.travelFrom = travelFrom;
        this.price = price;
        this.hoursOfTravel = hoursOfTravel;
        this.type = type;
    }



    public String getTravelTo()
    {
        return travelTo;
    }

    public void setTravelTo(String travelTo)
    {
        this.travelTo = travelTo;
    }

    public String getTravelFrom()
    {
        return travelFrom;
    }

    public void setTravelFrom(String travelFrom)
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
}
