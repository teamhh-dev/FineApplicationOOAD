/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.time.LocalDateTime;

/**
 *
 * @author Hamza
 */
public class TransactionModel
{

    enum PaymentType
    {

        bill, payment, none
    }
    private String customerName;
    private float amount;
    private String currentDateTime;
    private String description;
    private PaymentType type;

    public TransactionModel(String customerName, float amount, PaymentType type, String dateTime,String description)
    {
        this.customerName = customerName;
        this.amount = amount;
        this.type = type;
        this.currentDateTime = dateTime;
        this.description=description;
    }

    public String getDescription()
    {
        return description;
    }

    
    public String getCustomerName()
    {
        return customerName;
    }

    public float getAmount()
    {
        return amount;
    }

    public PaymentType getType()
    {
        return type;
    }

    public String getCurrentDateTime()
    {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime)
    {
        this.currentDateTime = currentDateTime;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    
    
    
    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setType(PaymentType type)
    {
        this.type = type;
    }

}
