package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends View{
    private JPanel buttonPanel;
    int buttonCount;

    public MenuView(String[] options, ActionListener[] actions) {
        super();
        buttonPanel = makeButtonRow(options,actions);
        buttonPanel.setLayout(new GridLayout(buttonCount,1,0,5));
        buttonCount = options.length;

        this.add(buttonPanel);
    }

    @Override
    protected void updateSize(int width, int height) {
        this.setSize(new Dimension(width,height));
        buttonPanel.setPreferredSize(new Dimension(width, buttonCount*50));
    }
}
