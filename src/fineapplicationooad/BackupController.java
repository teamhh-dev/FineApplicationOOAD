/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Hamza
 */
public class BackupController
{
    BackupView view;
    Database dao;

    public BackupController(BackupView view, Database dao)
    {
        this.view = view;
        this.dao = dao;
    }
    
    
    public BackupView getView()
    {
        return view;
    }

    public void setView(BackupView view)
    {
        this.view = view;
    }
    
    
     public static void main(String[] args)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BackupController controller=new BackupController(new BackupView(), new Database());
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize((int) screenSize.getWidth() - 275, (int) screenSize.getHeight() - 50);
        frame.add(controller.getView());

        frame.setVisible(true);
    }
    
}
