package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{
    public MenuView(String name) {
        super(name);
    }
    @Override
    public void setup() {
        setupMenuView();
        draw();
    }
    protected void setupMenuView() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridy = 1;
        //con.ipady = 20;
        //con.ipadx = 20;
        //buttonPanel.setBackground(Color.GREEN);
        //buttonPanel.setForeground(Color.GREEN);
        this.add(buttonPanel, con);
    }

    @Override
    public void draw() {
        System.out.println("MenuView");
        buttonPanel.setLayout(new GridLayout(getButtonCount(),1,0,0));
        repaint();
    }
    @Override
    public void updateSize(int width, int height) {
        //this.setSize(new Dimension(width,height));
        buttonPanel.setPreferredSize(new Dimension(500, (height/12)*getButtonCount()));
        buttonPanel.setMinimumSize(new Dimension(1, (height/12)*getButtonCount()));
        //buttonPanel.setMinimumSize();
        draw();
    }
}
