/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

/**
 *
 * @author Hamza
 */
public class CustomerModel
{
    private String customerName;
    private String customerAddress;
    private String customerPhoneNo;
    private String customerDescription;

    public CustomerModel(String customerName, String customerAddress, String customerPhoneNo, String customerDescription)
    {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNo = customerPhoneNo;
        this.customerDescription = customerDescription;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public String getCustomerPhoneNo()
    {
        return customerPhoneNo;
    }

    public String getCustomerDescription()
    {
        return customerDescription;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPhoneNo(String customerPhoneNo)
    {
        this.customerPhoneNo = customerPhoneNo;
    }

    public void setCustomerDescription(String customerDescription)
    {
        this.customerDescription = customerDescription;
    }
    
    
    
    
}
