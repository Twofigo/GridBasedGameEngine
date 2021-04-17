package games.TestGame;
import engine.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ShittyGame extends PuppetMaster{
    static PuppetMaster p;
    public static void main(String[] args) {
        new ShittyGame();
    }

    public ShittyGame() {
        super();

        Image img1;
        Image img2;
        Image img3;
        try {
            File f = new File("src/Texture/dc-dngn/floor/bog_green0.png");
            img1 = ImageIO.read(f);

            f = new File("src/Texture/dc-dngn/floor/cobble_blood1.png");
            img2 = ImageIO.read(f);

            f = new File("src/Texture/dc-mon/deep_elf_mage.png");
            img3 = ImageIO.read(f);
        } catch (IOException e) {
            return;
        }
        Tile t1 = new Tile(img1);
        Tile t2 = new Tile(img2);
        Tile t3 = new Tile(img3);


        Board b1 = new Board(10, 10, t1);
        b1.set(t2, 0, 0);

        Board b2 = new Board(10, 10);
        b2.set(t3, 1, 1);
        b2.set(t3, 2, 2);
        b2.set(t3, 3, 3);

        TableTop tb;
        try {
             tb = new TableTop(new Board[]{b1, b2});
        } catch (Exception e) {
            return;
        }

        BoardView bv = new BoardView("mainBoard", new String[]{"hej", "hopp", "snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToMenuView();
            }
        }, null, null}, tb);
        MenuView mv = new MenuView("mainMenu", new String[]{"hej", "hopp", "snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToBoardView();
            }
        }, null, null});
        this.window.addView(bv);
        this.window.addView(mv);

        this.window.setView("mainBoard");
        this.window.setAspect(4, 3);
        this.window.updateSize();
        this.window.lockResize(true);
        this.window.setSize(800, 600);
    }
    public void changeToBoardView(){
        this.window.setView("mainBoard");
    }

    public void changeToMenuView(){
        this.window.setView("mainMenu");
    }
}
