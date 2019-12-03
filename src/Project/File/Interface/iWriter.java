package Project.File.Interface;

import Project.Person.*;
import Project.Reservation.Package;
import Project.Reservation.Place;
import Project.Reservation.Reservation;

import java.nio.file.Path;
import java.util.List;

public interface iWriter
{
    void write(Path path, PersonType pType, List point);
    void writeTraveler(Path path, List<Person> point);
    void writeReservation(Path path, Reservation input);
    void createCompanyDirectory(Path filePath);
    void createTripDirectory(Company company, Agent selectedAgent, String uniqueID);
    void writePerson(Path filePath, List<Person> personList);


}
