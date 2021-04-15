package games.TestGame;
import engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShittyGame {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        MenuView mv = new MenuView(new String[]{"hej","hopp","snopp"}, new ActionListener[]{null,null,null});
        mv.show(f);

        Container contentPane = f.getContentPane();
        contentPane.setLayout(new BorderLayout());
        f.setSize(300,300);
        f.setVisible(true);
    }
}
