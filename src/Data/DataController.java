package Data;

import Project.Person.Agent;
import Project.Person.Company;
import Project.Person.Traveler;
import Project.Person.Trip;
import Project.Singleton.CompanySingleton;
import Project.Singleton.TravelerSingleton;
import Project.Singleton.TripSingleton;

import java.util.List;

public class DataController
{
    private static List<Company> companies;
    private static List<Trip> trips;
    private static List<Traveler> travelers;
    private static List<Agent> agents;

    private static Agent selectedAgent;
    private static Trip selectedTrip;

    public DataController()
    {
    }

    public static void load()
    {
        companies = CompanySingleton.getCompanyList();
        TripSingleton.populateTripList(companies, selectedAgent);
        for(Company company: companies)
        {
            System.out.println(company.getCompanyName());
            for(Trip trip : company.getTripList())
            {
                trip.generateFilePath();
                System.out.println(trip.getUniqueID());
            }
        }
        for(Company company: companies)
        {
            TravelerSingleton.populate(company.getTripList());
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
}
