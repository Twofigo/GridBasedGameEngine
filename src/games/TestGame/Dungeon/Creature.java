package games.TestGame.Dungeon;

import engine.Entity;

import java.awt.*;

public abstract class Creature extends Entity implements Description{

    protected int maxHealth;
    protected int health;
    protected String texturePath;

    protected String name = "";
    protected String description = "";


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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
