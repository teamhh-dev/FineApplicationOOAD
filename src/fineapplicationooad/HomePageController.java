/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableColumnModel;

/**
 *
 * @author Hamza
 */
public class HomePageController
{

    HomePageView view;
    Database dao;

    CustomerController customer;
    RecordController record;
    AddTransactionController addTransaction;
    UpdateDeleteTransactionController updateDeleteTransaction;
    BackupController backup;

    public HomePageController(HomePageView view, Database dao)
    {
        this.view = view;
        this.view.frame.setVisible(false);
        this.dao = dao;
        int count=0;
        while (this.dao.databaseStatus != true)
        {
            count=count+1;
            
            this.dao = new Database();
            JOptionPane.showMessageDialog(this.view.frame, "No database connectivity!\nStart MySql from XAMMP'", "No database connectivity!", JOptionPane.WARNING_MESSAGE);
            if(count==3)
            {
                System.exit(0);
            }
            
        }

        customer = new CustomerController(new CustomerModel(null, null, null, null), new CustomerView(), dao);
        record = new RecordController(new RecordView(), new RecordModel(), dao);
        addTransaction = new AddTransactionController(new AddTransactionView(), new TransactionModel(null, 0, TransactionModel.PaymentType.none, null, null), dao);
        updateDeleteTransaction = new UpdateDeleteTransactionController(new TransactionModel(null, 0, TransactionModel.PaymentType.none, null, null), new UpdateDeleteTransactionView(), dao);
        backup = new BackupController(new BackupView(), dao);

        view.contentPanel.add(customer.getView(), "Add Customer");
        view.contentPanel.add(record.getView(), "View Record");
        view.contentPanel.add(addTransaction.getView(), "Add Transaction");
        view.contentPanel.add(updateDeleteTransaction.getView(), "Update Delete Transaction");
        view.contentPanel.add(backup.getView(), "Backup");

        initButtonHandlers();

    }

    public void startApp()
    {
        this.view.frame.setVisible(true);

    }

    private void initButtonHandlers()
    {
        //Customer Add Button Handler 
        customer.view.addButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                customer.model.setCustomerName(customer.view.nameText.getText());
                customer.model.setCustomerAddress(customer.view.addressText.getText());
                customer.model.setCustomerPhoneNo(customer.view.phoneNoText.getText());
                customer.model.setCustomerDescription(customer.view.descriptionText.getText());
                customer.addCustomer(customer.model);

                record.updateCustomersInSearchBar();
                addTransaction.updateCustomersInSearchBar();
                updateDeleteTransaction.updateCustomersInSearchBar();

            }

        });

        //view customer record button handler
        record.searchBarCtrl.view.viewButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                List<String> selectedValuesList = record.searchBarCtrl.view.searchList.getSelectedValuesList();

                String selectedCustomer = record.searchBarCtrl.view.searchList.getSelectedValue();

                record.view.recordTable.setModel(dao.getCustomerRecord(selectedCustomer));

            }
        });

        //select ccustomer of add transaction panel button handler
        addTransaction.searchBarCtrl.view.viewButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedCustomer = addTransaction.searchBarCtrl.view.searchList.getSelectedValue();

                addTransaction.view.customerName.setText(selectedCustomer);
            }
        });
        //select customer of update delete transaction panel button handler
        updateDeleteTransaction.searchBarCtrl.view.viewButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedCustomer = updateDeleteTransaction.searchBarCtrl.view.searchList.getSelectedValue();

                updateDeleteTransaction.view.transactionsTable.setModel(dao.getAllTransaction(selectedCustomer));
                updateDeleteTransaction.view.transactionsTable.getTableHeader().setReorderingAllowed(false);

            }
        });

        //add transaction button handler of transaction panel
        addTransaction.view.addTransactionButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddTransactionView view = addTransaction.view;
                addTransaction.view.date.setText(addTransaction.getCurrentDate());
                if (!((addTransaction.view.customerName.getText().equals("Select Customer...") || (view.description.getText().equals("")) || (view.amount.getText().equals("")))))
                {
                    addTransaction.model.setAmount(Float.parseFloat(view.amount.getText()));
                    addTransaction.model.setCurrentDateTime(view.date.getText());
                    addTransaction.model.setCustomerName(view.customerName.getText());
                    addTransaction.model.setDescription(view.description.getText());
                    if (view.bill.isSelected())
                    {
                        addTransaction.model.setType(TransactionModel.PaymentType.bill);

                    } else
                    {
                        addTransaction.model.setType(TransactionModel.PaymentType.payment);

                    }
                    addTransaction.addTransaction();
                } else
                {
                    JOptionPane.showMessageDialog(view, "Transaction not added! Check if every field is selected", "Error Doing Transaction!", JOptionPane.WARNING_MESSAGE);

                }

            }
        });
        //delete transaction of update delete transaction panel button handler 
        updateDeleteTransaction.view.deleteButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                int selectedRowToDelete = updateDeleteTransaction.view.transactionsTable.getSelectedRow();
                JTable referenceTable = updateDeleteTransaction.view.transactionsTable;
                String customerName = referenceTable.getValueAt(selectedRowToDelete, 0).toString();
                String transactionDate = referenceTable.getValueAt(selectedRowToDelete, 1).toString();
                char transactionType = referenceTable.getValueAt(selectedRowToDelete, 2).toString().charAt(0);
                TransactionModel model;
                if (transactionType == 'B')
                {
                    model = new TransactionModel(customerName, 0, TransactionModel.PaymentType.bill, transactionDate, null);

                } else
                {
                    model = new TransactionModel(customerName, 0, TransactionModel.PaymentType.payment, transactionDate, null);

                }

                if (dao.deleteTransaction(model))
                {
                    URL iconURL = getClass().getResource("AppData/add.png");
                    ImageIcon icon = new ImageIcon(iconURL);
                    JOptionPane.showMessageDialog(updateDeleteTransaction.view, "Transaction  Deleted!", "Transaction Deleted!", JOptionPane.YES_OPTION, icon);

                } else
                {
                    JOptionPane.showMessageDialog(updateDeleteTransaction.view, "Transaction not Deleted!", "Error Deleting Transaction!", JOptionPane.WARNING_MESSAGE);

                }
                String selectedCustomer = updateDeleteTransaction.searchBarCtrl.view.searchList.getSelectedValue();

                updateDeleteTransaction.view.transactionsTable.setModel(dao.getAllTransaction(customerName));
            }
        });
        //update transaction of update delete transaction panel button handler 
        updateDeleteTransaction.view.updateButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                int selectedRowToDelete = updateDeleteTransaction.view.transactionsTable.getSelectedRow();
                JTable referenceTable = updateDeleteTransaction.view.transactionsTable;
//                String customerName = referenceTable.getValueAt(selectedRowToDelete, 0).toString();
//                String transactionDate = referenceTable.getValueAt(selectedRowToDelete, 1).toString();
//                char transactionType = referenceTable.getValueAt(selectedRowToDelete, 2).toString().charAt(0);
//                

                String customerName = referenceTable.getValueAt(referenceTable.getSelectedRow(), 0).toString();
                String transactionDate = referenceTable.getValueAt(referenceTable.getSelectedRow(), 1).toString();
                String transactionType = referenceTable.getValueAt(referenceTable.getSelectedRow(), 2).toString();
                String transactionDesc = referenceTable.getValueAt(referenceTable.getSelectedRow(), 4).toString();

                float transactionAmount = Float.parseFloat(referenceTable.getValueAt(referenceTable.getSelectedRow(), 3).toString());

                if (transactionType.equalsIgnoreCase("Bill"))
                {
                    if (dao.updateTransaction(new TransactionModel(customerName, transactionAmount, TransactionModel.PaymentType.bill, transactionDate, transactionDesc)))
                    {
                        URL iconURL = getClass().getResource("AppData/add.png");
                        ImageIcon icon = new ImageIcon(iconURL);
                        JOptionPane.showMessageDialog(updateDeleteTransaction.view, "Transaction  Updated!", "Transaction Updated!", JOptionPane.YES_OPTION, icon);

                    } else
                    {
                        JOptionPane.showMessageDialog(updateDeleteTransaction.view, "Transaction not Updated! Check if every field is correct-Otherwise Select CUstomer Transactions Again", "Error Doing Transaction Update!", JOptionPane.WARNING_MESSAGE);

                    }

                } else
                {
                    if (dao.updateTransaction(new TransactionModel(customerName, transactionAmount, TransactionModel.PaymentType.payment, transactionDate, transactionDesc)))
                    {
                        URL iconURL = getClass().getResource("AppData/add.png");
                        ImageIcon icon = new ImageIcon(iconURL);
                        JOptionPane.showMessageDialog(updateDeleteTransaction.view, "Transaction  Updated!", "Transaction Updated!", JOptionPane.YES_OPTION, icon);

                    } else
                    {
                        JOptionPane.showMessageDialog(updateDeleteTransaction.view, "Transaction not Updated! Check if every field is correct-Otherwise Select CUstomer Transactions Again", "Error Doing Transaction Update!", JOptionPane.WARNING_MESSAGE);

                    }
                }
                String selectedCustomer = updateDeleteTransaction.searchBarCtrl.view.searchList.getSelectedValue();

                updateDeleteTransaction.view.transactionsTable.setModel(dao.getAllTransaction(customerName));

            }
        });

        //local backup button handler of backup panel
        this.backup.view.local.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                LocalDateTime dateAndTime = LocalDateTime.now();

                DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm");

                String formattedDate = dateAndTime.format(formatting);

                String path = null;
                JFileChooser localBackupPath = new JFileChooser("./");
                localBackupPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                localBackupPath.showSaveDialog(view.frame);

                if(path==null)
                {
                    return;
                }
                File f = localBackupPath.getSelectedFile();
                path = f.getAbsolutePath();
                path = path.replace('\\', '/');

                path += ("/" + formattedDate + "_database_backup.sql");

                path = path.replace("//", "/");

                try
                {
                    String backupCommand = "C:/xampp/mysql/bin/mysqldump.exe -uroot  --add-drop-database -B hamzaalidatabase -r" + path;

                    int processComplete = -1;
                    Process runtime = Runtime.getRuntime().exec(backupCommand);

                    BufferedReader reader
                            = new BufferedReader(new InputStreamReader(runtime.getInputStream()));
                    while ((reader.readLine()) != null)
                    {
                        processComplete = runtime.waitFor();
                    }
                    if (processComplete == -1)
                    {
                        URL iconURL = getClass().getResource("AppData/add.png");
                        ImageIcon icon = new ImageIcon(iconURL);
                        JOptionPane.showMessageDialog(view.frame, "Backup Created Succesfully", "Sussessfuly Created!", JOptionPane.OK_OPTION, icon);

                    } else
                    {
                        JOptionPane.showMessageDialog(view.frame, "Error Creating Backup", "Backup Error!", JOptionPane.WARNING_MESSAGE);

                    }
                } catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(view.frame, "Error Creating Backup", "Backup Error!", JOptionPane.WARNING_MESSAGE);

                }
            }
        });

        //Menu Bar button handler on click switch to that specific panel
        this.view.viewRecord.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout layout = (CardLayout) view.contentPanel.getLayout();
                layout.show(view.contentPanel, "View Record");
            }
        });

        this.view.addCustomer.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout layout = (CardLayout) view.contentPanel.getLayout();
                layout.show(view.contentPanel, "Add Customer");
            }
        });
        this.view.addTransaction.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout layout = (CardLayout) view.contentPanel.getLayout();
                layout.show(view.contentPanel, "Add Transaction");
            }
        });
        this.view.updateDeleteTransaction.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout layout = (CardLayout) view.contentPanel.getLayout();
                layout.show(view.contentPanel, "Update Delete Transaction");
            }
        });
        this.view.backup.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout layout = (CardLayout) view.contentPanel.getLayout();
                layout.show(view.contentPanel, "Backup");
            }
        });

    }

}
