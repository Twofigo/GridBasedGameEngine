package engine;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

public abstract class PuppetMaster extends JFrame implements KeyListener, MouseInputListener {
    private final Window window;

    public PuppetMaster() {
        window = new Window(1, 1);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.addKeyListener(this);
    }

    public Window getWindow() {
        return window;
    }

    public abstract void update();
    public abstract boolean interact(Interaction i, Tile obj1, Tile obj2);
    public abstract boolean interact(Interaction i, int x, int y);

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}

}
