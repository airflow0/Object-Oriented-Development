package Project.Payment;

import Project.Person.Person;
import Project.Reservation.Package;
import Project.Reservation.Reservation;

public class Check implements iPayment
{
    private double totalPrice;
    private String accountNumber;
    private String routingNumber;

    public Check()
    {
        totalPrice = 0;
    }

    @Override
    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
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
