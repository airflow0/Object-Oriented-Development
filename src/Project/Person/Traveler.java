package Project.Person;


import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import Project.File.Writer.JSONWriter;

import java.util.List;

public class Traveler
{

    private String name;
    private Trip trip;
    public Traveler()
    {

    }
    public Traveler(String name, Trip trip)
    {
        this.name = name;
        this.trip = trip;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
