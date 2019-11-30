package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Traveler;
import Project.Person.Trip;

import java.util.List;

public class TravelerSingleton
{
    private static final Object syncLock = new Object();
    private static volatile TravelerSingleton _instance;
    private TravelerSingleton(List<Trip> trips)
    {
        for(Trip trip : trips)
        {
            List<Traveler> tempList = ReaderFactory.readFile(FileType.JSON).readTravelerFromFile(trip.getFilePath());
            if(tempList.isEmpty())
            {

            }
            else
            {
                trip.setTravelers(tempList);
            }

        }
    }

    public static void populate(List<Trip> trips)
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new TravelerSingleton(trips);
                }
            }
        }
    }
}
