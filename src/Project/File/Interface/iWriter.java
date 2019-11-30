package Project.File.Interface;

import Project.Person.*;
import Reservation.Package;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public interface iWriter
{
    void write(Path path, PersonType pType, List point);
    void writeTraveler(Path path, PersonType pType, List<Traveler> point);
    void createCompanyDirectory(Path filePath);
    void createTripDirectory(Company company, Agent selectedAgent, String uniqueID);
    void createPackage(List<Package> pack);
    void createAgentList(List<Agent> agents);


}
