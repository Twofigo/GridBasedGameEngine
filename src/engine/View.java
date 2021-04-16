package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.*;

abstract public class View extends JPanel{
    private final String name;
    public View(String name) {
        this.name = name;
        //this.setBackground(Color.blue);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
    protected JPanel makeButtonRow(String[] options, ActionListener[] actions){
        if (options.length!=actions.length) throw new IndexOutOfBoundsException();

        JPanel buttonPanel = new JPanel();
        for(int k=0;k<options.length;k++){
            Button b = new Button(options[k]);
            b.setPreferredSize(new Dimension(0,0));
            b.addActionListener(actions[k]);
            buttonPanel.add(b);
        }
        return buttonPanel;
    }
    public String getName(){
        return name;
    }

    protected abstract void updateSize(int width, int height);
}
