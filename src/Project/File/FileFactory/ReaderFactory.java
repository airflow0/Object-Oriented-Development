package Project.File.FileFactory;

import Project.File.FileType.FileType;
import Project.File.Interface.iReader;
import Project.File.Reader.JSONReader;
import Project.Person.Company;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class ReaderFactory
{
    public static   iReader readFile(FileType type)
    {
        switch(type)
        {
            case JSON:
                return new JSONReader();
            case XML:
                throw new NotImplementedException();
            default:
                throw new NotImplementedException();
        }
    }
}
