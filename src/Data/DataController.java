package Data;

import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import Project.File.Writer.JSONWriter;
import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Person;
import Project.Person.Trip;
import Project.Reservation.Place;
import Project.Singleton.*;
import Project.Reservation.Package;

import java.util.ArrayList;
import java.util.List;

public class DataController
{
    private static List<Company> companies;
    private static List<Agent> agents;
    private static List<Package> packages;
    private static List<Place> placeList;
    private static Company selectedCompany;
    private static Agent selectedAgent;
    private static Trip selectedTrip;
    private static int selectedCompanyIndex;
    private static int  selectedTripIndex;


    public DataController()
    {
    }

    public static void load()
    {
        companies = CompanySingleton.getCompanyList();
        TripSingleton.populateTripList(companies, selectedAgent);
        generateFilePath();
        PersonSingleton.populatePersonList(companies);
        System.out.println("[LOADED COMPANIES, TRIPS, TRAVELERS]");
        for(Company company : companies)
        {
            System.out.println("Companies: ");
            System.out.println(company.getCompanyName());

            for(Trip trip : company.getTripList())
            {
                System.out.println("\tTrips: ");
                System.out.println("\t\t" + trip.getUniqueID());
                System.out.println("\tTravelers: ");
                for(Person person : trip.getTravelers())
                {

                    System.out.println("\t\t" + person.getName());
                }
            }
        }
        TravelerSingleton.populateTraveler(companies);
        packages = PackageSingleton.getPackageList(companies);
        placeList = PlaceSingleton.getPlaceList();

    }
    public static void savePersonList()
    {
        WriterFactory.createWriter(FileType.JSON).writePerson(companies.get(selectedCompanyIndex).getFilePath(), companies.get(selectedCompanyIndex).getPeople());
    }
    public static void saveTraveler()
    {
        WriterFactory.createWriter(FileType.JSON).writeTraveler(selectedTrip.getFilePath(), selectedTrip.getTravelers());
    }
    public static List<Company> getCompanies()
    {
        return companies;
    }

    public static void setCompanies(List<Company> companies)
    {
        DataController.companies = companies;
    }

    public static List<Agent> getAgents()
    {
        return agents;
    }
    public static void setAgents(List<Agent> agents)
    {
        DataController.agents = agents;
    }

    public static Agent getSelectedAgent()
    {
        return selectedAgent;
    }

    public static void setSelectedAgent(Agent selectedAgent)
    {
        DataController.selectedAgent = selectedAgent;
    }

    public static Agent getAgentByIndex(int index)
    {
        return agents.get(index);
    }

    public static Trip getSelectedTrip()
    {
        return selectedTrip;
    }

    public static void setSelectedTrip(Trip selectedTrip)
    {
        DataController.selectedTrip = selectedTrip;
    }

    public static List<Package> getPackages()
    {
        return packages;
    }

    public static List<Place> getPlaceList()
    {
        return placeList;
    }

    public static void setPlaceList(List<Place> placeList)
    {
        DataController.placeList = placeList;
    }

    public static void setPackages(List<Package> packages)
    {
        DataController.packages = packages;
    }

    public static void setSelectedCompany()
    {
        selectedCompany = companies.get(selectedCompanyIndex);
    }
    public static Company getSelectedCompany()
    {
        return selectedCompany;
    }
    public static int getSelectedCompanyIndex()
    {
        return selectedCompanyIndex;
    }

    public static void setSelectedCompanyIndex(int selectedCompanyIndex)
    {
        DataController.selectedCompanyIndex = selectedCompanyIndex;
    }

    public static int getSelectedTripIndex()
    {
        return selectedTripIndex;
    }

    public static void setSelectedTripIndex(int selectedTripIndex)
    {
        DataController.selectedTripIndex = selectedTripIndex;
    }
    private static void generateFilePath()
    {
        for(Company company : companies)
        {
            for(Trip trip : company.getTripList())
            {
                trip.generateFilePath();
            }
        }
    }
}
