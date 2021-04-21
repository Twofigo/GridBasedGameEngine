package games.TestGame.Dungeon;

import engine.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Dungeon extends PuppetMaster {
    public final MoveTo MOVETO = new MoveTo();

    private Player player;
    private BoardView boardView;
    private Level level;


    public static void main(String[] args) {
        new Dungeon();
    }

    public Dungeon(){
        super();
        // resource setup
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("src/Texture/");
        th.setDefaultTexture("effect/sling_bullet0.png");
        th.addTexture("dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("dc-dngn/wall/stone_brick1.png", "wall");
        //System.setProperty("sun.java2d.opengl", "true");

        // level setup
        player = new Player("Elf");
        level = createLevel(1);
        level.getMiddleground().place(player,0,0);
        this.setTableTop(level);

        // window setup
        boardView = new BoardView("mainBoard", level);
        Window win = getWindow();
        win.addView(boardView);

        //bv.setZoom(20);
        win.setView("mainBoard");
        win.setAspect(4, 3);
        win.lockResize(true);
        win.updateSize(800, 600+40);
        win.draw();
    }
    public Level createLevel(int difficulty){
        Level l = new Level(8,8);
        l.getBackground().clear(new Tile("Floor1"));
        return l;
    }
    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        int x = player.getX();
        int y = player.getY();
        if(c=='w') y-=1;
        else if(c=='a') x-=1;
        else if(c=='s') y+=1;
        else if(c=='d') x+=1;
        interact(MOVETO,player, x, y);
        //bv.setOffset(p.getX()-0.5, p.getY()-0.5);
        getWindow().draw();
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}