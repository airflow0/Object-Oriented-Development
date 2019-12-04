package Project.Payment;

import Project.Person.Person;
import Project.Reservation.Package;
import Project.Reservation.Reservation;

public class Check implements iPayment
{
    private Person selectedPerson;
    private double totalPrice;
    private String accountNumber;
    private String routingNumber;

    public Check()
    {
        totalPrice = 0;
    }

    public Person getSelectedPerson()
    {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson)
    {
        this.selectedPerson = selectedPerson;
    }

    @Override
    public void calculatePayment(Reservation reservation)
    {
        for(Package pack : reservation.getPackages())
        {
            totalPrice = totalPrice + pack.getPrice();
        }
    }
}
