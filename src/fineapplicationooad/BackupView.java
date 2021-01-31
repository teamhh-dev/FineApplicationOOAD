/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Hamza
 */
public class BackupView extends JPanel
{

    Font titleFont = new Font("Calibri", Font.BOLD, 30);

    JButton local;
//    JButton drive;

    public BackupView()
    {

        intiGui();

    }

    private void intiGui()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(null, "BACKUP", TitledBorder.CENTER, TitledBorder.CENTER, titleFont, Color.BLACK));
        setBackground(Color.GRAY);

        URL iconUrl = getClass().getResource("AppData/local.png");
        ImageIcon icon = new ImageIcon(iconUrl);

        local = new JButton("Select Folder...", icon);
        local.setBorder(null);
        local.setBackground(Color.GRAY);
        local.setFont(titleFont);
        local.setForeground(Color.WHITE);
        local.addMouseListener(new ButtonHoverListener(local));



        local.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 200)));

        add(local);


    }

}
