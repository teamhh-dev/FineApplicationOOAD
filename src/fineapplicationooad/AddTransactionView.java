/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Hamza
 */
public class AddTransactionView extends JPanel
{

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Font titleFont = new Font("Calibri", Font.BOLD, 30);
    Color labelsColor = new Color(15, 48, 87);

    JPanel transactionPanel;

    JLabel customerLabel;
    JLabel dateLabel;
    JLabel amountLabel;
    JLabel transactionTypeLabel;
    JLabel descriptionLabel;

    JTextField customerName;
    JTextField date;

    JTextField amount;
    JTextField description;

    JRadioButton bill;
    JRadioButton payment;

    JButton addTransactionButton;

    public AddTransactionView()
    {

        initGui();
    }

    private void initGui()
    {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(null, "Add Transaction", TitledBorder.CENTER, TitledBorder.CENTER, titleFont, Color.BLACK));
        setBackground(new Color(174, 179, 184));

        initTransactionPanel();
        add(transactionPanel, BorderLayout.EAST);

    }

    private void initTransactionPanel()
    {

        customerLabel = new JLabel("Customer Name");
        customerLabel.setFont(titleFont);
        customerLabel.setForeground(labelsColor);

        dateLabel = new JLabel("Transaction Date and Time");
        dateLabel.setFont(titleFont);
        dateLabel.setForeground(labelsColor);

        amountLabel = new JLabel("Amount");
        amountLabel.setFont(titleFont);
        amountLabel.setForeground(labelsColor);

        transactionTypeLabel = new JLabel("Transaction Type");
        transactionTypeLabel.setFont(titleFont);
        transactionTypeLabel.setForeground(labelsColor);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(titleFont);
        descriptionLabel.setForeground(labelsColor);

        customerName = new JTextField("Select Customer...", WIDTH);
        customerName.setFont(titleFont);
        customerName.setToolTipText("Select Customer...");
        customerName.setForeground(Color.WHITE);
        customerName.setBackground(Color.BLACK);
        customerName.setEditable(false);

        date = new JTextField(20);
        date.setFont(titleFont);
        date.setToolTipText("System Date");
        date.setForeground(Color.WHITE);
        date.setBackground(Color.BLACK);
        date.setEditable(false);

        amount = new JTextField("", 20);
        amount.setFont(titleFont);
        amount.setToolTipText("Enter Amount");
        amount.setForeground(Color.WHITE);
        amount.setBackground(Color.BLACK);

        description = new JTextField("None", 20);
        description.setFont(titleFont);
        description.setToolTipText("Enter Amount");
        description.setForeground(Color.WHITE);
        description.setBackground(Color.BLACK);

        bill = new JRadioButton("Bill");
        bill.setBorder(null);
        bill.setBackground(Color.GRAY);
        bill.setFont(titleFont);

        payment = new JRadioButton("Payment");
        payment.setBorder(null);
        payment.setBackground(Color.GRAY);
        payment.setFont(titleFont);

        ButtonGroup bg = new ButtonGroup();
        bg.add(bill);
        bg.add(payment);

        bg.setSelected(bill.getModel(), true);

        URL iconURL = getClass().getResource("AppData/add.png");
        ImageIcon icon = new ImageIcon(iconURL);

        addTransactionButton = new JButton("Add", icon);
        addTransactionButton.setFont(titleFont);
        addTransactionButton.setBackground(labelsColor);
        addTransactionButton.setForeground(Color.WHITE);

        transactionPanel = new JPanel();
        transactionPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 700, (int) screenSize.getHeight() - 50));
        transactionPanel.setMaximumSize(new Dimension((int) screenSize.getWidth() - 700, (int) screenSize.getHeight() - 50));
//        transactionPanel.setPreferredSize(transactionPanel.getPreferredSize());

        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.PAGE_AXIS));

        transactionPanel.setBackground(Color.GRAY);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        transactionPanel.add(customerLabel);
        transactionPanel.add(customerName);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        transactionPanel.add(dateLabel);
        transactionPanel.add(date);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        transactionPanel.add(amountLabel);
        transactionPanel.add(amount);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        transactionPanel.add(transactionTypeLabel);
        transactionPanel.add(bill);
        transactionPanel.add(payment);
//        transactionPanel.add(amount);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        transactionPanel.add(descriptionLabel);
        transactionPanel.add(description);
        transactionPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        addTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        transactionPanel.add(addTransactionButton);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 100)));
//        transactionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

    }

}
