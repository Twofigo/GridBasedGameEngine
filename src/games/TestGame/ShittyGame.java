package games.TestGame;
import engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShittyGame {
    static PuppetMaster p;
    static BoardView bv;
    static MenuView mv;
    public static void main(String[] args) {
        p = new PuppetMaster(){};
        p.setAspect(4,3);


        Board b = new Board(10,10);
        bv = new BoardView(new String[]{"hej","hopp","snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToMenuView();
            }
        },null,null},b);
        mv = new MenuView(new String[]{"hej","hopp","snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToBoardView();
            }
        },null,null});

        p.setView(mv);
    }
    public static void changeToBoardView(){
        p.setView(bv);
    }

    public static void changeToMenuView(){
        p.setView(mv);
    }
}
