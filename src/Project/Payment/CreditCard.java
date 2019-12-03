package Project.Payment;

import Project.Reservation.Package;
import Project.Reservation.Reservation;

public class CreditCard implements iPayment
{
    private double totalPrice;
    public CreditCard()
    {
        totalPrice = 0;
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
