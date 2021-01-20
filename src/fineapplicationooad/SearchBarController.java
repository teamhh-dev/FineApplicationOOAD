/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author Hamza
 */
public class SearchBarController
{

    SearchBarView view;
    SearchBarModel model;

    Database dao;
    Vector<String> customerName = new Vector<>();
    Vector<CustomerModel> customers=new Vector<>();

    public SearchBarController(SearchBarView view, SearchBarModel model, Database dao)
    {
        this.view = view;
        this.model = model;
        this.dao = dao;
        this.dao = new Database();

        customers = this.dao.getAllCustomers();
        
        for (CustomerModel customer : customers)
        {
            customerName.add(customer.getCustomerName());
//            System.out.println(customer.getCustomerName());
        }

        view.searchList.setListData(customerName);

//        view.button.addActionListener(new ActionListener()
//        {
//
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
////                int[] array = searchList.getSelectedIndices();
//                List<String> selectedValuesList = view.searchList.getSelectedValuesList();
////                Obj[] select=searchList.getSelectedValues();
//                for (String selectedValue : selectedValuesList)
//                {
//                    System.out.println(selectedValue);
//                }
//
//            }
//        });
        this.view.searchType.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                view.searchType.setText("");
                view.searchList.setListData(customerName);
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });

        this.view.searchType.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                Vector<String> search = new Vector<>();
                String type = view.searchType.getText();

                if (type.equals(""))
                {
                    view.searchList.setListData(customerName);
                } else
                {

                    for (String customer : customerName)
                    {

                        if (customer.toLowerCase().contains(type.toLowerCase()))
                        {
                            search.addElement(customer);
                        }

                    }
                    view.searchList.setListData(search);
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                Vector<String> search = new Vector<>();
                String type = view.searchType.getText();

                if (type.equals(""))
                {
                    view.searchList.setListData(customerName);
                } else
                {

                    for (String customer : customerName)
                    {

                        if (customer.toLowerCase().contains(type.toLowerCase()))
                        {
                            search.addElement(customer);
                        }

                    }
                    view.searchList.setListData(search);
                }
            }
        });
    }

    public void setModel(SearchBarModel model)
    {
        this.model = model;
    }

    public SearchBarModel getModel()
    {
        return model;
    }

    public void setView(SearchBarView view)
    {
        this.view = view;
    }

    public SearchBarView getView()
    {
        return view;
    }

    public void updateCustomerList()
    {
        customers = this.dao.getAllCustomers();
        customerName = new Stack<>();

        for (CustomerModel customer : customers)
        {
            customerName.add(customer.getCustomerName());
//            System.out.println(customer.getCustomerName());
        }

        view.searchList.setListData(customerName);

    }

    public static void main(String[] args)
    {
        JFrame frame;

        frame = new JFrame("Search Menu");
        frame.setSize(450, 700);

        SearchBarView app = new SearchBarView();
        SearchBarController ctrl = new SearchBarController(app, new SearchBarModel(), new Database());
        frame.add(ctrl.getView());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
