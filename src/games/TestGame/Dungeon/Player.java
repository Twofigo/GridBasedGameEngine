package games.TestGame.Dungeon;

import engine.Entity;

import java.awt.*;

public class Player extends Creature {

    private int intelligence; // mana amount & spell damage - increased from casting spells
    private int endurance;   // health amount - increased from taking damage
    private int strength;    // weapon damage - increases from hacking monster scum to bits and pieces

    private Equipable[] equipment;
    private Weapon weapon;

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

    public int getArmor() {
        int armor = 0;
        for(int k=0;k<equipment.length;k++) {
            armor+=getEquipment(k).getArmorRating();
        }
        return armor;
    }

    public Player(String name, int health) {
        super(name);
        super.health = health;
    }

    @Override
    public void update() {

    }
}