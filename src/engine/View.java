package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.*;

abstract public class View extends JPanel{
    protected JPanel buttonPanel;
    private int buttonCount;
    private final String name;

    public View(String name) {
        this.buttonPanel = new JPanel();
        this.name = name;
        this.buttonCount = 0;
    }
    public String getName(){
        return name;
    }
    public abstract void updateSize(int width, int height);
    public abstract void draw();

    public void addButton(String name, ActionListener action){
        Button b = new Button(name);
        b.setPreferredSize(new Dimension(0,0));
        b.addActionListener(action);
        buttonCount++;
        buttonPanel.add(b);
    }
    public void addLabel(String name){
        JLabel l = new JLabel(name);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setPreferredSize(new Dimension(0,0));
        buttonCount++;
        buttonPanel.add(l);
    }
    public boolean removeButton(String name){
        Button b = getButton(name);
        if(b==null) return false;
        buttonPanel.remove(b);
        buttonCount--;
        return true;
    }
    public boolean setButtonState(boolean enabled, boolean visible){
        Button b = getButton(name);
        if(b==null) return false;
        b.setEnabled(enabled);
        b.setVisible(visible);
        return true;
    }
    public boolean setButtonVisable(boolean visible){
        Button b = getButton(name);
        if(b==null) return false;
        b.setVisible(visible);
        return true;
    }
    public boolean setButtonEnabled(boolean enabled){
        Button b = getButton(name);
        if(b==null) return false;
        b.setEnabled(enabled);
        return true;
    }
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
    public int getButtonCount() {
        return buttonCount;
    }
}
