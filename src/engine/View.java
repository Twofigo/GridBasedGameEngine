package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.*;

abstract public class View extends JPanel{

    public View() {
        this.setBackground(Color.blue);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

    }
    protected JPanel makeButtonRow(String[] options, ActionListener[] actions){
        if (options.length!=actions.length) throw new IndexOutOfBoundsException();

        JPanel buttonPanel = new JPanel();
        for(int k=0;k<options.length;k++){
            JButton b = new JButton(options[k]);
            b.addActionListener(actions[k]);
            buttonPanel.add(b);
        }
        return buttonPanel;
    }

    protected abstract void updateSize(int width, int height);
}
