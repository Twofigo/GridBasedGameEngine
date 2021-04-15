package games.TestGame;

import java.awt.*;
import javax.swing.*;

public class GridBagLayoutExample extends JFrame {

    public static void main(String[] args) {
        GridBagLayoutExample a = new GridBagLayoutExample();
    }

    public GridBagLayoutExample() {
        setSize(800, 800);
        //setPreferredSize(getSize());

        setBackground(Color.CYAN);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setTitle("GridBag Layout");




        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel p;
        p = new JPanel();
        p.setBackground(Color.blue);
        this.add(p, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        p = new JPanel();
        p.setBackground(Color.red);
        this.add(p, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        p = new JPanel();
        p.setBackground(Color.orange);
        this.add(p, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        p = new JPanel();
        p.setBackground(Color.cyan);
        this.add(p, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.gridwidth = 2;
        p = new JPanel();
        p.setBackground(Color.yellow);
        this.add(p, gbc);

        setVisible(true);

    }
}