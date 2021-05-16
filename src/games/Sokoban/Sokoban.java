package games.Sokoban;

import engine.*;
import engine.Graphics.BoardRenderer;
import engine.Graphics.BoardView;
import engine.tools.TextureHandler;
import engine.Graphics.Window;

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
        Sokoban s = new Sokoban();
        assert(s!=null);
        assert(true!=false);
        assert(1+1==2);
    }

    public Sokoban(){
        super();
        // resource setup

        boolean b;
        TextureHandler th = TextureHandler.getInstance();
        assert (th!=null);
        th.setRootPath("src/Texture/");
        b=th.setDefaultTexture("effect/sling_bullet0.png");
        assert(b);
        b=th.addTexture("dc-dngn/floor/bog_green0.png", "Floor1");
        assert(b);
        b=th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");
        assert(b);
        b=th.addTexture("dc-mon/deep_elf_mage.png", "Elf");
        assert(b);
        b=th.addTexture("dc-dngn/wall/stone2_gray0.png", "box");
        assert(b);
        b=th.addTexture("dc-dngn/wall/crystal_wall02.png", "ActiveBox");
        assert(b);
        b=th.addTexture("dc-dngn/floor/tutorial_pad.png", "plate");
        assert(b);
        b=th.addTexture("dc-dngn/wall/stone_brick1.png", "wall");
        assert(b);
        //System.setProperty("sun.java2d.opengl", "true");

        // level setup
        levels = new ArrayList<Level>();
        createLevel1();
        createLevel2();
        createLevel3();
        assert(levels.size()==3);

        p = new Player("Elf");
        victoryProgress = 0;
        assert(levels.get(0)!=null);
        setTableTop(levels.get(0));
        b=levels.get(0).getForeground().place(p,0,0);
        assert(b);

        // window setup
        bv = new BoardView("mainBoard", levels.get(0));
        Window win = getWindow();
        assert(win!=null);
        win.addView(bv);

        //bv.setZoom(20);
        b=win.setView("mainBoard");
        assert(b);
        win.setAspect(4, 3);
        win.lockResize(true);
        win.updateSize(800, 600+40);
        win.draw();
    }
    public void createLevel1(){
        Level l = new Level(8,8, 4);
        l.setPlayerSpawn(0,0);
        assert(l.getPlayerSpawnX()==0 && l.getPlayerSpawnY()==0);


        assert(levels!=null);

        Board bg = l.getBackground();
        Board mg = l.getMiddleground();
        Board fg = l.getForeground();
        assert(bg!=null);
        assert(mg!=null);
        assert(fg!=null);

        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        boolean b;
        b=fg.place(b1,2,2);
        assert(b); // placement successful
        b=fg.place(b2,3,2);
        assert(b); // placement successful
        b=fg.place(b3,4,2);
        assert(b); // placement successful
        b=fg.place(b4,5,2);
        assert(b); // placement successful


        Plate plate = new Plate("plate");
        b=mg.place(plate,0,7);
        assert(b); // placement successful
        b=mg.place(plate,2,7);
        assert(b); // placement successful
        b=mg.place(plate,5,7);
        assert(b); // placement successful
        b=mg.place(plate,7,7);
        assert(b); // placement successful
        bg.clear(new Tile("Floor1"));

        assert(fg.get(l.getPlayerSpawnX(), l.getPlayerSpawnY())==null);
        levels.add(l);
    }
    public void createLevel2(){
        Level l = new Level(11,11, 8);
        l.setPlayerSpawn(5,5);
        assert(l.getPlayerSpawnX()==5 && l.getPlayerSpawnY()==5);
        assert(levels!=null);

        Board bg = l.getBackground();
        Board mg = l.getMiddleground();
        Board fg = l.getForeground();
        assert(bg!=null);
        assert(mg!=null);
        assert(fg!=null);


        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        Box b5 = new Box("box", "ActiveBox");
        Box b6 = new Box("box", "ActiveBox");
        Box b7 = new Box("box","ActiveBox");
        Box b8 = new Box("box","ActiveBox");

        boolean b;

        b=fg.place(b1,2,2);
        assert(b);
        b=fg.place(b2,8,2);
        assert(b);
        b=fg.place(b3,2,8);
        assert(b);
        b=fg.place(b4,8,8);
        assert(b);
        b=fg.place(b5,5,4);
        assert(b);
        b=fg.place(b6,4,5);
        assert(b);
        b=fg.place(b7,6,5);
        assert(b);
        b=fg.place(b8,5,6);
        assert(b);

        Wall wall = new Wall("wall");
        b=fg.place(wall,1,1);
        assert(b);
        b=fg.place(wall,2,1);
        assert(b);
        b=fg.place(wall,3,1);
        assert(b);
        b=fg.place(wall,4,1);
        assert(b);
        b=fg.place(wall,6,1);
        assert(b);
        b=fg.place(wall,7,1);
        assert(b);
        b=fg.place(wall,9,1);
        assert(b);

        b=fg.place(wall,9,2);
        assert(b);

        b=fg.place(wall,1,3);
        assert(b);
        b=fg.place(wall,3,3);
        assert(b);
        b=fg.place(wall,4,3);
        assert(b);
        b=fg.place(wall,6,3);
        assert(b);
        b=fg.place(wall,7,3);
        assert(b);
        b=fg.place(wall,9,3);
        assert(b);

        b=fg.place(wall,1,4);
        assert(b);
        b=fg.place(wall,3,4);
        assert(b);
        b=fg.place(wall,7,4);
        assert(b);
        b=fg.place(wall,9,4);
        assert(b);

        b=fg.place(wall,1,6);
        assert(b);
        b=fg.place(wall,3,6);
        assert(b);
        b=fg.place(wall,7,6);
        assert(b);
        b=fg.place(wall,9,6);
        assert(b);

        b=fg.place(wall,1,7);
        assert(b);
        b=fg.place(wall,3,7);
        assert(b);
        b=fg.place(wall,4,7);
        assert(b);
        b=fg.place(wall,6,7);
        assert(b);
        b=fg.place(wall,7,7);
        assert(b);
        b=fg.place(wall,9,7);
        assert(b);

        b=fg.place(wall,1,8);
        assert(b);

        b=fg.place(wall,1,9);
        assert(b);
        b=fg.place(wall,3,9);
        assert(b);
        b=fg.place(wall,4,9);
        assert(b);
        b=fg.place(wall,6,9);
        assert(b);
        b=fg.place(wall,7,9);
        assert(b);
        b=fg.place(wall,8,9);
        assert(b);
        b=fg.place(wall,9,9);
        assert(b);

        Plate plate = new Plate("plate");
        b=mg.place(plate,5,0);
        assert(b);
        b=mg.place(plate,0,5);
        assert(b);
        b=mg.place(plate,10,5);
        assert(b);
        b=mg.place(plate,5,10);
        assert(b);

        b=mg.place(plate,5,2);
        assert(b);
        b=mg.place(plate,2,5);
        assert(b);
        b=mg.place(plate,8,5);
        assert(b);
        b=mg.place(plate,5,8);
        assert(b);
        bg.clear(new Tile("Floor2"));

        levels.add(l);
    }
    public void createLevel3(){
        Level l = new Level(8,8, 4);
        l = new Level(8,8, 999);
        l.setPlayerSpawn(0,0);
        assert(l.getPlayerSpawnX()==0 && l.getPlayerSpawnY()==0);

        assert(levels!=null);

        Board bg = l.getBackground();
        Board mg = l.getMiddleground();
        Board fg = l.getForeground();
        assert(bg!=null);
        assert(mg!=null);
        assert(fg!=null);


        Box b1 = new Box("box", "ActiveBox");
        Box b2 = new Box("box", "ActiveBox");
        Box b3 = new Box("box","ActiveBox");
        Box b4 = new Box("box","ActiveBox");
        boolean b;

        b=fg.place(b1,3,3);
        assert(b);
        b=fg.place(b2,4,3);
        assert(b);
        b=fg.place(b3,3,4);
        assert(b);
        b=fg.place(b4,2,4);
        assert(b);
        l.getBackground().clear(new Tile("Floor2"));
        levels.add(l);
    }
    public void changeLevel(){
        boolean b;

        assert(bv!=null);
        BoardRenderer br = bv.getBoardRenderer();
        assert(br!=null);

        if (currentLevelIndex+1>=levels.size()) return;
        Level l;

        l = ((Level)getTableTop());
        assert(l!=null);
        assert(p!=null);
        int x=p.getX();
        int y=p.getY();
        Tile e2 = l.getForeground().pickup(p);
        assert(l.getForeground().get(x,y)==null); // pickup successful
        assert(e2==p); // picked up correct object

        currentLevelIndex++;
        assert(levels.get(currentLevelIndex)!=null);
        l = levels.get(currentLevelIndex);
        setTableTop(l);
        br.setTableTop(l);
        assert(l!=null);

        b = l.getForeground().place(p,l.getPlayerSpawnX(),l.getPlayerSpawnY());
        assert(b);
        assert(l.getForeground().get(l.getPlayerSpawnX(),l.getPlayerSpawnY())==p);
        assert(p.getX()==l.getPlayerSpawnX() && p.getY()==l.getPlayerSpawnY());

        assert(l.width()!=0 && l.height()!=0);
        br.setOffset(l.width()*0.5, l.height()*0.5);
        br.setZoom(l.width());

    }
    public void updateVictoryProgress(int i){
        this.victoryProgress+=i;
    }

    @Override
    public void update() {
        Level l = ((Level)getTableTop());
        assert(l!=null);
        assert(l.getVictoryCondition()>0);
        if (victoryProgress>=l.getVictoryCondition()){
            victoryProgress=0;
            changeLevel();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        assert(p!=null);
        assert(MOVETO!=null);
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