package Project.File.FileFactory;

import Project.File.FileType.FileType;
import Project.File.Interface.iWriter;
import Project.File.Writer.JSONWriter;
import Project.Person.PersonType;
import Project.Person.Trip;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WriterFactory
{

    public static iWriter createWriter(FileType type)
    {
        switch(type)
        {
            case JSON:
                return new JSONWriter();
            case XML:
                //return new XMLWriter();
            default:
                throw new NotImplementedException();
        }
    }
    public static void writeTripData(Trip trip, FileType type, PersonType pType)
    {
        switch(type)
        {
            case JSON:
                new JSONWriter().write(trip, pType, trip.getListType(pType));
            case XML:
                new NotImplementedException();
            default:
                new NotImplementedException();
        }
    }
}
