package Project.File.Interface;

import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Person;
import Project.Person.Trip;
import Project.Reservation.Package;
import Project.Reservation.Place;

import java.nio.file.Path;
import java.util.List;

public interface iReader
{
    List<Agent> readAgentFromFile();
    List<Person> readTravelerFromFile(Path filePath);
    List<Trip> readTripFromDirectory(Company company, Agent agent);
    List<Company> readCompanyFromDirectory();
    List<Package> readReservationFromFile(Path filePath);
    List<Place> readPlaceList();
    List<Person> readPersonList(Path filePath);
}
