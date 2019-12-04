package Project.Payment;

import Project.Person.Person;
import Project.Reservation.Package;
import Project.Reservation.Reservation;

public class CreditCard implements iPayment
{
    private Person selectedPerson;
    private double totalPrice;
    private String creditCardNumber;
    private String creditCardDate;
    private String creditCardCSV;
    public CreditCard()
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
