package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class BoardView extends View {
    private JPanel buttonPanel;
    int buttonCount;

    private BoardCanvas boardC;

    public BoardView(String[] options, ActionListener[] actions, Board b) {
        super();
        buttonPanel = makeButtonRow(options,actions);
        buttonPanel.setLayout(new GridLayout(1,buttonCount,0,0));
        buttonCount = options.length;

        boardC = new BoardCanvas(b);

        this.add(boardC);
        this.add(buttonPanel);
    }

    @Override
    protected void updateSize(int width, int height) {
        this.setSize(new Dimension(width,height));
        boardC.setPreferredSize(new Dimension(width, width));
        buttonPanel.setPreferredSize(new Dimension(width, height-width-40));
    }
}

class BoardCanvas extends JPanel{
    Board b;

    public BoardCanvas(Board b) {
        this.setBackground(Color.green);
        this.b = b;
    }

    @Override
    public void paint(Graphics g){
        g.drawRect(5,5,100,100);
        g.setColor(Color.green);
    }
}
