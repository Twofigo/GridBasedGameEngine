package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.Inventory.*;
import games.TestGame.Dungeon.World.Level;
import games.TestGame.Dungeon.World.Wall;

import java.awt.event.*;

public class Dungeon extends PuppetMaster {
    public final MoveTo MOVETO = new MoveTo();
    public final Pickup PICKUP = new Pickup();
    public final MoveItem MOVEITEM = new MoveItem();

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

        loadTextures();
        //System.setProperty("sun.java2d.opengl", "true");

        // player setup
        player = new Player("human",10);

        // level setup
        level = createLevel(1);
        level.getMiddleground().place(player,0,0);
        this.setTableTop(level);

        setupInventory();

        setupDungeonView();
        setupInventoryView();

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
    private void setupInventory(){
        inventory = new Inventory(8,8);
        inventory.getBackground().clear(new Tile("inventory"));
        Board bg = inventory.getBackground();
        EmptySpace empty = new EmptySpace("wall");
        for(int y=0;y<inventory.height();y++) {
            for (int x = 5; x<inventory.width();x++){
                bg.set(empty,x,y);
            }
        }
        bg.set(getPlayer(),6,1);
        bg.place(new EquipmentSlot("inventory",0),6,2);
        bg.place(new EquipmentSlot("inventory",1),6,3);
        bg.place(new EquipmentSlot("inventory",2),6,4);
        bg.place(new EquipmentSlot("inventory",3),6,5);
        bg.place(new EquipmentSlot("inventory",4),6,6);

    }
    private void loadTextures(){
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("src/Texture/");
        th.setDefaultTexture("dc-misc/error.png");
        th.addTexture("dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("player/base/human_m.png", "human");
        th.addTexture("player/head/wizard_purple.png", "hat");
        th.addTexture("player/body/bikini_red.png", "bikini");
        th.addTexture("item/misc/gold_pile.png", "coin");

        th.addTexture("dc-dngn/floor/ice0.png", "Floor1");
        th.addTexture("dc-dngn/floor/ice1.png", "Floor2");
        th.addTexture("dc-dngn/floor/ice2.png", "Floor3");
        th.addTexture("dc-dngn/floor/ice3.png", "Floor4");
        th.addTexture("dc-dngn/floor/dirt_full.png", "grass");
        th.addTexture("dc-dngn/floor/floor_sand_stone5.png", "Floor6");
        th.addTexture("dc-dngn/floor/floor_sand_stone6.png", "Floor7");
        th.addTexture("dc-dngn/floor/floor_sand_stone7.png", "Floor8");

        th.addTexture("dc-dngn/floor/cobble_blood1.png", "Floor");

        th.addTexture("dc-dngn/wall/dngn_mirrored_wall.png", "inventory");
        th.addTexture("dc-dngn/wall/stone_brick1.png", "wall");
    }
    private void setupDungeonView(){
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
    }
    private void setupInventoryView(){
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
    }

    public Player getPlayer() {
        return player;
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
        /*
        Level l = new Level(8,8);
        l.getFloor().place(new Item("coin"),2,2);
        l.getFloor().place(new Item("coin"),6,3);
        l.getFloor().place(new Item("coin"),3,6);
        l.getFloor().place(new Equipable("hat",1,0), 3,3);
        l.getFloor().place(new Equipable("bikini",1,1), 4,4);
        Tile[] tiles = {new Tile("Floor3"),new Tile("Floor1"),new Tile("Floor4"),new Tile("Floor5"),new Tile("Floor6"),new Tile("Floor7"),new Tile("Floor8"),new Tile("Floor9"),};
        l.getBackground().clear(tiles);
        */
        Level l = new Level(60,60);
        Wall voidWall = new Wall("grass");
        Wall wall = new Wall("wall");
        Tile t3 = new Tile("Floor");

        Tile[] floors = new Tile[]{
            new Tile("Floor1"),
            new Tile("Floor2"),
            new Tile("Floor3"),
            new Tile("Floor4")};
                    /*
            new Tile("Floor5"),
            new Tile("Floor6"),
            new Tile("Floor7"),
            new Tile("Floor8")}
            */

        l.getBackground().clear(floors);

        DungeonGenerator dg = new DungeonGenerator();
        int[][] bitmap = dg.getBitmap();
        for(int y=0;y<bitmap[0].length;y++){
            for(int x=0;x<bitmap.length;x++){
                int v = bitmap[y][x];
                Tile t = voidWall;
                if(v==0){
                    t = voidWall;
                    l.getBackground().set(null,x,y);
                }
                if(v==2){
                    t = wall;
                }
                if(v==3){
                    t=null;
                    l.getBackground().set(t3,x,y);
                }
                if(v==4){
                    t=null;
                }
                l.getForeground().set(t,x,y);
            }
        }
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
        if (this.getTableTop() instanceof Inventory){
            if (inventoryPickedUpItem == null){
                Board b = inventory.getForeground();
                int x = inventoryView.boardTransX(e.getX());
                int y = inventoryView.boardTransY(e.getY());
                x/=100;
                y/=100;
                Tile t = b.get(x,y);
                if (t==null) return;
                inventoryPickedUpItem = (Item)t;
                mouseDragged = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.getTableTop() instanceof Inventory) {
            if (inventoryPickedUpItem != null){
                Board b = inventory.getForeground();
                int x = inventoryView.boardTransX(e.getX());
                int y = inventoryView.boardTransY(e.getY());
                x/=100;
                y/=100;

                if (mouseDragged){
                    this.interact(this.MOVEITEM, inventoryPickedUpItem, x,y);
                    inventoryPickedUpItem = null;
                }
                else{
                    // consume
                }

            }
        }
    }


    private Item inventoryPickedUpItem;
    private boolean mouseDragged;
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.getTableTop() instanceof Inventory) {
            if (inventoryPickedUpItem != null) {
                Board b = inventory.getForeground();
                int x = inventoryView.boardTransX(e.getX());
                int y = inventoryView.boardTransY(e.getY());
                x /= 100;
                y /= 100;
                if (x!=inventoryPickedUpItem.getX() || y !=inventoryPickedUpItem.getY()){
                    mouseDragged = true;
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}