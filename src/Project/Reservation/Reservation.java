package Project.Reservation;

import java.util.Date;
import java.util.List;

public class Reservation
{
    private Date departingOn;
    private Date ArrivingOn;
    private List<Package> packages;


    public Date getDepartingOn()
    {
        return departingOn;
    }

    public void setDepartingOn(Date departingOn)
    {
        this.departingOn = departingOn;
    }

    public Date getArrivingOn()
    {
        return ArrivingOn;
    }

    public void setArrivingOn(Date arrivingOn)
    {
        ArrivingOn = arrivingOn;
    }

    public List<Package> getPackages()
    {
        return packages;
    }

    public void setPackages(List<Package> packages)
    {
        this.packages = packages;
    }
}
