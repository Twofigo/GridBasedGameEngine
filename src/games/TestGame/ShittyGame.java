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
        bv = new BoardView("mainBoard",new String[]{"hej","hopp","snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToMenuView();
            }
        },null,null},b);
        mv = new MenuView("mainMenu",new String[]{"hej","hopp","snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToBoardView();
            }
        },null,null});
        this.window.addView(bv);
        this.window.addView(mv);

        this.window.setView("mainBoard");
        this.window.setAspect(4,3);
        this.window.updateSize();
        //this.window.lockResize(true);
        //this.window.setSize(800,600);
    }

    public void changeToBoardView(){
        this.window.setView("mainBoard");
    }

    public void changeToMenuView(){
        this.window.setView("mainMenu");
    }
}
