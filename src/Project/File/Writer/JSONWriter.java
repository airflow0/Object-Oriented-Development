package Project.File.Writer;



import Project.File.Interface.iWriter;
import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.PersonType;
import Project.Person.Trip;

import Reservation.Package;
import Reservation.TransportType;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class JSONWriter implements iWriter
{

    private ObjectMapper mapper;
    public JSONWriter()
    {
        mapper = new ObjectMapper();
    }
    public void write(Trip trip, PersonType pType, List point)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(trip.getFilePath()+ "/" + pType.toString().toLowerCase() +".json"), point);
        }
        catch (JsonGenerationException e)
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
    }
    //Only for initally creating package json ahahahaah I cheat.


    public void createCompanyDirectory(Path filePath)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Files.exists(filePath))
        {

            //System.out.println("Files already exist!");
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Company already exist!");
            alert.showAndWait();
        }
        else
        {
            try
            {
                Files.createDirectories(filePath);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Company Created!");
                alert.showAndWait();


            }
            catch (IOException e)
            {
                //TODO: Message box
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Error in creating Company directory!");
                alert.showAndWait();
            }
        }
    }
    public void createTripDirectory(Company company, Agent selectedAgent, String uniqueID)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        //resources/Facebook /
        Path filePath = Paths.get(company.getFilePath() + "/"+ selectedAgent.getName() +  "/" + uniqueID);
        if(Files.exists(filePath))
        {

            //System.out.println("Files already exist!");
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Trip already exist!");
            alert.showAndWait();
        }
        else
        {
            try
            {
                Files.createDirectories(filePath);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Trip Created!");
                alert.showAndWait();


            }
            catch (IOException e)
            {
                //TODO: Message box
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Error in creating Trip directory!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void createPackage(List<Package> pack)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources/package.json"), pack);
        }
        catch (JsonGenerationException e)
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
    }
    @Override
    public void createAgentList(List<Agent> agents)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources/agent.json"), agents);
        }
        catch (JsonGenerationException e)
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
    }
}
