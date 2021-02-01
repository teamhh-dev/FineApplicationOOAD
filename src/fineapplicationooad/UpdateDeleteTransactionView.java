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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Hamza
 */
public class UpdateDeleteTransactionView extends JPanel
{

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Font titleFont = new Font("Calibri", Font.BOLD, 30);
    Color labelsColor = new Color(15, 48, 87);

    JButton updateButton;
    JButton deleteButton;
    
    JPanel buttonsPanel;
    JTable transactionsTable;
    JScrollPane tableContainer;
    
    
    public UpdateDeleteTransactionView()
    {

        initGui();
    }

    private void initGui()
    {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(null, "Update/Delete Transaction", TitledBorder.CENTER, TitledBorder.CENTER, titleFont, Color.BLACK));
        setBackground(new Color(174, 179, 184));

        
        
        transactionsTable=new JTable();
        transactionsTable.setFont(titleFont.deriveFont(Font.PLAIN, 22));
        transactionsTable.setRowHeight(transactionsTable.getRowHeight()+20);
        
        
        
        tableContainer = new JScrollPane(transactionsTable);
        
        tableContainer.setPreferredSize(new Dimension((int) screenSize.getWidth() - 700, (int) screenSize.getHeight() - 50));
        
        deleteButton = new JButton("Delete");
        deleteButton.setFont(titleFont);
        deleteButton.setBackground(labelsColor);
        deleteButton.setForeground(Color.WHITE);
        
        updateButton = new JButton("Update");
        updateButton.setFont(titleFont);
        updateButton.setBackground(labelsColor);
        updateButton.setForeground(Color.WHITE);
        
        buttonsPanel=new JPanel(new FlowLayout());
        
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(updateButton);
        
        add(tableContainer,BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);
        
        
    }
}
