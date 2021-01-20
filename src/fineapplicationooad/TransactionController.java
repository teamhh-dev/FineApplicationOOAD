/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Hamza
 */
public class TransactionController
{

    TransactionView view;
    TransactionModel model;
    Database dao;
    SearchBarController searchBarCtrl;
    LocalDateTime dateAndTime = LocalDateTime.now();

    DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    String formattedDate = dateAndTime.format(formatting);

    public TransactionController(TransactionView view, TransactionModel model, Database dao)
    {
        searchBarCtrl = new SearchBarController(new SearchBarView(), new SearchBarModel(), dao);
        searchBarCtrl.view.viewButton.setText("Select");

        this.view = view;
        this.model = model;
        this.dao = dao;

        this.view.date.setText(formattedDate);

        view.add(searchBarCtrl.getView(), BorderLayout.WEST);
    }

    public String getCurrentDate()
    {
        dateAndTime = LocalDateTime.now();

        formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        formattedDate = dateAndTime.format(formatting);
        return formattedDate;

    }

    public void addTransaction()
    {
//        if (((this.view.customerName.getText().equals("Select Customer...") || ||(view.description.getText().equals(""))||(this.dao.addTransaction(this.model)))))
       if(!this.dao.addTransaction(this.model))
        {
            JOptionPane.showMessageDialog(view, "Transaction not added! Check if every field is selected", "Error Doing Transaction!", JOptionPane.WARNING_MESSAGE);

        } else
        {
            URL iconURL = getClass().getResource("AppData/add.png");
            ImageIcon icon = new ImageIcon(iconURL);
            JOptionPane.showMessageDialog(view, "Transaction  added!", "Transaction Done!", JOptionPane.YES_OPTION,icon);

        }

    }

    public Database getDao()
    {
        return dao;
    }

    public void setDao(Database dao)
    {
        this.dao = dao;
    }

    public TransactionModel getModel()
    {
        return model;
    }

    public void setModel(TransactionModel model)
    {
        this.model = model;
    }

    public TransactionView getView()
    {
        return view;
    }

    public void setView(TransactionView view)
    {
        this.view = view;
    }

    void updateCustomersInSearchBar()
    {
//        searchBarCtrl.view.searchList.setListData(listData);
        searchBarCtrl.updateCustomerList();

    }

    public static void main(String[] args)
    {

        TransactionController ctrl = new TransactionController(new TransactionView(), null, new Database());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize((int) screenSize.getWidth() - 275, (int) screenSize.getHeight() - 50);
        frame.add(ctrl.getView());

        frame.setVisible(true);

    }

}
