package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Reservation.Place;

import java.util.List;

public class PlaceSingleton
{
    private static Object syncLock = new Object();
    private static volatile PlaceSingleton _instance;
    private static List<Place> placeList;

    private PlaceSingleton()
    {
        placeList = ReaderFactory.readFile(FileType.JSON).readPlaceList();

    }
    public static List<Place> getPlaceList()
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new PlaceSingleton();
                }
            }
        }
        return placeList;
    }
}
