package games.TestGame.Dungeon.Inventory;

import engine.Entity;

public class Weapon extends Equipable {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public Weapon(String textureName, String textureEquipedName, int damage) {
        super(textureName, textureEquipedName, 0);
        this.damage = damage;
    }

    public Weapon clone(Entity e){
        Weapon w = (Weapon)super.clone(e);
        w.damage = this.damage;
        return w;
    }

    public Weapon clone(){
        return clone(new Weapon("","",0));
    }

}