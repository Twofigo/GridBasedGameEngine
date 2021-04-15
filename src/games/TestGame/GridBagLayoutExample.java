package games.TestGame;

import java.awt.*;
import javax.swing.JFrame;

public class GridBagLayoutExample extends JFrame {

    public static void main(String[] args) {
        GridBagLayoutExample a = new GridBagLayoutExample();
    }

    public GridBagLayoutExample() {
        setSize(300, 300);
        //setPreferredSize(getSize());
        setBackground(Color.CYAN);


        setLayout(new GridBagLayout());
        setTitle("GridBag Layout");


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new Button("Button 1"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new Button("Button 2"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new Button("Button 3"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new Button("Button 4"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.gridwidth = 2;
        this.add(new Button("Button 5"), gbc);

        setVisible(true);

    }
}