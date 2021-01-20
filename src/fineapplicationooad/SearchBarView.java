/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Hamza
 */
public class SearchBarView extends JPanel
{
    Font titleFont = new Font("Calibri", Font.BOLD, 30);
    
    JScrollPane scollPane;
    JTextField searchType;
    JList<String> searchList;


    Font font = new Font("Aspira", Font.BOLD, 20);
    JButton viewButton;

    public SearchBarView()
    {

        this.initGui();

    }

    private void initGui()
    {

        setLayout(new BorderLayout());
//        setBorder(BorderFactory.createTitledBorder(null, "Add Customer", TitledBorder.CENTER, TitledBorder.CENTER, titleFont, Color.BLACK));
//        setBackground(new Color(174, 179, 184));
//        
        
    
        searchType = new JTextField("Type Here", 20);
        searchType.setFont(font);
        searchType.setBackground(Color.BLACK);
        searchType.setForeground(Color.WHITE);

        searchList = new JList<>();
        searchList.setFont(font);

        

//        searchList.scrollRectToVisible(null);
        add(searchType, BorderLayout.NORTH);

        scollPane = new JScrollPane(searchList);

        
        URL iconURL = getClass().getResource("AppData/view.png");
        ImageIcon icon = new ImageIcon(iconURL);
        
        viewButton = new JButton("View", icon);
        viewButton.setFont(font);
        viewButton.setBackground(Color.BLACK);
        viewButton.setForeground(Color.WHITE);
//        button.addMouseListener(new ButtonHoverListener(button));

        add(scollPane, BorderLayout.CENTER);
        add(viewButton, BorderLayout.SOUTH);

    }



}
