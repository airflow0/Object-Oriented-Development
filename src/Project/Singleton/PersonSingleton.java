package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Company;
import Project.Person.Person;
import Project.Person.Trip;

import java.io.File;
import java.io.Reader;
import java.util.List;

public class PersonSingleton
{
    private static final Object syncLock = new Object();
    private static volatile PersonSingleton _instance;
    private static List<Person> personList;
    private PersonSingleton(List<Company> companies)
    {

        for(Company company : companies)
        {
            personList = ReaderFactory.readFile(FileType.JSON).readPersonList(company.getFilePath());
            company.setPeople(personList);
        }
    }

    public static void populatePersonList(List<Company> companies)
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new PersonSingleton(companies);
                }
            }
        }

    }
}
