package games.TestGame.Dungeon;

import games.TestGame.Dungeon.Inventory.Armor;
import games.TestGame.Dungeon.Inventory.Equipable;
import games.TestGame.Dungeon.Inventory.Weapon;

import java.awt.*;

public class Player extends Creature {

    public static final int WEAPON = 0;
    public static final int HAT    = 1;
    public static final int CHEST  = 2;
    public static final int LEGS   = 3;
    public static final int BOOTS  = 4;
    public static final int CAPE   = 5;
    public static final int SHIELD = 6;

    private int intelligence; // mana amount & spell damage - increased from casting spells
    private int endurance;   // health amount - increased from taking damage
    private int strength;    // weapon damage - increases from hacking monster scum to bits and pieces

    private Equipable[] equipment;

    public Player(String name, int health, int intelligence, int endurance, int strength) {
        super(name, health);
        equipment           = new Equipable[7];
        this.endurance      =endurance;
        this.intelligence   =intelligence;
        this.strength       =strength;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g, int x, int y){
        super.render(g,x,y);
        for (Equipable eq:equipment) {
            if (eq==null) continue;
            eq.renderEquipped(g,x,y);
        }
    }

    @Override
    public int getDamage() {
        int d = getStrength();
        Weapon w = getWeapon();
        if (w!=null) d+=w.getDamage();
        return d;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getStrength() {
        return strength;
    }

    protected Equipable getEquipment(int slot) {
        return equipment[slot];
    }
    protected Armor getArmor(int slot){
        return (Armor)equipment[slot];
    }

    public Weapon getWeapon() {
        Equipable e = getEquipment(WEAPON);
        if (e==null) return null;
        return (Weapon)e;
    }

    public int getArmorRating() {
        int armor = 0;
        Armor a;
        for(int k=1;k<equipment.length;k++) {
            a = getArmor(k);
            if(a==null) continue;
            armor+=a.getArmorRating();
        }
        return armor;
    }

    public void equip(Equipable eq){
        equipment[eq.getEquipmentSlot()] = eq;
    }
    public void unEquip(int slot){
        equipment[slot] = null;
    }


}