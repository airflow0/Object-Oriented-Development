package Project.Person;

import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import Project.Properties.Settings;
import Reservation.Reservation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Trip
{

    private Settings set;
    private String uniqueID;
    private List<Traveler> travelers;
    private Agent selectedAgent;
    private List<Reservation> reservations;

    private Path filePath;
    private Company company;

    public Trip(Company company, Agent selectedAgent)
    {

        set = new Settings();
        travelers = new ArrayList<>();
        this.company = company;
        this.selectedAgent = selectedAgent;
    }


    public String getUniqueID()
    {
        return uniqueID;
    }
    public void setUniqueID(String uniqueID)
    {
        this.uniqueID = uniqueID;
    }

    public List<Traveler> getTravelers()
    {
        return travelers;
    }
    public void setTravelers(List<Traveler> travelers)
    {
        this.travelers = travelers;
    }
    public void addTraveler(Traveler traveler)
    {
        travelers.add(traveler);
    }
    public void addTravelers(List<Traveler> tempList)
    {
        travelers.addAll(tempList);

    }

    public void addAgent(Agent agent)
    {
        this.selectedAgent = agent;
    }
    public Agent getAgent()
    {
        return selectedAgent;
    }

    public Path getFilePath()
    {
        return filePath;
    }
    private void setFilePath(Path filePath)
    {
        this.filePath = filePath;
    }

    public List getListType(PersonType p)
    {
        switch(p)
        {
            case AGENT:
                throw new NotImplementedException();
            case TRAVELER:
                return travelers;
            default:
                return null;
        }
    }
    public void createTrip()
    {
        String pattern ="yyyy-MM-dd HH.mm.ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern, new Locale("en", "US"));
        String date = format.format(new Date());
        uniqueID = date;
        WriterFactory.createWriter(FileType.JSON).createTripDirectory(company,selectedAgent,date);
    }

}

