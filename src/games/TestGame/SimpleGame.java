package games.TestGame;
import engine.*;
import engine.Graphics.*;
import engine.Graphics.Window;
import engine.tools.TextureHandler;

import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends PuppetMaster{
    private static PuppetMaster p;
    private BoardView bv;
    private MenuView mv;
    private SpriteRenderer sr;
    Tile click;
    TableTop tb;
    public static void main(String[] args) {
        new SimpleGame();
    }

    public SimpleGame() {
        super();

        Image img1;
        Image img2;
        Image img3;
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("");
        th.setDefaultTexture("Texture/effect/arrow2.png");
        th.addTexture("Texture/dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("Texture/dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("Texture/dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("Texture/spells/air/chain_lightning.png", "Pow!");

        Tile t1 = new Tile("Floor1");
        Tile t2 = new Tile("Floor2");
        Tile t3 = new Tile("Elf");
        click = new Tile("Pow!");



        Board b1 = new Board(10, 10, t1);
        b1.set(t2, 0, 0);

        Board b2 = new Board(10, 10);
        b2.set(t3, 1, 1);
        b2.set(t3, 2, 2);
        b2.set(t3, 3, 3);

        try {
             tb = new TableTop(new Board[]{b1,b2});
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

        this.sr = new SpriteRenderer(10,10);
        this.sr.setTransformContext(bv.getBoardRenderer().getTransformContext());
        // makes the board and the sprite renderer act with the same transformation
        bv.addRenderer(this.sr);

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
        win.start(25);
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

        FadeSprite sprite = new FadeSprite("Pow!",x,y,1000,System.currentTimeMillis());
        sprite.setSize(200,200);
        this.sr.addSprite(sprite);

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
        double amount = (e.getUnitsToScroll()>1?1:-1)*0.5;
        double zoom = br.getZoom() + amount;
        if (zoom < 1) return;
        if (zoom > 50) return;
        br.setZoom(zoom);
        bv.draw();
    }
}
