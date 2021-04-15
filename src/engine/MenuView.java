package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{

    private JPanel buttonPanel;
    public MenuView(String[] options, ActionListener[] actions) {
        super(options, actions);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttons.length,1,10,10));
        for(int k=0;k<buttons.length; k++){
            buttonPanel.add(buttons[k]);
        }

        buttonPanel.setBackground(Color.green);
        buttonPanel.setSize(200,800);
        panel.setSize(200,400);
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    protected void setSize(int width, int height) {
        panel.setSize(width,height);

        for(JButton b : buttons){
            b.setPreferredSize(new Dimension(width/3,((height)/buttons.length)-10));
        }
        buttonPanel.setSize(width/3, height);
    }
}
