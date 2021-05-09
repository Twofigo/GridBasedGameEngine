package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import engine.tools.TextureHandler;

import java.awt.*;

public class Consumable extends Item {

    protected int duration;
    protected int durationRemaining;
    protected int health;
    protected int strength;
    protected int intelligence;
    protected int endurance;

    protected Image textureConsumed;

    public Consumable(String textureName,String textureConsumedName, int health, int strength, int intelligence, int endurance, int duration) {
        super(textureName);
        this.textureConsumed = TextureHandler.getInstance().getTexture(textureConsumedName);
        this.health = health;
        this.strength = strength;
        this.intelligence = intelligence;
        this.endurance = endurance;
        this.duration = duration;
        durationRemaining = duration;
    }

    public void renderConsumed(Graphics g, int x, int y){
        g.drawImage(textureConsumed,x,y-100,100,100,null);
    }

    public int getDuration() {
        return duration;
    }

    public int getDurationRemaining() {
        return durationRemaining;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setDurationRemaining(int durationRemaining) {
        this.durationRemaining = durationRemaining;
    }

    public Consumable clone(Entity e){
        Consumable c = (Consumable)super.clone(e);
        c.duration = this.duration;
        c.durationRemaining = this.durationRemaining;
        c.health = this.health;
        c.strength = this.strength;
        c.intelligence = this.intelligence;
        c.endurance = this.endurance;
        c.textureConsumed = this.textureConsumed;
        return c;
    }

    public Consumable clone(){
        return clone(new Consumable("","",0,0,0,0,0));
    }
}
