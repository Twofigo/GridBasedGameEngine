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
        buttonPanel.setLayout(new GridLayout(3,4,0,0));
        buttonCount = options.length;
        boardC = new BoardCanvas(b);

        this.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        con.fill = GridBagConstraints.BOTH;
        con.gridy = 0;
        con.weightx = 1.0;
        con.weighty = 3/4.0;
        this.add(boardC, con);

        con.gridy = 1;
        con.weighty = 1/4.0;
        this.add(buttonPanel, con);

        setVisible(true);
    }

    @Override
    protected void updateSize(int width, int height) {
        //boardC.setPreferredSize(new Dimension(width, width));
        //buttonPanel.setPreferredSize(new Dimension(width, height-width-40));
    }
}

class BoardCanvas extends JPanel{
    Board b;

    public BoardCanvas(Board b) {
        this.b = b;
    }

    @Override
    public void paint(Graphics g){
        g.fillRect(5,5,100,100);
        g.setColor(Color.green);
    }
}
