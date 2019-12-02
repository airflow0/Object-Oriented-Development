package Project.Person;

import Data.DataController;
import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import Project.Properties.Settings;
import Project.Reservation.Package;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Trip
{

    private String uniqueID;
    private List<Person> travelers;
    private List<Package> reservations;
    private Agent selectedAgent;
    private Path filePath;
    private Company company;

    public Trip(Company company, Agent selectedAgent)
    {
        this.company = company;
        this.selectedAgent = selectedAgent;
        travelers = new ArrayList<>();
        reservations = new ArrayList<>();


    }


    public String getUniqueID()
    {
        return uniqueID;
    }
    public void setUniqueID(String uniqueID)
    {
        this.uniqueID = uniqueID;
    }

    public List<Person> getTravelers()
    {
        return travelers;
    }
    public void setTravelers(List<Person> travelers)
    {
        this.travelers = travelers;
    }
    public void addTraveler(Person person)
    {
        travelers.add(person);
    }
    public void addTravelers(List<Person> tempList)
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

    public void addToReservation(Package pack)
    {
        reservations.add(pack);
    }
    public Package getReservationByIndex(int index)
    {
        return reservations.get(index);
    }
    public List<Package> getReservations()
    {
        return reservations;
    }

    public void setReservations(List<Package> reservations)
    {
        this.reservations = reservations;
    }

    public Path getFilePath()
    {
        return filePath;
    }
    public void setFilePath(Path filePath)
    {
        this.filePath = filePath;
    }

    public void createTrip()
    {
        String pattern ="yyyy-MM-dd HH.mm.ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern, new Locale("en", "US"));
        String date = format.format(new Date());
        uniqueID = date;
        filePath = Paths.get(company.getFilePath() + "/" + selectedAgent.getName() + "/" + uniqueID);
        WriterFactory.createWriter(FileType.JSON).createTripDirectory(company,selectedAgent,date);
    }

    public void saveReservations()
    {
        WriterFactory.createWriter(FileType.JSON).writeReservation(filePath, reservations);
    }
    public void generateFilePath()
    {
        Path  tempPath = Paths.get(company.getFilePath() + "/" + DataController.getSelectedAgent().getName() + "/" + uniqueID);
        filePath = tempPath;
    }
    public int getTravelerListSize()
    {
        return travelers.size();
    }
    @Override
    public String toString()
    {
        return uniqueID;
    }
}

