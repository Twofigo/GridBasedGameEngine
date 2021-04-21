package games.TestGame.Dungeon;

import engine.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Dungeon extends PuppetMaster {
    public final MoveTo MOVETO = new MoveTo();
    public final Pickup PICKUP = new Pickup();

    public Level getLevel() {
        return level;
    }
    public Inventory getInventory() {
        return inventory;
    }

    private Player player;
    private BoardView dungeonView;
    private BoardView inventoryView;
    private Level level;
    private Inventory inventory;

    public static void main(String[] args) {
        new Dungeon();
    }

    public Dungeon(){
        super();
        // resource setup
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("src/Texture/");
        th.setDefaultTexture("dc-misc/error.png");
        th.addTexture("dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("item/misc/gold_pile.png", "coin");

        th.addTexture("dc-dngn/floor/bog_green0.png", "Floor1");
        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");
        th.addTexture("dc-dngn/wall/crystal_wall02.png", "inventory");
        th.addTexture("dc-dngn/wall/stone_brick1.png", "wall");

        //System.setProperty("sun.java2d.opengl", "true");

        // player setup
        player = new Player("Elf");
        inventory = new Inventory(8,8);
        inventory.getBackground().clear(new Tile("inventory"));

        // level setup
        level = createLevel(1);
        level.getMiddleground().place(player,0,0);
        this.setTableTop(level);


        // window setup
        dungeonView = new BoardView("dungeon", level);
        dungeonView.addLabel("Navigation");
        dungeonView.addButton("Settings", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        dungeonView.addButton("Inventory", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToInventoryView();
            }
        });
        dungeonView.addButton("Stats", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        dungeonView.addLabel("Actions");
        dungeonView.addButton("Attack", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        dungeonView.addButton("Investigate", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        dungeonView.addButton("Wait", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        dungeonView.addLabel("Spells");
        dungeonView.addButton("Spell slot 1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });

        dungeonView.addButton("Spell slot 2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });

        dungeonView.addButton("Spell slot 3", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });

        inventoryView = new BoardView("inventory", inventory);
        inventoryView.addLabel("Navigation");
        inventoryView.addButton("Settings", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        inventoryView.addButton("Return", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToDungeonView();
            }
        });
        inventoryView.addButton("Stats", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });

        Window win = getWindow();
        win.addView(dungeonView);
        win.addView(inventoryView);
        win.setView("dungeon");
        //bv.setZoom(20);
        win.setView("mainBoard");
        win.setAspect(4, 3);
        win.lockResize(true);
        win.updateSize(800, 600+40);
        win.draw();
    }
    private void changeToDungeonView(){
        getWindow().setView("dungeon");
        setTableTop(level);
    }
    private void changeToInventoryView(){
        getWindow().setView("inventory");
        setTableTop(inventory);
    }
    public Level createLevel(int difficulty){
        Level l = new Level(8,8);
        l.getFloor().place(new Item("coin"),2,2);
        l.getFloor().place(new Item("coin"),6,3);
        l.getFloor().place(new Item("coin"),3,6);
        l.getBackground().clear(new Tile("Floor1"));
        return l;
    }
    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.getTableTop() instanceof Level){
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