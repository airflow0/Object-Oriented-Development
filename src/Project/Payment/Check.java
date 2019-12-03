package Project.Payment;

import Project.Reservation.Reservation;

public class Check implements iPayment
{
    private String creditCardNumber;
    private String creditCardDate;
    private String creditCardCSV;

    @Override
    public void calculatePayment(Reservation reservation)
    {

    }
}
