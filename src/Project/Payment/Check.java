package Project.Payment;

import Project.Person.Person;
import Project.Reservation.Package;
import Project.Reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Check
{
    @JsonProperty("payAsGo")
    private boolean payAsGo;
    private String accountNumber;
    private String routingNumber;
    @JsonProperty("amountPaid")
    private double amountPaid;

    public Check()
    {

        amountPaid = 0;
        payAsGo = false;
    }

    public Check(double totalPrice)
    {
        amountPaid = 0;
        payAsGo = false;
    }
    public String getAccountNumber()
    {
        return accountNumber;
    }
    public void setAmountPaid(double amount)
    {
        amountPaid = amount;
    }
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }
    public String getRoutingNumber()
    {
        return routingNumber;
    }
    public void setRoutingNumber(String routingNumber)
    {
        this.routingNumber = routingNumber;
    }
    public double getAmountPaid()
    {
        return amountPaid;
    }
    public void setPayAsGo(boolean payAsGo)
    {
        this.payAsGo = payAsGo;
    }
}
