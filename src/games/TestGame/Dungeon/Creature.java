package games.TestGame.Dungeon;

import engine.Entity;

import java.awt.*;

public abstract class Creature extends Entity {

    protected int maxHealth;
    protected int health;
    private String texturePath;

    public Creature(String name, int maxHealth) {
        super(name);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getHealth() {
        return health;
    }
    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    protected void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void render(Graphics g, int x, int y){
        super.render(g,x,y);
        g.setColor(Color.gray);
        g.fillRect(x+10,y,80,5);
        g.setColor(Color.red);
        g.fillRect(x+10,y,(int)((health/(double)maxHealth)*80),5);
    }

    public abstract int getDamage();
}
