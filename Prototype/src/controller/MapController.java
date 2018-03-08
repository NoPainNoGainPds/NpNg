package controller;

import vue.Map;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapController  extends MouseAdapter{
    private Map map;
    public MapController(Map map) {
        // TODO Auto-generated constructor stub
        this.map = map;
    }
    public void mouseClicked(MouseEvent e)
    {
        this.map.setClicked();
        this.map.sendData();
        this.map.repaint();
    }
    public void mouseMoved(MouseEvent e)
    {
        this.map.setMousePos(e.getX(), e.getY());
        //System.out.println("x:"+e.getX()+"y:"+e.getY());
        this.map.refresh();
        this.map.repaint();
    }
}
