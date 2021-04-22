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
        th.addTexture("player/base/human_m.png", "human");
        th.addTexture("player/head/wizard_purple.png", "hat");
        th.addTexture("player/body/bikini_red.png", "bikini");
        th.addTexture("item/misc/gold_pile.png", "coin");

        th.addTexture("dc-dngn/floor/floor_sand_stone0.png", "Floor1");
        th.addTexture("dc-dngn/floor/floor_sand_stone1.png", "Floor3");
        th.addTexture("dc-dngn/floor/floor_sand_stone2.png", "Floor4");
        th.addTexture("dc-dngn/floor/floor_sand_stone3.png", "Floor5");
        th.addTexture("dc-dngn/floor/floor_sand_stone4.png", "Floor6");
        th.addTexture("dc-dngn/floor/floor_sand_stone5.png", "Floor7");
        th.addTexture("dc-dngn/floor/floor_sand_stone6.png", "Floor8");
        th.addTexture("dc-dngn/floor/floor_sand_stone7.png", "Floor9");

        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor2");

        th.addTexture("dc-dngn/wall/dngn_mirrored_wall.png", "inventory");
        th.addTexture("dc-dngn/wall/stone_brick1.png", "wall");

        //System.setProperty("sun.java2d.opengl", "true");

        // player setup
        Equipable hat = new Equipable("hat",1,0);
        Equipable bikini = new Equipable("bikini",1,1);
        player = new Player("human",10);
        player.equip(hat);
        player.equip(bikini);
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

        //dungeonView.setZoom(3);
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
        Tile[] tiles = {new Tile("Floor3"),new Tile("Floor1"),new Tile("Floor4"),new Tile("Floor5"),new Tile("Floor6"),new Tile("Floor7"),new Tile("Floor8"),new Tile("Floor9"),};
        l.getBackground().clear(tiles);
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