/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Hamza
 */
public class RecordView extends JPanel
{

    Font titleFont = new Font("Calibri", Font.BOLD, 30);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JScrollPane tableContainer;
    JTable recordTable;

    

    public RecordView()
    {
        intiGui();

    }

    private void intiGui()
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(null, "View Record", TitledBorder.CENTER, TitledBorder.CENTER, titleFont, Color.BLACK));
        setBackground(new Color(174, 179, 184));

        recordTable = new JTable();
//        recordTable = new JTable(data, columnNames);
        recordTable.setFont(titleFont.deriveFont(Font.PLAIN, 22));
        recordTable.setRowHeight(recordTable.getRowHeight()+20);
        
        tableContainer = new JScrollPane(recordTable);
        
        tableContainer.setPreferredSize(new Dimension((int) screenSize.getWidth() - 700, (int) screenSize.getHeight() - 50));
        
        add(tableContainer, BorderLayout.EAST);
//        add(new SearchBarView(),BorderLayout.WEST);
    }

//    public static void main(String[] args)
//    {
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        JFrame frame = new JFrame();
//        frame.setLayout(new BorderLayout());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        frame.setSize((int) screenSize.getWidth() - 275, (int) screenSize.getHeight() - 50);
//        frame.add(new RecordView());
//
//        frame.setVisible(true);
//    }

}
