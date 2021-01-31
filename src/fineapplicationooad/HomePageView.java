/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hamza
 */
public class HomePageView
{

    JFrame frame;

    JPanel menuPanel;
    JPanel contentPanel;

    //content panel layout panels
//    JPanel addCustomerPanel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Font buttonsFont = new Font("Calibri", Font.BOLD, 24);

    JLabel tyreIcon;
    JButton addCustomer;
    JButton viewRecord;
    JButton addTransaction;
    JButton updateDeleteTransaction;
    
    JButton backup;
    int menuPanelWidth=275;
    
    public HomePageView()
    {
        initGui();
    }

    private void initGui()
    {

        intitMenuPanelComponents();
        initMenuPanel();

        
        intitContentPanel();

        initMainFrame();
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.EAST);

    }

    private void initMainFrame()
    {

        frame = new JFrame("Tyre Managment System");
        frame.setLayout(new BorderLayout());
        try
        {
            URL iconURL = getClass().getResource("AppData/tyreicon.png");
            ImageIcon icon = new ImageIcon(iconURL);
            frame.setIconImage(icon.getImage());

        } catch (Exception e)
        {
            System.out.println("Pic Not Found!");
        }

        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight() - 50);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMenuPanel()
    {

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        menuPanel.setPreferredSize(new Dimension(menuPanelWidth, (int) screenSize.getHeight()));
        menuPanel.setBackground(new Color(15, 48, 87));

        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        menuPanel.add(tyreIcon);

        menuPanel.add(Box.createRigidArea(new Dimension(0, 75)));

        menuPanel.add(addCustomer);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        menuPanel.add(viewRecord);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        menuPanel.add(addTransaction);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        menuPanel.add(updateDeleteTransaction);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        menuPanel.add(backup);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));

    }

    private void intitContentPanel()
    {
        CardLayout contentPanelLayout=new CardLayout();
   
        
        contentPanel = new JPanel(new CardLayout());
        
        contentPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - menuPanelWidth-20, (int) screenSize.getHeight()));
        contentPanel.setBackground(new Color(174, 179, 184));
        
        


        
    }

    private void intitMenuPanelComponents()
    {
        try
        {

            URL iconURL = getClass().getResource("AppData/tyreicon2x.png");
            ImageIcon icon = new ImageIcon(iconURL);
            tyreIcon = new JLabel(icon);

            iconURL = getClass().getResource("AppData/customer.png");
            icon = new ImageIcon(iconURL);
            addCustomer = new JButton("Add Customer", icon);
            addCustomer.setBackground(new Color(15, 48, 87));
            addCustomer.setBorder(null);
            addCustomer.setFont(buttonsFont);
            addCustomer.setForeground(Color.WHITE);

            iconURL = getClass().getResource("AppData/folder.png");
            icon = new ImageIcon(iconURL);
            viewRecord = new JButton("View Record", icon);
            viewRecord.setBackground(new Color(15, 48, 87));
            viewRecord.setBorder(null);
            viewRecord.setFont(buttonsFont);
            viewRecord.setForeground(Color.WHITE);

            iconURL = getClass().getResource("AppData/transaction.png");
            icon = new ImageIcon(iconURL);
            addTransaction = new JButton("Add Transaction", icon);
            addTransaction.setBackground(new Color(15, 48, 87));
            addTransaction.setBorder(null);
            addTransaction.setFont(buttonsFont);
            addTransaction.setForeground(Color.WHITE);
            
            iconURL = getClass().getResource("AppData/updateDelete.png");
            icon = new ImageIcon(iconURL);
            updateDeleteTransaction = new JButton("Update/Delete Transaction", icon);
            updateDeleteTransaction.setBackground(new Color(15, 48, 87));
            updateDeleteTransaction.setBorder(null);
            updateDeleteTransaction.setFont(buttonsFont);
            updateDeleteTransaction.setForeground(Color.WHITE);

            iconURL = getClass().getResource("AppData/backup.png");
            icon = new ImageIcon(iconURL);
            backup = new JButton("Backup", icon);
            backup.setBackground(new Color(15, 48, 87));
            backup.setBorder(null);
            backup.setFont(buttonsFont);
            backup.setForeground(Color.WHITE);

            tyreIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

            addCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
            viewRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
            addTransaction.setAlignmentX(Component.CENTER_ALIGNMENT);
            updateDeleteTransaction.setAlignmentX(Component.CENTER_ALIGNMENT);
            backup.setAlignmentX(Component.CENTER_ALIGNMENT);

            addCustomer.addMouseListener(new ButtonHoverListener(addCustomer));
            viewRecord.addMouseListener(new ButtonHoverListener(viewRecord));
            addTransaction.addMouseListener(new ButtonHoverListener(addTransaction));
            updateDeleteTransaction.addMouseListener(new ButtonHoverListener(updateDeleteTransaction));
            backup.addMouseListener(new ButtonHoverListener(backup));

        } catch (Exception e)
        {
            addCustomer = new JButton("Add Customer");
            viewRecord = new JButton("View Record");
            addTransaction = new JButton("Add Transaction");
            updateDeleteTransaction = new JButton("Update/Delete Transaction");
            backup = new JButton("Backup");
        }

    }

    

    
}
