package games.TestGame;
import engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShittyGame {
    public static void main(String[] args) {
        PuppetMaster p = new PuppetMaster(){};
        p.setAspect(3,4);


        Board b = new Board(10,10);
        BoardView bv = new BoardView(new String[]{"hej","hopp","snopp"}, new ActionListener[]{null,null,null},b);

        MenuView mv = new MenuView(new String[]{"hej","hopp","snopp"}, new ActionListener[]{null,null,null});

        p.setView(bv);
    }
}
