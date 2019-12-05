package Project.File.Writer;



import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.File.Interface.iWriter;
import Project.Notes.ThankYou;
import Project.Payment.Payment;
import Project.Person.*;

import Project.Reservation.Package;
import Project.Reservation.Place;
import Project.Reservation.Reservation;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


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
    public void writeTraveler(Path path, List<Person> people)
    {
        try
        {
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + "/"  + "traveler.json"), people);
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
    public void writeReservation(Path path, Reservation input)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + "/reservation.json"), input);
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
                Files.createFile(filePath.resolve("people.json"));
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
        Path thankyou = filePath.resolve("thankyou.json");
        Path itinerary = filePath.resolve("itinerary.json");
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
                Files.createFile(thankyou);
                Files.createFile(itinerary);
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
    public void writePerson(Path filePath, List<Person> personList)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath + "/people.json"), personList);
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
    public void writePayment(Path filePath, Payment payment)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath + "/payment.json"), payment);
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
    public void writeThankYou(Path filePath, ThankYou note)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath + "/thankyou.json"), note);
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
