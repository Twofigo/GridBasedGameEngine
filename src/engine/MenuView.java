package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{
    private JPanel buttonPanel;
    int buttonCount;

    public MenuView(String name, String[] options, ActionListener[] actions) {
        super(name);
        buttonPanel = makeButtonRow(options,actions);
        buttonPanel.setLayout(new GridLayout(buttonCount,1,0,0));
        buttonCount = options.length;

        this.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        con.fill = GridBagConstraints.BOTH;
        con.gridy = 1;
        con.weightx = 1.0;
        con.weighty = buttonCount/12.0;
        this.add(buttonPanel, con);

        con.weighty = (12-buttonCount)/24.0;
        con.gridy = 0;
        this.add(new JPanel(), con);
        con.gridy = 2;
        this.add(new JPanel(), con);
    }

    @Override
    protected void updateSize(int width, int height) {
        //this.setSize(new Dimension(width,height));
        //buttonPanel.setPreferredSize(new Dimension(width, buttonCount*50));
    }
}
