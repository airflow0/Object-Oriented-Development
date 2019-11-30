package Data;

import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;
import Project.Singleton.CompanySingleton;
import Project.Singleton.PackageSingleton;
import Project.Singleton.TravelerSingleton;
import Project.Singleton.TripSingleton;
import Project.Reservation.Package;

import java.util.List;

public class DataController
{
    private static List<Company> companies;
    private static List<Trip> trips;
    private static List<Traveler> travelers;
    private static List<Agent> agents;
    private static List<Package> preloadedPackages;

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
        TravelerSingleton.populate(companies);
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
                for(Traveler traveler : trip.getTravelers())
                {

                    System.out.println("\t\t" + traveler.getName());
                }
            }
        }

        preloadedPackages = PackageSingleton.getPackageList();
        System.out.println("[LOADED: PREDESIGNED PACKAGES]");
        for(Package pack : preloadedPackages)
        {
            System.out.println("Travel From " + pack.getTravelFrom() + " Travel To: " + pack.getTravelTo());
        }

    }
    public static List<Company> getCompanies()
    {
        return companies;
    }

    public static void setCompanies(List<Company> companies)
    {
        DataController.companies = companies;
    }

    public static List<Trip> getTrips()
    {
        return trips;
    }

    public static void setTrips(List<Trip> trips)
    {
        DataController.trips = trips;
    }

    public static List<Traveler> getTravelers()
    {
        return travelers;
    }

    public static void setTravelers(List<Traveler> travelers)
    {
        DataController.travelers = travelers;
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

    public static List<Package> getPreloadedPackages()
    {
        return preloadedPackages;
    }

    public static void setPreloadedPackages(List<Package> preloadedPackages)
    {
        DataController.preloadedPackages = preloadedPackages;
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
}
