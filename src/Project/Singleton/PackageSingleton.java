package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;

import java.util.List;

import Project.Person.Company;

import Project.Person.Trip;
import Project.Reservation.Package;
import Project.Reservation.Reservation;

public class PackageSingleton
{
    private static Object syncLock = new Object();
    private static volatile PackageSingleton _instance;

    private PackageSingleton(List<Company> companies)
    {
        for(Company company : companies)
        {
            for(Trip trip : company.getTripList())
            {
                Reservation temp = ReaderFactory.readFile(FileType.JSON).readReservationFromFile(trip.getFilePath());
                trip.setReservation(temp);
            }

        }

    }
    public static void getReservation(List<Company> companies)
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
    }
}
