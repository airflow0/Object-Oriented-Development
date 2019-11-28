package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripSingleton
{
    private static final Object syncLock = new Object();
    private static volatile TripSingleton _instance;

    private TripSingleton(List<Company> company, Agent selectedAgent)
    {
        for(Company temp : company)
        {
            List<Trip> tempList = ReaderFactory.readFile(FileType.JSON).readTripFromDirectory(temp, selectedAgent);
            temp.replaceTripList(tempList);
        }
    }
    public static void populateTripList(List<Company> company, Agent selectedAgent)
    {
        if (_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new TripSingleton(company, selectedAgent);
                }
            }
        }

    }
}
