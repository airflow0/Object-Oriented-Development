package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;

import java.util.List;
import Project.Reservation.Package;

public class PackageSingleton
{
    private static Object syncLock = new Object();
    private static volatile PackageSingleton _instance;
    private static List<Package> packageList;
    private PackageSingleton()
    {
        packageList = ReaderFactory.readFile(FileType.JSON).readDefaultPackage();

    }
    public static List<Package> getPackageList()
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new PackageSingleton();
                }
            }
        }
        return packageList;
    }
}
