package engine.Graphics;

import engine.Graphics.View;

import java.awt.*;

/**
 *  a view with nothing but buttons, centered on the screen
 *  see view
 */
public class MenuView extends View {
    public MenuView(String name) {
        super(name);
        this.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridy = 1;
        //con.ipady = 20;
        //con.ipadx = 20;
        //buttonPanel.setBackground(Color.GREEN);
        //buttonPanel.setForeground(Color.GREEN);
        this.add(buttonPanel, con);
        draw();
    }

    @Override
    public void draw() {
        System.out.println("MenuView");
        buttonPanel.setLayout(new GridLayout(getButtonCount(),1,0,0));
        repaint();
    }

    @Override
    public void draw(long timeStamp) {

    }

    @Override
    public void updateSize(int width, int height) {
        //this.setSize(new Dimension(width,height));aa
        buttonPanel.setPreferredSize(new Dimension(500, (height/12)*getButtonCount()));
        buttonPanel.setMinimumSize(new Dimension(1, (height/12)*getButtonCount()));
        //buttonPanel.setMinimumSize();
        draw();
    }
}
