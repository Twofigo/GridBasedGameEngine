package engine;

import engine.Graphics.Window;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;

public abstract class PuppetMaster extends JFrame implements KeyListener, MouseInputListener, MouseWheelListener {
    private final Window window;
    private TableTop tb;

    /**
     * the puppermaster controlls all events an interactions on the board.
     */
    public PuppetMaster() {
        window = new Window(1, 1);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.addKeyListener(this);
        window.addMouseWheelListener(this);
    }

    /**
     * @return Window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Generally used to call a full board update
     */
    public abstract void update();

    /**
     * Controls all interactions in the game
     * @param i
     * @param e
     * @param x
     * @param y
     * @return boolean
     */
    public boolean interact(Interaction i, Entity e, int x, int y){
        return i.action(this,this.tb,e,x,y);
    }

    /**
     *
     * @param tb
     */
    public void setTableTop(TableTop tb) {
        this.tb = tb;
    }

    /**
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e){}

    /**
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e){}

    /**
     * @return TableTop
     */
    protected TableTop getTableTop() {
        return tb;
    }

    /**
     *
     */
    public void end(){
        
    }


}
