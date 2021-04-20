package games.TestGame.Sokoban;

import engine.*;
import engine.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Sokoban extends PuppetMaster {
    public final MoveTo MOVETO = new MoveTo();
    public final Actuate ACTUATE = new Actuate();

    private Player p;
    private int victoryProgress;
    private ArrayList<Level> levels;
    private int currentLevelIndex;
    private BoardView bv;


    public static void main(String[] args) {
        new Sokoban();
    }

    public Sokoban(){
        super();
        // resource setup
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("src/Texture/");
        th.setDefaultTexture("effect/sling_bullet0.png");
        th.addTexture("dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("dc-dngn/wall/stone2_gray0.png", "box");
        th.addTexture("dc-dngn/wall/crystal_wall02.png", "ActiveBox");
        th.addTexture("dc-dngn/floor/tutorial_pad.png", "plate");
        //System.setProperty("sun.java2d.opengl", "true");

        // level setup
        p = new Player("Elf");
        victoryProgress = 0;

        levels = new ArrayList<Level>();
        Level l = new Level(8,8, 4);

        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        l.getForeground().place(b1,2,2);
        l.getForeground().place(b2,3,2);
        l.getForeground().place(b3,4,2);
        l.getForeground().place(b4,5,2);
        Plate plate = new Plate("plate");
        l.getMiddleground().place(plate,0,7);
        l.getMiddleground().place(plate,2,7);
        l.getMiddleground().place(plate,5,7);
        l.getMiddleground().place(plate,7,7);
        l.getBackground().clear(new Tile("Floor1"));

        levels.add(l);
        setTableTop(l);
        l.getForeground().place(p,0,0);

        l = new Level(8,8, 999);
        b1 = new Box("box", "ActiveBox");
        b2 = new Box("box", "ActiveBox");
        b3 = new Box("box","ActiveBox");
        b4 = new Box("box","ActiveBox");
        l.getForeground().place(b1,3,3);
        l.getForeground().place(b2,4,3);
        l.getForeground().place(b3,3,4);
        l.getForeground().place(b4,2,4);
        l.getBackground().clear(new Tile("Floor2"));
        levels.add(l);

        // window setup
        bv = new BoardView("mainBoard", levels.get(0));
        Window win = getWindow();
        win.addView(bv);

        //bv.setZoom(20);
        win.setView("mainBoard");
        win.setAspect(4, 3);
        win.lockResize(true);
        win.updateSize(800, 600+40);
        win.draw();
    }
    public void changeLevel(){
        if (currentLevelIndex+1>=levels.size()) return;
        ((Level)getTableTop()).getForeground().pickup(p);
        currentLevelIndex++;
        setTableTop(levels.get(currentLevelIndex));
        bv.setTableTop(getTableTop());
        ((Level)getTableTop()).getForeground().place(p,0,0);
    }

    public void updateVictoryProgress(int i){
        this.victoryProgress+=i;
    }

    @Override
    public void update() {
        Level l = ((Level)getTableTop());
        if (victoryProgress>=l.getVictoryCondition()){
            victoryProgress=0;
            changeLevel();
        }
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
class Level extends BasicTableTop{
    private int victoryCondition;

    public Level(int width, int height, int victoryCondition) {
        super(width, height);
        this.victoryCondition = victoryCondition;
    }

    public int getVictoryCondition() {
        return victoryCondition;
    }
}
class Box extends Entity implements MoveInto{
    private boolean active;

    private Image neutralTexure;
    private Image activeTexture;
    public Box(Image texture, Image activeTexture) {
        super(texture);
        this.neutralTexure = texture;
        this.activeTexture = activeTexture;
        this.active = false;
    }
    public Box(String textureName, String activeTextureName) {
        super(textureName);
        this.neutralTexure = TextureHandler.getInstance().getTexture(textureName);
        this.activeTexture = TextureHandler.getInstance().getTexture(activeTextureName);
        this.active = false;
    }

    @Override
    public void update() {

    }
    public boolean moveInto(Sokoban p, Entity e){
        if(e instanceof Box){return false;}
        Sokoban game = ((Sokoban)p);
        int x = this.getX()*2 - e.getX();
        int y = this.getY()*2 - e.getY();
        if(!game.interact(game.MOVETO,this,x,y)) return false;
        game.interact(game.ACTUATE,this,x,y);
        return true;
    }
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            this.setTexture(activeTexture);
        }
        else {
            this.setTexture(neutralTexure);
        }
    }
    public boolean isActive() {
        return active;
    }
}
class Plate extends Tile implements MoveInto{
    public Plate(Image texture) {
        super(texture);
    }
    public Plate(String textureName){
        super(textureName);
    }
    @Override
    public boolean moveInto(Sokoban p, Entity e) {
        return true;
    }
}
class MoveTo extends Interaction{
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        Sokoban game = ((Sokoban)p);
        BasicTableTop world = ((BasicTableTop)tb);
        Board b = world.getForeground();
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
class Actuate extends Interaction{
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        if (!(e instanceof Box)) return false;
        Box box = (Box)e;
        Sokoban game = ((Sokoban)p);
        BasicTableTop world = ((BasicTableTop)tb);
        Board b = world.getMiddleground();
        Tile floor = b.get(x,y);
        if (floor instanceof Plate){
            if(!box.isActive()){
                box.setActive(true);
                ((Sokoban) p).updateVictoryProgress(1);
            }
            return true;
        }
        else if (box.isActive()){
            box.setActive(false);
            ((Sokoban) p).updateVictoryProgress(-1);
        }
        return false;
    }
}