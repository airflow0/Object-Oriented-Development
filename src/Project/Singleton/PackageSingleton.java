package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;

import java.util.List;

import Project.Person.Company;

import Project.Person.Trip;
import Project.Reservation.Package;

public class PackageSingleton
{
    private static Object syncLock = new Object();
    private static volatile PackageSingleton _instance;
    private static List<Package> packageList;
    private PackageSingleton(List<Company> companies)
    {
        for(Company company : companies)
        {
            for(Trip trip : company.getTripList())
            {
                List<Package> tempList = ReaderFactory.readFile(FileType.JSON).readReservationFromFile(trip.getFilePath());
                trip.setReservations(tempList);
            }

        }

    }
    public static List<Package> getPackageList(List<Company> companies)
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new PackageSingleton(companies);
                }
            }
        }
        return packageList;
    }
}
