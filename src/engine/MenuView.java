package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{

    public MenuView(String[] options, ActionListener[] actions) {
        super(options, actions);

        buttonPanel.setLayout(new GridLayout(buttons.length,1,0,5));
        panel.add(buttonPanel);
    }

    @Override
    protected void setSize(int width, int height) {
        panel.setSize(new Dimension(width,height));
        buttonPanel.setPreferredSize(new Dimension(width, buttons.length*50));
    }
}
