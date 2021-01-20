/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Hamza
 */
public class ButtonHoverListener implements MouseListener
{

    JButton reference;
    
    Color originalColorBg;
    Color originalColorFg;
    Font originialFont;
    
    public ButtonHoverListener(JButton ref)
    {
        
        reference = ref;
        
        originialFont=ref.getFont();
        originalColorBg=ref.getBackground();
        originalColorFg=ref.getForeground();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
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
        reference.setBackground(new Color(0, 0, 0));
        reference.setForeground(new Color(255, 255, 255));
        reference.setFont(originialFont.deriveFont((float)originialFont.getSize()+8));
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        reference.setBackground(originalColorBg);
        reference.setForeground(originalColorFg);
//        reference.setBackground(new Color(15, 48, 87));
        reference.setFont(originialFont);
        

    }

}
