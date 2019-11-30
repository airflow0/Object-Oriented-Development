package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;

import java.util.ArrayList;
import java.util.List;

public class TravelerSingleton
{
    private static final Object syncLock = new Object();
    private static volatile TravelerSingleton _instance;
    private TravelerSingleton(List<Company> companies)
    {

        for(Company company : companies)
        {
            for(Trip trip : company.getTripList())
            {
                List<Traveler> tempList = ReaderFactory.readFile(FileType.JSON).readTravelerFromFile(trip.getFilePath());
                trip.setTravelers(tempList);
            }

        }
    }

    public static void populate(List<Company> companies)
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new TravelerSingleton(companies);
                }
            }
        }
    }
}
