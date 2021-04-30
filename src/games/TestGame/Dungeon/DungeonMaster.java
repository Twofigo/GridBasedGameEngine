package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.Inventory.*;
import games.TestGame.Dungeon.World.DungeonGenerator;
import games.TestGame.Dungeon.World.Exit;
import games.TestGame.Dungeon.World.Level;
import games.TestGame.Dungeon.World.Wall;

import java.awt.event.*;

public class DungeonMaster extends PuppetMaster {
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
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Monster monster4;
    private Monster monster5;
    private Monster monster6;

    private DungeonView dungeonView;
    private BoardView inventoryView;
    private StatsRenderer statsRenderer;

    private Level level;
    private Inventory inventory;

    public static void main(String[] args) {
        new DungeonMaster();
    }

    public DungeonMaster(){
        super();
        System.setProperty("sun.java2d.opengl", "true");
        // resource setup
        loadTextures();

        // player setup
        player = new Player("human",50,1,5,3);
        player.setInfo("human","a lone explorer who\nentered a dungeon he\nshouldn't have.");

        level = generateFloor();
        populateFloor(level,1);
        level.spawn(player);
        this.setTableTop(level);

        setupInventory();

        statsRenderer = new StatsRenderer();
        setupDungeonView();
        setupInventoryView();

        DungeonRenderer dr = dungeonView.getDungeonRenderer();
        dr.setZoom(15);
        dr.setOffset(player.getX()+0.5, player.getY()+0.5);

        updateVisibility();
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

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        ((Level)getTableTop()).getForeground().update(this, getLevel());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.getTableTop() instanceof Level){
            update();
            char c = e.getKeyChar();
            int x = player.getX();
            int y = player.getY();
            if(c=='w') y-=1;
            else if(c=='a') x-=1;
            else if(c=='s') y+=1;
            else if(c=='d') x+=1;
            interact(MOVETO,player, x, y);
            DungeonRenderer dr = dungeonView.getDungeonRenderer();
            dr.setOffset(player.getX()+0.5, player.getY()+0.5);
            getWindow().draw();
            updateVisibility();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (this.getTableTop() instanceof Inventory){
            BoardRenderer br = inventoryView.getBoardRenderer();
            if (inventoryPickedUpItem == null){
                Board b = inventory.getForeground();
                int x = br.boardTransX(e.getX());
                int y = br.boardTransY(e.getY());
                x/=100;
                y/=100;
                Tile t = b.get(x,y);
                if (t==null) return;
                if (!(t instanceof Item)) return;
                inventoryPickedUpItem = (Item)t;
                mouseDragged = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        BoardRenderer br = inventoryView.getBoardRenderer();
        if (this.getTableTop() instanceof Inventory) {
            if (inventoryPickedUpItem != null){
                Board b = inventory.getForeground();
                int x = br.boardTransX(e.getX());
                int y = br.boardTransY(e.getY());
                x/=100;
                y/=100;

                if (mouseDragged){
                    this.interact(this.MOVEITEM, inventoryPickedUpItem, x,y);
                    inventoryPickedUpItem = null;
                }
                else{
                    Tile t = b.get(x,y);
                    if (!(t instanceof Consumable)) return;
                    if(this.getPlayer().consume((Consumable)t)){
                        b.pickup((Entity)t);
                        inventoryPickedUpItem = null;
                        getWindow().draw();
                    }
                }
            }
        }
    }


    private Item inventoryPickedUpItem;
    private boolean mouseDragged;
    @Override
    public void mouseDragged(MouseEvent e) {
        BoardRenderer br = inventoryView.getBoardRenderer();
        if (this.getTableTop() instanceof Inventory) {
            if (inventoryPickedUpItem != null) {
                Board b = inventory.getForeground();
                int x = br.boardTransX(e.getX());
                int y = br.boardTransY(e.getY());
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
        if (this.getTableTop() instanceof Inventory) {
            BoardRenderer br = inventoryView.getBoardRenderer();
            int x = br.boardTransX(e.getX());
            int y = br.boardTransY(e.getY());
            x/=100;
            y/=100;
            Tile t;
            t = inventory.getForeground().get(x,y);
            if(t==null){
                t= inventory.getBackground().get(x,y);
            }
            statsRenderer.setFocusEntity(t);
            getWindow().draw();
        }
        else if(this.getTableTop() instanceof Level){
            DungeonRenderer br = dungeonView.getDungeonRenderer();
            int x = br.boardTransX(e.getX());
            int y = br.boardTransY(e.getY());
            x/=100;
            y/=100;
            Tile t;
            t = level.getForeground().get(x,y);
            if(t==null){
                t= level.getFloor().get(x,y);
            }
            if(t==null){
                t= level.getBackground().get(x,y);
            }
            statsRenderer.setFocusEntity(t);
            getWindow().draw();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        DungeonRenderer dr = dungeonView.getDungeonRenderer();
        if (getTableTop() instanceof Level) {
            double amount = (e.getUnitsToScroll()>1?1:-1)*0.5;
            double zoom = dr.getZoom() + amount;
            if (zoom < 1) return;
            if (zoom > 50) return;
            dr.setZoom(zoom);
            dungeonView.draw();
        }
    }

    private void loadTextures(){
        TextureHandler th = TextureHandler.getInstance();
        th.setRootPath("");
        // creatures
        th.addTexture("src/Texture/dc-dngn/gateways/stone_stairs_down.png","stairs");
        th.addTexture("src/Texture/item/potion/ruby.png", "hp_Pot");
        th.addTexture("src/Texture/item/potion/i-heal.png","hp_Effect");
        th.setDefaultTexture("src/Texture/dc-misc/error.png");
        th.addTexture("src/Texture/dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("src/Texture/player/base/human_m.png", "human");
        th.addTexture("src/Texture/item/misc/gold_pile.png", "coin");
        th.addTexture("src/Texture/dc-mon/undead/spectral_warrior.png","zombie");

        // equipment
        th.addTexture("src/Texture/item/armour/headgear/wizard_hat2.png", "hat");
        th.addTexture("src/Texture/item/armour/mottled_dragon_hide.png", "bikini");
        th.addTexture("src/Texture/item/armour/dwarven_ringmail.png", "chainmail");
        th.addTexture("src/Texture/player/legs/leg_armor02.png", "legs");
        th.addTexture("src/Texture/item/weapon/battle_axe1.png", "axe");
        th.addTexture("src/Texture/item/weapon/executioner_axe1.png", "axe2");

        th.addTexture("src/Texture/player/head/wizard_purple.png", "hat_eq");
        th.addTexture("src/Texture/player/body/bikini_red.png", "bikini_eq");
        th.addTexture("src/Texture/player/body/chainmail3.png", "chainmail_eq");
        th.addTexture("src/Texture/player/legs/leg_armor02.png", "legs_eq");
        th.addTexture("src/Texture/player/hand1/axe_double.png", "axe_eq");
        th.addTexture("src/Texture/player/hand1/axe_executioner.png", "axe2_eq");

        // world
        /*
        th.addTexture("src/Texture/dc-dngn/floor/ice0.png", "Floor1");
        th.addTexture("src/Texture/dc-dngn/floor/ice1.png", "Floor2");
        th.addTexture("src/Texture/dc-dngn/floor/ice2.png", "Floor3");
        th.addTexture("src/Texture/dc-dngn/floor/ice3.png", "Floor4");
        */
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt0.png", "Floor1");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt1.png", "Floor2");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt2.png", "Floor3");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt3.png", "Floor4");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt4.png", "Floor5");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt5.png", "Floor6");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt6.png", "Floor7");
        th.addTexture("src/Texture/dc-dngn/floor/grey_dirt7.png", "Floor8");

        th.addTexture("src/Texture/dc-dngn/floor/dirt_full.png", "grass");
        //th.addTexture("src/Texture/dc-dngn/floor/floor_sand_stone5.png", "Floor6");
        //th.addTexture("src/Texture/dc-dngn/floor/floor_sand_stone6.png", "Floor7");
        //th.addTexture("src/Texture/dc-dngn/floor/floor_sand_stone7.png", "Floor8");

        th.addTexture("src/Texture/dc-dngn/floor/cobble_blood1.png", "Floor");

        th.addTexture("src/Texture/dc-dngn/wall/dngn_mirrored_wall.png", "inventory");
        th.addTexture("src/Texture/dc-dngn/wall/stone_brick1.png", "wall");
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
        inventory.getForeground().set(getPlayer(),6,1);
        bg.place(new Tile("inventory"),6,1);
        bg.place(new EquipmentSlot("inventory",Player.HAT),6,2);
        bg.place(new EquipmentSlot("inventory",Player.CHEST),6,3);
        bg.place(new EquipmentSlot("inventory",Player.LEGS),6,4);
        bg.place(new EquipmentSlot("inventory",Player.BOOTS),6,5);
        bg.place(new EquipmentSlot("inventory",Player.CAPE),6,6);
        bg.place(new EquipmentSlot("inventory",Player.SHIELD),6,7);
        bg.place(new EquipmentSlot("inventory",Player.WEAPON),7,1); // weapon
        bg.place(new Trash("Trash"),4,7); // trashcan

    }
    private Level generateFloor(){
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
        Wall voidWall = new Wall("void");
        Wall wall = new Wall("wall");
        Tile t3 = new Tile("Floor");

        Tile[] floors = new Tile[]{
                new Tile("Floor1"),
                new Tile("Floor2"),
                new Tile("Floor3"),
                new Tile("Floor4"),
                new Tile("Floor5"),
                new Tile("Floor6"),
                new Tile("Floor7"),
                new Tile("Floor8")};


        l.getBackground().clear(floors);

        DungeonGenerator dg = new DungeonGenerator();
        dg.setROOM_MIN_DISTANCE(0);
        dg.setROOM_MIN_SIZE(5);
        dg.setRANDOM_PATHS(6);
        int[][] bitmap = dg.generateMap();
        for(int y=0;y<bitmap[0].length;y++){
            for(int x=0;x<bitmap.length;x++){
                int v = bitmap[y][x];
                Tile t = voidWall;
                if(v==0){
                    t = voidWall;
                }
                else if(v==2){
                    t = wall;
                }
                else if(v==3){
                    t = t3;
                }
                else{
                    continue;
                }
                l.getBackground().set(t,x,y);
            }
        }
        return l;
    }
    private void populateFloor(Level l, int difficulty){
        l.spawn(new Exit("stairs"));
        l.spawn(new Item("coin"));
        l.spawn(new Item("coin"));
        l.spawn(new Item("coin"));
        Monster ghast = new Monster("zombie",10,1);
        ghast.setInfo("Ghast","The reminant soul of\na previous explorer.");
        l.spawn(ghast.clone());
        l.spawn(ghast.clone());
        l.spawn(ghast.clone());
        l.spawn(ghast.clone());
        l.spawn(ghast.clone());
        l.spawn(ghast.clone());
        l.spawn(new Consumable("hp_Pot","hp_Effect",3,5,5,5,5));
        // level setup
        l.spawn(new Armor("hat","hat_eq",1, Player.HAT));
        l.spawn(new Armor("bikini","bikini_eq",1, Player.CHEST));
        l.spawn(new Armor("chainmail","chainmail_eq",3,Player.CHEST));
        l.spawn(new Armor("legs","legs_eq",3,Player.LEGS));
        l.spawn(new Weapon("axe2","axe2_eq",3));
    }
    public void goDeeper(){
        Level nextLevel = generateFloor();
        populateFloor(nextLevel,1);
        nextLevel.spawn(this.player);
        this.level = nextLevel;
        this.setTableTop(nextLevel);
        dungeonView.getDungeonRenderer().setTableTop(nextLevel);
        dungeonView.getDungeonRenderer().getDiscoveredMask().clear(false);
        updateVisibility();
        getWindow().draw();
    }

    private void setupDungeonView(){
        // window setup
        dungeonView = new DungeonView("dungeon", level);
        dungeonView.addRenderer(statsRenderer);
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
                Attack a = new Attack();
                //a.action(this,level,player,);
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


        DungeonRenderer dr = dungeonView.getDungeonRenderer();
        dr.getDiscoveredMask().clear(false);
    }
    private void setupInventoryView(){
        inventoryView = new BoardView("inventory", inventory);
        inventoryView.addRenderer(statsRenderer);
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

    private void changeToDungeonView(){
        getWindow().setView("dungeon");
        setTableTop(level);
    }
    private void changeToInventoryView(){
        getWindow().setView("inventory");
        setTableTop(inventory);
    }

    private void createCreatureList(){

    }
    private void createItemList(){

    }

    private void updateVisibility(){

        DungeonRenderer dr = dungeonView.getDungeonRenderer();
        TableTop tb = getTableTop();
        BinaryMask bm = dr.getVisibilityMask();
        bm.clear(false);

        int sightRange = 8;
        //sightRay(bm, player.getX(), player.getY(), player.getX()+sightRange, player.getY()+sightRange);
        //sightRay(bm, player.getX(), player.getY(), player.getX()-sightRange, player.getY()+sightRange);
        //sightRay(bm, player.getX(), player.getY(), player.getX()+sightRange, player.getY()-sightRange);
        //sightRay(bm, player.getX(), player.getY(), player.getX()-sightRange, player.getY()-sightRange);

        int px = player.getX();
        int py = player.getY();

        for(int x=-sightRange;x<=sightRange;x++){
            sightRay(bm, px, py, px+x, py-sightRange);
            sightRay(bm, px, py, px+x, py+sightRange);
        }
        for(int y=-sightRange;y<=sightRange;y++){
            sightRay(bm, px, py, px-sightRange, py+y);
            sightRay(bm, px, py, px+sightRange, py+y);
        }

        // if 3 adjacent tiles are visible, set the tile to visible too
        // "quick" way to fill in wierd holes in sigth algorithm without adding more rays
        for(int x=-sightRange;x<=sightRange;x++){
            for(int y=-sightRange;y<=sightRange;y++){
                if(bm.get(px+x,py+y)) continue;
                if((    (bm.get(px+x-1,py+y)?1:0) +
                        (bm.get(px+x+1,py+y)?1:0) +
                        (bm.get(px+x,py+y-1)?1:0) +
                        (bm.get(px+x,py+y+1)?1:0)) >=3){
                    bm.set(true,px+x,py+y);
                }
            }
        }
        //bm.clear(true); // make everything visable
        dr.getDiscoveredMask().mergeOR(bm);
    }
    private void sightRay(BinaryMask bm, int fromX, int fromY, int toX, int toY){
        // draw a ray from point to closes wall or endpoint
        Board b = ((Level)this.getTableTop()).getBackground();
        int lenX = toX-fromX;
        int lenY = toY-fromY;
        int iter = Math.max(Math.abs(lenY),Math.abs(lenX));
        for(int k=0;k<=iter;k++){
            int x = fromX+(int)Math.round((k*lenX)/((float)iter));
            int y = fromY+(int)Math.round((k*lenY)/((float)iter));
            bm.set(true,x,y);
            if (b.get(x,y) instanceof Wall) break;
        }
    }
    public boolean isVisable(int fromX, int fromY, int toX, int toY){
        Board b = ((Level)this.getTableTop()).getBackground();
        int lenX = toX-fromX;
        int lenY = toY-fromY;
        int iter = Math.max(Math.abs(lenY),Math.abs(lenX));
        for(int k=0;k<=iter;k++){
            int x = fromX+(int)Math.round((k*lenX)/((float)iter));
            int y = fromY+(int)Math.round((k*lenY)/((float)iter));
            if (b.get(x,y) instanceof Wall) return false;
        }
        return true;
    }
}