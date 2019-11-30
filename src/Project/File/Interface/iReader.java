package Project.File.Interface;

import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;

import java.nio.file.Path;
import java.util.List;

public interface iReader
{
    List<Agent> readAgentFromFile();
    List<Traveler> readTravelerFromFile(Path filePath);
    List<Trip> readTripFromDirectory(Company company, Agent agent);
    List<Company> readCompanyFromDirectory();
}
