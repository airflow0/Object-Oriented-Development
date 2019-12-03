package Project.Reservation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation
{
    private String departingOn;
    private String ArrivingOn;
    private List<Package> packages;

    public Reservation()
    {
        packages = new ArrayList<>();
    }
    public Reservation(String departingOn)
    {
        this.departingOn = departingOn;
        packages = new ArrayList<>();
    }

    public String getDepartingOn()
    {
        return departingOn;
    }

    public void setDepartingOn(String departingOn)
    {
        this.departingOn = departingOn;
    }

    public String getArrivingOn()
    {
        return ArrivingOn;
    }

    public void setArrivingOn(String arrivingOn)
    {
        ArrivingOn = arrivingOn;
    }

    public void addToPackages(Package pack)
    {
        packages.add(pack);
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
