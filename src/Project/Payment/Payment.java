package Project.Payment;

import Project.Person.Person;
import Project.Reservation.Package;
import Project.Reservation.Reservation;

public class Payment
{
    private Person person;
    private CreditCard credit;
    private Check check;
    private double totalPrice;

    public Payment()
    {
        totalPrice = 0;
    }

    public Payment(Person person)
    {
        this.person = person;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public CreditCard getCredit()
    {
        return credit;
    }

    public void setCredit(CreditCard credit)
    {
        this.credit = credit;
    }

    public Check getCheck()
    {
        return check;
    }

    public void setCheck(Check check)
    {
        this.check = check;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public void calculatePayment(Reservation reservation)
    {
        double temp = 0;

        for(Package pack : reservation.getPackages())
        {
            temp = temp + pack.getPrice();
        }
        if(credit != null)
        {
            temp = temp - credit.getAmountPaid();
        }
        if(check != null)
        {
            temp = temp - check.getAmountPaid();
        }
        totalPrice = temp;
    }
}
