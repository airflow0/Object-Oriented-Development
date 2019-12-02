package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Company;
import Project.Person.Person;
import Project.Person.Trip;
import Project.Reservation.Package;

import java.util.List;

public class TravelerSingleton
{
    private static Object syncLock = new Object();
    private static volatile TravelerSingleton _instance;

    private TravelerSingleton(List<Company> companies)
    {
        for(Company company : companies)
        {
            for(Trip trip : company.getTripList())
            {
                List<Person> tempList = ReaderFactory.readFile(FileType.JSON).readTravelerFromFile(trip.getFilePath());
                trip.setTravelers(tempList);
            }

        }

    }
    public static void populateTraveler(List<Company> companies)
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
