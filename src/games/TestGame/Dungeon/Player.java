package games.TestGame.Dungeon;

import games.TestGame.Dungeon.Inventory.Equipable;
import games.TestGame.Dungeon.Inventory.Weapon;

import java.awt.*;

public class Player extends Creature {

    private int intelligence; // mana amount & spell damage - increased from casting spells
    private int endurance;   // health amount - increased from taking damage
    private int strength;    // weapon damage - increases from hacking monster scum to bits and pieces

    private Equipable[] equipment;
    private Weapon weapon;

    public Player(String name, int health) {
        super(name);
        equipment = new Equipable[6];
        super.health = health;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g, int x, int y){
        super.render(g,x,y);
        for (Equipable eq:equipment) {
            if (eq==null) continue;
            eq.renderEquiped(g,x,y);
        }
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

    public Equipable getEquipment(int slot) {
        return equipment[slot];
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getArmorRating() {
        int armor = 0;
        for(int k=0;k<equipment.length;k++) {
            armor+=getEquipment(k).getArmorRating();
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