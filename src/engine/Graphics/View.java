package engine.Graphics;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.*;

/**
 * A view is much like it sounds, a view for the window to display
 */
abstract public class View extends JPanel{
    protected JPanel buttonPanel;
    private int buttonCount;
    private final String name;

    /**
     * sets up a view that you get to name, fancy stuff
     * @param name
     */
    public View(String name) {
        this.buttonPanel = new JPanel();
        this.name = name;
        this.buttonCount = 0;
    }

    /**
     * returns name of view
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * resize the view
     * @param width
     * @param height
     */
    public abstract void updateSize(int width, int height);

    /**
     * User defined draw
     */
    public abstract void draw();

    /**
     * @param name
     * @param action
     */
    public void addButton(String name, ActionListener action){
        Button b = new Button(name);
        b.setPreferredSize(new Dimension(0,0));
        b.addActionListener(action);
        buttonCount++;
        buttonPanel.add(b);
    }

    /**
     * @param name
     */
    public void addLabel(String name){
        JLabel l = new JLabel(name);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setPreferredSize(new Dimension(0,0));
        buttonCount++;
        buttonPanel.add(l);
    }

    /**
     * @param name
     * @return
     */
    public boolean removeButton(String name){
        Button b = getButton(name);
        if(b==null) return false;
        buttonPanel.remove(b);
        buttonCount--;
        return true;
    }

    /**
     * @param enabled
     * @param visible
     * @return
     */
    public boolean setButtonState(boolean enabled, boolean visible){
        Button b = getButton(name);
        if(b==null) return false;
        b.setEnabled(enabled);
        b.setVisible(visible);
        return true;
    }

    /**
     * @param visible
     * @return
     */
    public boolean setButtonVisable(boolean visible){
        Button b = getButton(name);
        if(b==null) return false;
        b.setVisible(visible);
        return true;
    }

    /**
     * @param enabled
     * @return
     */
    public boolean setButtonEnabled(boolean enabled){
        Button b = getButton(name);
        if(b==null) return false;
        b.setEnabled(enabled);
        return true;
    }

    /**
     * @param name
     * @return
     */
    public Button getButton(String name){
        Component[] comp = buttonPanel.getComponents();
        Button b;
        for(Component c: comp){
            b  =((Button)c);
            if (b.getLabel().equals(name)){
                return b;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public int getButtonCount() {
        return buttonCount;
    }
}
