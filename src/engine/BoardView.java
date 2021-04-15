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
        boardC.setSize(width,width);
    }
}

class BoardCanvas extends Canvas{
    Board b;



    public BoardCanvas(Board b) {
        this.setBackground(Color.green);
        this.b = b;
    }

    @Override
    public void paint(Graphics g){

    }
}
