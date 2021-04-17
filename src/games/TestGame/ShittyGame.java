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

        Image img;
        Image img2;
        try{
            File f = new File("src/Texture/dc-dngn/floor/bog_green0.png");
            img = ImageIO.read(f);
            f = new File("src/Texture/dc-dngn/floor/cobble_blood1.png");
            img2 = ImageIO.read(f);
        }
        catch (IOException e){
            return;
        }
        Tile t = new Tile(img2);


        Board b = new Board(10,10, new Tile(img));
        b.set(t,0,0);

        BoardView bv = new BoardView("mainBoard",new String[]{"hej","hopp","snopp"}, new ActionListener[]{new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToMenuView();
            }
        },null,null},b);
        MenuView mv = new MenuView("mainMenu",new String[]{"hej","hopp","snopp"}, new ActionListener[]{new ActionListener() {
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
        this.window.lockResize(true);
        this.window.setSize(800,600);
    }

    public void changeToBoardView(){
        this.window.setView("mainBoard");
    }

    public void changeToMenuView(){
        this.window.setView("mainMenu");
    }
}
