package utils;

import javax.swing.*;
import java.awt.*;

public class AnimatedCardLayout extends CardLayout {
    public Component getCurrentComponent(Container parent)
    {
        int n = parent.getComponentCount();
        for(int i=0 ; i<n; i++)
        {
            Component panel = parent.getComponent(i);
            if(panel.isVisible())
            {
                return panel;
            }
        }
        return null;
    }
    public Component getNextComponent(Container parent)
    {
        int n = parent.getComponentCount();
        for(int i = 0;i<n;i++)
        {
            Component panel = parent.getComponent(i);
            if(panel.isVisible())
            {
                int current = (i+1)%n;
                panel = parent.getComponent(current);
                return panel;
            }
        }
        return null;
    }
    public Component getPreviousComponent(Container parent)
    {
        int n = parent.getComponentCount();
        for(int i = 0;i<n;i++)
        {
            Component panel = parent.getComponent(i);
            if(panel.isVisible())
            {
                int current = ((i>0) ? i-1 : n-1);
                panel = parent.getComponent(current);
                return panel;
            }
        }
        return null;
    }
}
