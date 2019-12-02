package Project.Person;


import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import Project.File.Writer.JSONWriter;

import java.util.List;

public class Person
{

    private String name;
    private Trip trip;

    public Person()
    {

    }
    public Person(String name)
    {
        this.name = name;
    }
    public Person(String name, Trip trip)
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
    @Override
    public String toString()
    {
        return name;
    }

}
