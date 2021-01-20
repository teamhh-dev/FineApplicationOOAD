/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Hamza
 */
public class CustomerView extends JPanel
{

    Font labelFont = new Font("Calibri", Font.BOLD, 24);
    Font titleFont = new Font("Calibri", Font.BOLD, 30);
    Font textFont = new Font("Calibri", Font.PLAIN, 24);

    JLabel name;
    JLabel address;
    JLabel phoneNo;
    JLabel description;

    JTextField nameText;
    JTextField addressText;
    JTextField phoneNoText;
    JTextArea descriptionText;

    JPanel nameAndTextField;
    JPanel addressAndTextField;
    JPanel phoneNoAndTextField;
    JPanel descritpionAndTextArea;

    Color contentPanelColor = new Color(174, 179, 184);

    JButton addButton;

    public CustomerView()
    {
        initComponents();
        intitGui();
    }

    private void initComponents()
    {

        URL iconURL = getClass().getResource("AppData/add.png");
        ImageIcon icon = new ImageIcon(iconURL);
        addButton = new JButton("Add", icon);
        addButton.setFont(titleFont);
//        addButton.setBorder(null);
//        addButton.setBackground(contentPanelColor);
        addButton.setBackground(new Color(15, 48, 87));
        addButton.setForeground(new Color(255,255,255));
//        addButton.addMouseListener(new ButtonHoverListener(addButton));
        
        name = new JLabel("Enter Name:");
        address = new JLabel("Enter Address:");
        phoneNo = new JLabel("Enter Phone Number:");
        description = new JLabel("Enter Description:");

        name.setFont(labelFont);
        address.setFont(labelFont);
        phoneNo.setFont(labelFont);
        description.setFont(labelFont);

        nameText = new JTextField(15);
        addressText = new JTextField("None",15);
        phoneNoText = new JTextField("None",15);
        descriptionText = new JTextArea("None", 5, 20);
        

        nameText.setFont(textFont);
        addressText.setFont(textFont);
        phoneNoText.setFont(textFont);
        descriptionText.setFont(textFont);
//        descriptionText.setMaximumSize(descriptionText.getPreferredSize());
        

        int labelAndTextWidth = nameText.getPreferredSize().width + name.getPreferredSize().width + 200;
        int labelAndTextHeight = nameText.getPreferredSize().height + 50;

        Dimension labelAndTextSize = new Dimension(labelAndTextWidth, labelAndTextHeight);
        nameAndTextField = new JPanel(new FlowLayout());
        nameAndTextField.setMaximumSize(labelAndTextSize);
        nameAndTextField.add(name);
        nameAndTextField.add(nameText);
        nameAndTextField.setBackground(contentPanelColor);

        addressAndTextField = new JPanel(new FlowLayout());
        addressAndTextField.add(address);
        addressAndTextField.setMaximumSize(labelAndTextSize);
        addressAndTextField.add(addressText);
        addressAndTextField.setBackground(contentPanelColor);

        phoneNoAndTextField = new JPanel(new FlowLayout());
        phoneNoAndTextField.add(phoneNo);
        phoneNoAndTextField.setMaximumSize(labelAndTextSize);
        phoneNoAndTextField.add(phoneNoText);
        phoneNoAndTextField.setBackground(contentPanelColor);

        descritpionAndTextArea = new JPanel(new FlowLayout());
        descritpionAndTextArea.add(description);
        descritpionAndTextArea.setMaximumSize(new Dimension(700, 160));
        descritpionAndTextArea.add(descriptionText);
        descritpionAndTextArea.setBackground(contentPanelColor);

    }

    private void intitGui()
    {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(null, "Add Customer", TitledBorder.CENTER, TitledBorder.CENTER, titleFont, Color.BLACK));
        setBackground(new Color(174, 179, 184));

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(nameAndTextField);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(addressAndTextField);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(phoneNoAndTextField);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(descritpionAndTextArea);
        
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(addButton);

    }

//    public static void main(String[] args)
//    {
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        JFrame frame = new JFrame();
//        frame.setLayout(new BorderLayout());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.setSize((int) screenSize.getWidth() - 275, (int) screenSize.getHeight()-50);
//        frame.add(new CustomerView());
//
//        frame.setVisible(true);
//    }
}
