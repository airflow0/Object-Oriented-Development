package Project.Person;

import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Company
{
    private List<Trip> trips;
    private Path filePath;
    private String companyName;



    public Company(String companyName)
    {
        this.companyName = companyName;
        trips = new ArrayList<>();
        filePath = Paths.get("resources/Company/" + companyName);

    }
    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public void createCompany()
    {
        WriterFactory.createWriter(FileType.JSON).createCompanyDirectory(filePath);
    }

    public void addToTripsList(Trip trip)
    {
        trips.add(trip);
    }
    public Trip getTrip(int index)
    {
        return trips.get(index);
    }
    public List<String> getTripListString()
    {
        List<String> tempList = new ArrayList<>();
        for(Trip i : trips)
        {
            tempList.add(i.getUniqueID());
        }
        return tempList;
    }
    public List<Trip> getTripList()
    {
        return trips;
    }
    public void replaceTripList(List<Trip> temp)
    {
        trips = temp;
    }
    public Path getFilePath()
    {
        return filePath;
    }

    public void setFilePath(Path filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public String toString()
    {
        return companyName;
    }
}
