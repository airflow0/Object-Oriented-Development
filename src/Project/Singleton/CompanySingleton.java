package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Company;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public final class CompanySingleton
{
    private static final Object syncLock = new Object();
    private static volatile CompanySingleton _instance; // Only used for lazy loader >=)
    private static List<Company> companyList = new ArrayList<>(); //Called in variables, because it will start up with the program anyways.

    private CompanySingleton()
    {
        companyList = ReaderFactory.readFile(FileType.JSON).readCompanyFromDirectory();

    }

    public static List getCompanyList()
    {
        //Double checking lazy loader >=)
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new CompanySingleton();
                }
            }
        }
        return companyList;
    }
}
