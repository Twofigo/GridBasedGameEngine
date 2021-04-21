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
        th.addTexture("dc-dngn/wall/stone_brick1.png", "wall");
        //System.setProperty("sun.java2d.opengl", "true");

        // level setup
        levels = new ArrayList<Level>();
        createLevel1();
        createLevel2();
        createLevel3();

        p = new Player("Elf");
        victoryProgress = 0;
        setTableTop(levels.get(0));
        levels.get(0).getForeground().place(p,0,0);


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
    public void createLevel1(){
        Level l = new Level(8,8, 4);
        l.setPlayerSpawn(0,0);

        Board bg = l.getBackground();
        Board mg = l.getMiddleground();
        Board fg = l.getForeground();

        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        fg.place(b1,2,2);
        fg.place(b2,3,2);
        fg.place(b3,4,2);
        fg.place(b4,5,2);

        Plate plate = new Plate("plate");
        mg.place(plate,0,7);
        mg.place(plate,2,7);
        mg.place(plate,5,7);
        mg.place(plate,7,7);
        bg.clear(new Tile("Floor1"));
        levels.add(l);
    }
    public void createLevel2(){
        Level l = new Level(11,11, 8);
        l.setPlayerSpawn(5,5);

        Board bg = l.getBackground();
        Board mg = l.getMiddleground();
        Board fg = l.getForeground();

        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        fg.place(b1,2,2);
        fg.place(b2,8,2);
        fg.place(b3,2,8);
        fg.place(b4,8,8);
        Box b5 = new Box("box", "ActiveBox");
        Box b6 = new Box("box", "ActiveBox");
        Box b7 = new Box("box","ActiveBox");
        Box b8 = new Box("box","ActiveBox");
        fg.place(b5,5,4);
        fg.place(b6,4,5);
        fg.place(b7,6,5);
        fg.place(b8,5,6);

        Wall wall = new Wall("wall");
        fg.place(wall,1,1);
        fg.place(wall,2,1);
        fg.place(wall,3,1);
        fg.place(wall,4,1);
        fg.place(wall,6,1);
        fg.place(wall,7,1);
        fg.place(wall,9,1);

        fg.place(wall,9,2);

        fg.place(wall,1,3);
        fg.place(wall,3,3);
        fg.place(wall,4,3);
        fg.place(wall,6,3);
        fg.place(wall,7,3);
        fg.place(wall,9,3);

        fg.place(wall,1,4);
        fg.place(wall,3,4);
        fg.place(wall,7,4);
        fg.place(wall,9,4);

        fg.place(wall,1,6);
        fg.place(wall,3,6);
        fg.place(wall,7,6);
        fg.place(wall,9,6);

        fg.place(wall,1,7);
        fg.place(wall,3,7);
        fg.place(wall,4,7);
        fg.place(wall,6,7);
        fg.place(wall,7,7);
        fg.place(wall,9,7);

        fg.place(wall,1,8);

        fg.place(wall,1,9);
        fg.place(wall,3,9);
        fg.place(wall,4,9);
        fg.place(wall,6,9);
        fg.place(wall,7,9);
        fg.place(wall,8,9);
        fg.place(wall,9,9);

        Plate plate = new Plate("plate");
        mg.place(plate,5,0);
        mg.place(plate,0,5);
        mg.place(plate,10,5);
        mg.place(plate,5,10);

        mg.place(plate,5,2);
        mg.place(plate,2,5);
        mg.place(plate,8,5);
        mg.place(plate,5,8);
        bg.clear(new Tile("Floor2"));
        levels.add(l);
    }
    public void createLevel3(){
        Level l = new Level(8,8, 4);
        l = new Level(8,8, 999);
        l.setPlayerSpawn(0,0);

        Board bg = l.getBackground();
        Board mg = l.getMiddleground();
        Board fg = l.getForeground();

        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        fg.place(b1,3,3);
        fg.place(b2,4,3);
        fg.place(b3,3,4);
        fg.place(b4,2,4);
        l.getBackground().clear(new Tile("Floor2"));
        levels.add(l);
    }
    public void changeLevel(){
        if (currentLevelIndex+1>=levels.size()) return;
        Level l;

        l = ((Level)getTableTop());
        l.getForeground().pickup(p);
        currentLevelIndex++;
        setTableTop(levels.get(currentLevelIndex));
        bv.setTableTop(getTableTop());
        l = ((Level)getTableTop());
        l.getForeground().place(p,l.getPlayerSpawnX(),l.getPlayerSpawnY());
        bv.setOffset(l.width()*0.5, l.height()*0.5);

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
class Level extends BasicTableTop{
    private int playerSpawnX;
    private int playerSpawnY;
    private int victoryCondition;

    public Level(int width, int height, int victoryCondition) {
        super(width, height);
        this.victoryCondition = victoryCondition;
    }

    public int getPlayerSpawnX() {
        return playerSpawnX;
    }

    public void setPlayerSpawn(int x, int y) {
        this.playerSpawnX = x;
        this.playerSpawnY = y;
    }

    public int getPlayerSpawnY() {
        return playerSpawnY;
    }

    public int getVictoryCondition() {
        return victoryCondition;
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
class Wall extends Tile implements MoveInto{
    public Wall(Image texture) {
        super(texture);
    }
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(Sokoban p, Entity e) {
        return false;
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