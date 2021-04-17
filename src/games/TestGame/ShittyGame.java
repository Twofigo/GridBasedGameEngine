package games.TestGame;
import engine.*;
import engine.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("src/Texture/");
        th.setDefaultTexture("effect/sling_bullet0.png");
        th.addTexture("dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("dc-mon/deep_elf_mage.png", "Elf");

        Tile t1 = new Tile("Floor1");
        Tile t2 = new Tile("Floor2");
        Tile t3 = new Tile("Elf");


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

        Window window = getWindow();
        window.addView(bv);
        window.addView(mv);

        window.setView("mainBoard");
        window.setAspect(4, 3);
        window.updateSize();
        window.lockResize(true);
        window.setSize(800, 600+40);
        //bv.setOffset(3.5,5);
        //bv.setZoom(1.5);
    }
    public void changeToBoardView(){
        getWindow().setView("mainBoard");
    }

    public void changeToMenuView(){
        getWindow().setView("mainMenu");
    }
}
