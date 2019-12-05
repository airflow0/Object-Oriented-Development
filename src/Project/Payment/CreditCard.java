package Project.Payment;

import Project.Person.Person;
import Project.Reservation.Package;
import Project.Reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCard
{
    @JsonProperty("payAsGo")
    private boolean payAsGo;
    private String creditCardNumber;
    private String creditCardDate;
    private String creditCardCSV;
    @JsonProperty("amountPaid")
    private double amountPaid;
    public CreditCard()
    {
        amountPaid = 0;
        payAsGo = false;
    }
    public CreditCard(double totalPrice)
    {
        amountPaid = 0;
        payAsGo = false;
    }

    public void setAmountPaid(double amount)
    {
        amountPaid = amount;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardDate()
    {
        return creditCardDate;
    }

    public void setCreditCardDate(String creditCardDate)
    {
        this.creditCardDate = creditCardDate;
    }

    public String getCreditCardCSV()
    {
        return creditCardCSV;
    }

    public void setCreditCardCSV(String creditCardCSV)
    {
        this.creditCardCSV = creditCardCSV;
    }

    public boolean isPayAsGo()
    {
        return payAsGo;
    }

    public void setPayAsGo(boolean payAsGo)
    {
        this.payAsGo = payAsGo;
    }

    public double getAmountPaid()
    {
        return amountPaid;
    }

}
