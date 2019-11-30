package Project.File.Reader;

import Project.File.Interface.iReader;
import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;
import Project.Reservation.Package;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONReader implements iReader
{
    private ObjectMapper mapper;
    public JSONReader()
    {
        mapper = new ObjectMapper();
    }

    @Override
    public List<Agent> readAgentFromFile()
    {
        List<Agent> tempList = new ArrayList<>();
        try
        {
            tempList = mapper.readValue(new File(   "resources/agent.json"), new TypeReference<List<Agent>>(){});
            return tempList;
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return tempList;
    }
    /*
    @Override
    public  List<Traveler> readTravelerFromFile(Trip trip)
    {
        List<Traveler> tempList = new ArrayList<>();
        try
        {
            tempList = mapper.readValue(new File(  "/" +  "traveler.json"), new TypeReference<List<Traveler>>(){});
            return tempList;
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return tempList;
    }*/
    public  List<Traveler> readTravelerFromFile(Path filePath)
    {
        List<Traveler> tempList = new ArrayList<>();
        Path tempPath = Paths.get(filePath +"/traveler.json");
        try
        {
            File file = new File(tempPath.toString());
            if(file.length() == 0)
            {
                return tempList;
            }
            else
            {
                tempList = mapper.readValue(file, new TypeReference<List<Traveler>>(){});
                return tempList;
            }

        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return tempList;
    }

    @Override
    public  List<Company> readCompanyFromDirectory()
    {
        List<Company>        tempList = new ArrayList<>();
        //Read file directory Test
        File directory = new File("resources/Company");
        File[] files = directory.listFiles();
        FileFilter fileFilter = new FileFilter()
        {
            public boolean accept(File file)
            {
                return file.isDirectory();
            }
        };
        files = directory.listFiles(fileFilter);

        for(File file : files)
        {
            tempList.add(new Company(file.getName()));
        }

        return tempList;
    }
    @Override
    public List<Trip> readTripFromDirectory(Company company, Agent selectedAgent)
    {
        List<Trip> tempList = new ArrayList<>();
        File directory = new File("resources/Company/" + company.getCompanyName()+"/" + selectedAgent.getName() + "/");
        File[] files = directory.listFiles();
        FileFilter fileFilter = new FileFilter()
        {
            public boolean accept(File file)
            {
                return file.isDirectory();
            }
        };
        files = directory.listFiles(fileFilter);
        for(File file: files)
        {
            Trip tempTrip = new Trip(company, selectedAgent);
            tempTrip.setUniqueID(file.getName());

            tempList.add(tempTrip);
        }
        return tempList;
    }
    @Override
    public List<Package> readDefaultPackage()
    {
        List<Package> temp = new ArrayList<>();
        try
        {
            temp = mapper.readValue(new File("resources/package.json"), new TypeReference <List<Package>>(){});
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return temp;
    }
}
