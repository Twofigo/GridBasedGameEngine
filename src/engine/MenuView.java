package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{

    private JPanel buttonPanel;
    public MenuView(String[] options, ActionListener[] actions) {
        super(options, actions);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttons.length,1,0,10));
        for(int k=0;k<buttons.length; k++){
            buttonPanel.add(buttons[k]);
        }

        buttonPanel.setBackground(Color.green);
        panel.setSize(200,400);
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    protected void setSize(int width, int height) {
        /*
        for(JButton b : buttons){
            b.setPreferredSize(new Dimension(width/3,40));
        }*/
        panel.setSize(width,height);
        buttonPanel.setPreferredSize(new Dimension(width/3, height-20));
    }
}
