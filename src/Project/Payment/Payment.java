package Project.Payment;

import Project.Person.Person;

public class Payment
{
    private Person person;
    private iPayment credit;
    private iPayment check;

    public Payment()
    {

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

    public iPayment getCredit()
    {
        return credit;
    }

    public void setCredit(iPayment credit)
    {
        this.credit = credit;
    }

    public iPayment getCheck()
    {
        return check;
    }

    public void setCheck(iPayment check)
    {
        this.check = check;
    }
}
