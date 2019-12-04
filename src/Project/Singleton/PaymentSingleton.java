package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Payment.Payment;
import Project.Person.Company;
import Project.Person.Trip;

import java.util.List;

public class PaymentSingleton
{
    private static Object syncLock = new Object();
    private static volatile PaymentSingleton _instance;

    private PaymentSingleton(List<Company> companies)
    {
        for(Company company : companies)
        {
            for(Trip trip : company.getTripList())
            {
                Payment temp = ReaderFactory.readFile(FileType.JSON).readPaymentFromFile(trip.getFilePath());
                trip.setPayment(temp);
            }
        }
    }
    public static void getPayment(List<Company> companies)
    {
        if(_instance == null)
        {
            synchronized (syncLock)
            {
                if(_instance == null)
                {
                    _instance = new PaymentSingleton(companies);
                }
            }
        }
    }
}
