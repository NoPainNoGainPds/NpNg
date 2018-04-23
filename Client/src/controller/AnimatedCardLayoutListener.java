package controller;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimatedCardLayoutListener implements ActionListener {
    private Component panel1;
    private Component panel2;
    private int steps;
    private int step=0;
    public Timer timer;
    private CardLayout cl;
    private  Container parent;
    boolean isNext;

    public AnimatedCardLayoutListener(int steps, Component panel1,Component panel2, boolean isNext,CardLayout cl,Container parent)
    {
        this.steps = steps;
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.isNext = isNext;
        this.cl = cl;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle bounds = panel1.getBounds();
        int shift=bounds.width/steps;
        if(this.isNext)
        {
            panel1.setLocation(bounds.x-shift,bounds.y);
            panel2.setLocation(bounds.x-shift+bounds.width,bounds.y);
        }else
        {
            panel1.setLocation(bounds.x+shift,bounds.y);
            panel2.setLocation(bounds.x+shift-bounds.width,bounds.y);
        }
        step++;
        if(step==steps)
        {
            timer.stop();
            panel2.setVisible(false);
            if(this.isNext)
            {
                cl.next(this.parent);
            }else
            {
                cl.previous(this.parent);
            }
        }
    }
}
