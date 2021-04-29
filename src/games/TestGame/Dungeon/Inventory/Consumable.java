package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;
import engine.TextureHandler;
import games.TestGame.Dungeon.DungeonMaster;
import games.TestGame.Dungeon.MoveInto;

import java.awt.*;

public class Consumable extends Item {

    private final int duration;
    private int durationRemaining;
    private final int health;
    private final int strength;
    private final int intelligence;
    private final int endurance;

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


}
