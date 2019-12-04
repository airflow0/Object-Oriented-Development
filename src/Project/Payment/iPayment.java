package Project.Payment;

import Project.Reservation.Reservation;

public interface iPayment
{
    double getTotalPrice();
    void calculatePayment(Reservation reservation);
}
