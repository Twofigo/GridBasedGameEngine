package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{

    private JPanel buttonPanel;
    public MenuView(String[] options, ActionListener[] actions) {
        super(options, actions);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttons.length,1));
        for(int k=0;k<buttons.length; k++){
            buttonPanel.add(buttons[k]);
        }

        buttonPanel.setBackground(Color.green);
        setSize();
        panel.add(buttonPanel);
    }

    @Override
    protected void setSize(int width, int height) {
        buttonPanel.setSize(width/3, height);
        panel.setSize(width,height);
    }
}
