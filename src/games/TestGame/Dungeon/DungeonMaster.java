package games.TestGame.Dungeon;

import engine.*;
import engine.Graphics.*;
import engine.tools.TextureHandler;
import games.TestGame.Dungeon.Inventory.*;
import games.TestGame.Dungeon.World.DungeonGenerator;
import games.TestGame.Dungeon.World.Exit;
import games.TestGame.Dungeon.World.Level;
import games.TestGame.Dungeon.World.Wall;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

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
    private DungeonView dungeonView;
    private BoardView inventoryView;
    private StatsRenderer statsRenderer;

    private Level level;
    private int difficulty = 1;
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
        player = new Player("human",100,1,2,3);
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

        win.start(25);
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
                int x = br.transX(e.getX());
                int y = br.transY(e.getY());
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
                int x = br.transX(e.getX());
                int y = br.transY(e.getY());
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
                int x = br.transX(e.getX());
                int y = br.transY(e.getY());
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
            int x = br.transX(e.getX());
            int y = br.transY(e.getY());
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
            int x = br.transX(e.getX());
            int y = br.transY(e.getY());
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
        th.setDefaultTexture("src/Texture/dc-misc/error.png");
        th.addTexture("src/Texture/dc-mon/deep_elf_mage.png", "Elf");
        th.addTexture("src/Texture/player/base/human_m.png", "human");
        th.addTexture("src/Texture/item/misc/gold_pile.png", "coin");
        th.addTexture("src/Texture/dc-mon/undead/spectral_warrior.png","zombie");

        //Consumables
        th.addTexture("src/Texture/item/potion/ruby.png", "hp_Pot");
        th.addTexture("src/Texture/item/potion/i-heal.png","hp_Effect");
        th.addTexture("src/Texture/item/food/orange.png","orange");
        th.addTexture("src/Texture/item/food/chunk.png","meat");
        th.addTexture("src/Texture/item/potion/orange.png","str_Pot");
        th.addTexture("src/Texture/item/potion/i-gain-strength.png","str_Effect");

        // equipment
        //dropped
        th.addTexture("src/Texture/item/armour/headgear/elven_leather_helm.png","hat2");
        th.addTexture("src/Texture/item/armour/elven_leather_armor.png","jacket");
        th.addTexture("src/Texture/item/armour/headgear/wizard_hat2.png", "hat");
        th.addTexture("src/Texture/item/armour/mottled_dragon_hide.png", "bikini");
        th.addTexture("src/Texture/item/armour/dwarven_ringmail.png", "chainmail");
        th.addTexture("src/Texture/player/legs/leg_armor02.png", "legs");
        th.addTexture("src/Texture/item/weapon/battle_axe1.png", "axe");
        th.addTexture("src/Texture/item/weapon/executioner_axe1.png", "axe2");
        th.addTexture("src/Texture/item/armour/shields/buckler1.png","shield");
        th.addTexture("src/Texture/item/armour/shields/shield1_elven.png","shield2");
        th.addTexture("src/Texture/item/weapon/quarterstaff.png","staff");
        th.addTexture("src/Texture/item/weapon/spear2.png","spear");
        th.addTexture("src/Texture/item/weapon/mace1.png","mace");
        th.addTexture("src/Texture/item/weapon/morningstar2.png","morningstar");
        th.addTexture("src/Texture/item/weapon/long_sword1.png","sword");
        th.addTexture("src/Texture/player/hand1/artefact/sword_of_power.png","sword2");

        //equipped
        th.addTexture("src/Texture/player/hand1/blessed_blade.png","sword2_eq");
        th.addTexture("src/Texture/player/hand1/sword3.png","sword_eq");
        th.addTexture("src/Texture/player/hand1/morningstar2.png","morningstar_eq");
        th.addTexture("src/Texture/player/hand1/mace.png","mace_eq");
        th.addTexture("src/Texture/player/hand1/spear1.png","spear_eq");
        th.addTexture("src/Texture/player/hand1/quarterstaff1.png","staff_eq");
        th.addTexture("src/Texture/player/hand2/shield_knight_blue.png","shield2_eq");
        th.addTexture("src/Texture/player/hand2/boromir.png","shield_eq");
        th.addTexture("src/Texture/player/body/jacket2.png","jacket_eq");
        th.addTexture("src/Texture/player/head/feather_green.png","hat2_eq");
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
        ArrayList<Item> items = createItemList();
        Monster[] monster = createCreatureList();
        for (Monster ghast:monster) {
            if(ghast == null){continue;}
            l.spawn(ghast.clone());
        }
        for (Item thing:items) {
            l.spawn(thing.clone());
        }
        // level setup
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
        dungeonView.addButton("Inventory", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToInventoryView();
            }
        });


        DungeonRenderer dr = dungeonView.getDungeonRenderer();
        dr.getDiscoveredMask().clear(false);
    }
    private void setupInventoryView(){
        inventoryView = new BoardView("inventory", inventory);
        inventoryView.addRenderer(statsRenderer);
        inventoryView.addLabel("Navigation");
        inventoryView.addButton("Return", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToDungeonView();
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

    private Monster[] createCreatureList(){
        Monster ghast = new Monster("zombie",10+difficulty*2, (int)(8+(difficulty)*0.5));
        ghast.setInfo("Ghast","The reminant soul of\na previous explorer.");
        Monster[] ghasts = new Monster[18];
        for(int i = 0; i < 15*Math.tanh(difficulty*0.15)+3; i++){
            ghasts[i] = ghast.clone();
        }
        return ghasts;
    }
    private ArrayList<Item> createItemList(){
        ArrayList<Item> Items = new ArrayList<Item>();
        ArrayList<Item> Consumables = new ArrayList<>();
        ArrayList<Item> Picked = new ArrayList<Item>();
        Consumable hp_potion = new Consumable("hp_Pot","hp_Effect",5,0,0,0,5);
        Consumable orange = new Consumable("orange","hp_Effect",5,0,0,0,1);
        Consumable meat = new Consumable("meat","hp_Effect",1,1,0,0,10);
        Consumable str_potion = new Consumable("str_Pot","str_Effect",0,2,0,0,20);
        Armor wizardhat = new Armor("hat","hat_eq",1, Player.HAT);
        Armor bikini = new Armor("bikini","bikini_eq",1, Player.CHEST);
        Armor chainmail = new Armor("chainmail","chainmail_eq",3,Player.CHEST);
        Armor chainlegs = new Armor("legs","legs_eq",3,Player.LEGS);
        Armor leatherJacket = new Armor("jacket","jacket_eq",1,Player.CHEST);
        Armor hat2 = new Armor("hat2","hat2_eq",1,Player.HAT);
        Armor shield1 = new Armor("shield","shield_eq",2,Player.SHIELD);
        Armor shield2 = new Armor("shield2","shield2_eq",4,Player.SHIELD);
        Weapon axe2 = new Weapon("axe2","axe2_eq",2);
        Weapon staff = new Weapon("staff","staff_eq",1);
        Weapon spear = new Weapon("spear","spear_eq",2);
        Weapon mace = new Weapon("mace","mace_eq",2);
        Weapon morningstar = new Weapon("morningstar","morningstar_eq",2);
        Weapon sword = new Weapon("sword","sword_eq",2);
        Weapon sword2 = new Weapon("sword2","sword2_eq",10);
        Items.add(orange);
        Items.add(meat);
        Items.add(str_potion);
        Items.add(leatherJacket);
        Items.add(hat2);
        Items.add(shield1);
        Items.add(shield2);
        Items.add(staff);
        Items.add(spear);
        Items.add(mace);
        Items.add(morningstar);
        Items.add(sword);
        Items.add(sword2);
        Items.add(hp_potion);
        Items.add(wizardhat);
        Items.add(bikini);
        Items.add(chainmail);
        Items.add(chainlegs);
        Items.add(axe2);

        Consumables.add(orange);
        Consumables.add(meat);
        Consumables.add(str_potion);
        Consumables.add(hp_potion);

        for (int i = 0; i < 1.2*Math.tanh(difficulty*0.3)+1; i++) {
            Picked.add(Items.get(new Random().nextInt(Items.size())));
        }
        for (int i = 0; i < 2.2*Math.tanh(difficulty*0.3); i++) {
            Picked.add(Consumables.get(new Random().nextInt(Consumables.size())));
        }

        return Picked;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}