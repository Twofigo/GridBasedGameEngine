package engine;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

public abstract class PuppetMaster extends JFrame implements KeyListener, MouseInputListener, MouseWheelListener {
    private final Window window;
    private TableTop tb;

    public PuppetMaster() {
        window = new Window(1, 1);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.addKeyListener(this);
        window.addMouseWheelListener(this);
    }

    public Window getWindow() {
        return window;
    }

    public abstract void update();
    public boolean interact(Interaction i, Entity e, int x, int y){
        return i.action(this,this.tb,e,x,y);
    }

    public TableTop getTableTop() {
        return tb;
    }
    public void setTableTop(TableTop tb) {
        this.tb = tb;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}

}
