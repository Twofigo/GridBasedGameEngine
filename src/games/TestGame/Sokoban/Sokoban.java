package games.TestGame.Sokoban;

import engine.*;
import engine.Window;
import games.TestGame.ShittyGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Sokoban extends PuppetMaster {
    public final moveTo MOVETO = new moveTo();

    private Player p;

    public static void main(String[] args) {
        new Sokoban();
    }

    public Sokoban(){
        super();
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("src/Texture/");
        th.setDefaultTexture("effect/sling_bullet0.png");
        th.addTexture("dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("spells/air/chain_lightning.png", "Pow!");

        BasicTableTop tb = new BasicTableTop(12,12);
        setTableTop(tb);

        p = new Player("Elf");
        Box b1 = new Box("Pow!");
        Box b2 = new Box("Pow!");
        Box b3 = new Box("Pow!");
        tb.getMiddleground().place(p,2,2);
        tb.getMiddleground().place(b1,1,3);
        tb.getMiddleground().place(b2,3,3);
        tb.getMiddleground().place(b3,5,3);
        tb.getBackground().clear(new Tile("Floor1"));

        BoardView bv = new BoardView("mainBoard", tb);
        Window win = getWindow();
        win.addView(bv);

        win.setView("mainBoard");
        win.setAspect(4, 3);
        win.lockResize(true);
        win.updateSize(800, 600+40);
        win.draw();
    }
    @Override
    public void update() {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if(c=='w'){
            interact(MOVETO,p, p.getX(), p.getY()-1);
        }
        else if(c=='a'){
            interact(MOVETO,p, p.getX()-1, p.getY());
        }
        else if(c=='s'){
            interact(MOVETO,p, p.getX(), p.getY()+1);
        }
        else if(c=='d'){
            interact(MOVETO,p, p.getX()+1, p.getY());
        }
        getWindow().draw();
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
interface MoveInto{
    public boolean moveInto(Sokoban p, Entity e);
}

class Player extends Entity{

    public Player(Image texture) {
        super(texture);
    }
    public Player(String name) {
        super(name);
    }

    @Override
    public void update() {

    }
}
class Box extends Entity implements MoveInto{

    public Box(Image texture) {
        super(texture);
    }
    public Box(String name) {
        super(name);
    }

    @Override
    public void update() {

    }

    public boolean moveInto(Sokoban p, Entity e){
        // push
        Sokoban game = ((Sokoban)p);
        int x = this.getX()*2 - e.getX();
        int y = this.getY()*2 - e.getY();
        return game.interact(game.MOVETO,this,x,y);
    }
}
class moveTo extends Interaction{
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        Sokoban game = ((Sokoban)p);
        BasicTableTop world = ((BasicTableTop)tb);
        Board b = world.getMiddleground();
        if(b.OutOfBounds(x,y)) return false;
        Tile t = b.get(x,y);
        if(t==null){
            b.pickup(e);
            b.place(e,x,y);
            return true;
        }
        if(t instanceof MoveInto){
            if(((MoveInto)t).moveInto(game,e)){
                b.pickup(e);
                b.place(e,x,y);
                return true;
            }
        }
        return false;
    }
}