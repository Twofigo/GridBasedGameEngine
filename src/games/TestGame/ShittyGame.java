package games.TestGame;
import engine.*;
import engine.Window;

import java.awt.*;
import java.awt.event.*;

public class ShittyGame extends PuppetMaster{
    private static PuppetMaster p;
    private BoardView bv;
    private MenuView mv;
    Tile click;
    TableTop tb;
    Window window;

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
        th.addTexture("spells/air/chain_lightning.png", "Pow!");

        Tile t1 = new Tile("Floor1");
        Tile t2 = new Tile("Floor2");
        Tile t3 = new Tile("Elf");
        click = new Tile("Pow!");


        Board b1 = new Board(8, 8, t1);
        b1.set(t2, 0, 0);

        Board b2 = new Board(8, 8);
        b2.set(t3, 1, 1);
        b2.set(t3, 2, 2);
        b2.set(t3, 3, 3);

        try {
             tb = new TableTop(new Board[]{b1, b2});
        } catch (Exception e) {
            return;
        }

        bv = new BoardView("mainBoard", tb);
        mv = new MenuView("mainMenu");

        bv.addButton("Menu", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToMenuView();
            }});
        mv.addButton("Game", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToBoardView();
            }});

        window = getWindow();
        window.addView(bv);
        window.addView(mv);

        window.setView("mainBoard");
        window.setAspect(4, 3);
        window.updateSize();
        window.lockResize(true);
        window.setSize(800, 600+40);
        window.repaint();
        //bv.setOffset(3.5,5);
        //bv.setZoom(0.8);
    }
    private void changeToBoardView(){
        getWindow().setView("mainBoard");
    }
    private void changeToMenuView(){
        getWindow().setView("mainMenu");
    }
    @Override
    public void update() {

    }
    @Override
    public boolean interact(Interaction i, Tile obj1, Tile obj2) {
        return false;
    }
    @Override
    public boolean interact(Interaction i, int x, int y) {
        return false;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = bv.boardTransX(e.getX());
        int y = bv.boardTransY(e.getY());
        System.out.println(x+" : "+y);
        x/=100;
        y/=100;
        tb.getBoard(1).set(click, x,y);
        window.draw();
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int amount = e.getUnitsToScroll();
        double zoom = bv.getZoom() - (bv.getZoom()/20)*amount;
        if(zoom<0.1) return;
        if(zoom>50) return;
        bv.setZoom(zoom);
        bv.draw();
    }
}
