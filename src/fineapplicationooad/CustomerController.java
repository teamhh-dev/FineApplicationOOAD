/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Hamza
 */
public class CustomerController
{

    CustomerModel model;
    CustomerView view;
    Database dao;



    public CustomerController(CustomerModel model, CustomerView view,Database dao)
    {
        this.model = model;
        this.view = view;
        this.dao=dao;
//        view.addButton.addActionListener(new ActionListener()
//        {
//
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                model.setCustomerName(view.nameText.getText());
//                model.setCustomerAddress(view.addressText.getText());
//                model.setCustomerPhoneNo(view.phoneNoText.getText());
//                model.setCustomerDescription(view.descriptionText.getText());
//                addCustomer(model);
//            }
//
//        });

        this.dao = new Database();
    }

    void addCustomer(CustomerModel model)
    {
        //check if already exists customer with same name

        //check if string not null
        if (model.getCustomerName().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(view, "Name Missing!", "Enter Name", JOptionPane.WARNING_MESSAGE);
//            JOptionPane.showMessageDialog(view, "Name Missing!");
        } else
        {
            if (!dao.databaseStatus)
            {
                JOptionPane.showMessageDialog(view, "Customer " + model.getCustomerName() + " not added!", "Error Adding Customer!", JOptionPane.WARNING_MESSAGE);

            }
            else if (dao.addCustomer(model))
            {
                URL iconURL = getClass().getResource("AppData/add.png");
                ImageIcon icon = new ImageIcon(iconURL);
                JOptionPane.showMessageDialog(view, "Added Customer:" + model.getCustomerName() + "!", "Sussessfuly Added!", JOptionPane.OK_OPTION, icon);

            } else
            {
                JOptionPane.showMessageDialog(view, "Customer " + model.getCustomerName() + " not added!", "Error Adding Customer!", JOptionPane.WARNING_MESSAGE);

            }

        }

    }

    public CustomerModel getModel()
    {
        return model;
    }

    public CustomerView getView()
    {
        return view;
    }

    public void setModel(CustomerModel model)
    {
        this.model = model;
    }

    public void setView(CustomerView view)
    {
        this.view = view;
    }

}
