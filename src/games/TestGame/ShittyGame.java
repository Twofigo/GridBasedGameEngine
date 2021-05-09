package games.TestGame;
import engine.*;
import engine.Graphics.*;
import engine.Graphics.Window;
import engine.tools.TextureHandler;
import games.TestGame.Dungeon.World.DungeonGenerator;

import java.awt.*;
import java.awt.event.*;

public class ShittyGame extends PuppetMaster{
    private static PuppetMaster p;
    private BoardView bv;
    private MenuView mv;
    Tile click;
    TableTop tb;
    public static void main(String[] args) {
        new ShittyGame();
    }

    public ShittyGame() {
        super();
        ProceduralGeneration p = new ProceduralGeneration();
        int[][] arr = p.createArray(1,30,30);
        arr = p.createRooms(arr);
        new DungeonGenerator();
        Image img1;
        Image img2;
        Image img3;
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("");
        th.setDefaultTexture("src/Texture/effect/arrow2.png");
        th.addTexture("src/Texture/dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("src/Texture/dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("src/Texture/dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("src/Texture/spells/air/chain_lightning.png", "Pow!");

        Tile t1 = new Tile("Floor1");
        Tile t2 = new Tile("Floor2");
        Tile t3 = new Tile("Elf");
        click = new Tile("Pow!");

        map m = new map(arr,"src/Texture/dc-dngn/wall/tree1_lightred.png", th);

        Board b3 = m.getBoard();

        Board b1 = new Board(30, 30, t1);
        b1.set(t2, 0, 0);

        Board b2 = new Board(30, 30);
        b2.set(t3, 1, 1);
        b2.set(t3, 2, 2);
        b2.set(t3, 3, 3);

        try {
             tb = new TableTop(new Board[]{b1,b2,b3});
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

        Window win = getWindow();
        win.addView(bv);
        win.addView(mv);

        win.setView("mainBoard");
        win.setAspect(4, 3);
        win.lockResize(true);
        win.updateSize(800, 600+40);
        win.repaint();
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
        //tb.update();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        BoardRenderer br = bv.getBoardRenderer();
        int x = br.transY(e.getX());
        int y = br.transX(e.getY());
        System.out.println(x+" : "+y);
        x/=100;
        y/=100;
        tb.getBoard(1).set(click, x,y);
        getWindow().draw();
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
        BoardRenderer br = bv.getBoardRenderer();
        int amount = e.getUnitsToScroll();
        double zoom = br.getZoom() - (br.getZoom()/20)*amount;
        if(zoom<0.1) return;
        if(zoom>50) return;
        br.setZoom(zoom);
        bv.draw();
    }
}
