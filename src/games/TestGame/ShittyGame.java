package games.TestGame;
import engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShittyGame extends PuppetMaster{
    static PuppetMaster p;
    static BoardView bv;
    static MenuView mv;
    public static void main(String[] args) {
        new ShittyGame();
    }

    public ShittyGame() {
        super();
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
        this.window.setView(mv);
        this.window.setAspect(4,3);
        this.window.updateSize();
    }

    public void changeToBoardView(){
        this.window.setView(bv);
    }

    public void changeToMenuView(){
        this.window.setView(mv);
    }
}
