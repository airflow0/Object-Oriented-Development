package Project.File.Interface;

import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.PersonType;
import Project.Person.Trip;
import Reservation.Package;
import java.nio.file.Path;
import java.util.List;

public interface iWriter
{
    void write(Trip trip, PersonType pType, List point);
    void createCompanyDirectory(Path filePath);
    void createTripDirectory(Company company, Agent selectedAgent, String uniqueID);
    void createPackage(List<Package> pack);
    void createAgentList(List<Agent> agents);


}
