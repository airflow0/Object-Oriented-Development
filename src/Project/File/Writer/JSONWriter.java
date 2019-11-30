package Project.File.Writer;



import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.File.Interface.iWriter;
import Project.Person.*;

import Reservation.Package;
import Reservation.TransportType;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
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
    public void write(Path path, PersonType pType, List point)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + "/" + pType.toString().toLowerCase() +".json"), point);
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
    public void writeTraveler(Path path, PersonType pType, List<Traveler> travelers)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + "/" + pType.toString().toLowerCase() +".json"), travelers);
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

    public void createCompanyDirectory(Path filePath)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        List<Agent> populateAgent = ReaderFactory.readFile(FileType.JSON).readAgentFromFile();
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
                for( Agent agent : populateAgent)
                {
                    Path agentPath = Paths.get(filePath + "/" + agent.getName());
                    Files.createDirectories(agentPath);
                }
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
        Path travelerFile = filePath.resolve("traveler.json");
        Path reservation = filePath.resolve("reservation.json");
        Path payment = filePath.resolve("payment.json");
        Path billing = filePath.resolve("billing.json");
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
                Files.createFile(travelerFile);
                Files.createFile(reservation);
                Files.createFile(payment);
                Files.createFile(billing);
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
