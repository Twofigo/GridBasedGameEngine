package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class BoardView extends View {
    private BoardCanvas boardC;

    public BoardView(String[] options, ActionListener[] actions, Board b) {
        super(options, actions);
        boardC = new BoardCanvas(b);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,buttons.length));
        for(int k=0;k<buttons.length; k++){
            buttonPanel.add(buttons[k]);
        }

        panel.add(boardC);
        panel.add(buttonPanel);
    }

    @Override
    protected void setSize(int width, int height) {
        return;
    }
}

class BoardCanvas extends Canvas{
    Board b;

    public BoardCanvas(Board b) {
        this.b = b;
    }

    @Override
    public void paint(Graphics g){

    }
}
