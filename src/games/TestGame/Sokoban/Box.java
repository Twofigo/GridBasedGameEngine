package games.TestGame.Sokoban;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;
import engine.TextureHandler;
import java.awt.*;

public class Box extends Entity implements MoveInto {
    private boolean active;

    private Image neutralTexure;
    private Image activeTexture;
    public Box(String textureName, String activeTextureName) {
        super(textureName);
        this.neutralTexure = TextureHandler.getInstance().getTexture(textureName);
        this.activeTexture = TextureHandler.getInstance().getTexture(activeTextureName);
        this.active = false;
    }

    public boolean moveInto(Sokoban p, Entity e){
        if(e instanceof Box){return false;}
        Sokoban game = ((Sokoban)p);
        int x = this.getX()*2 - e.getX();
        int y = this.getY()*2 - e.getY();
        if(!game.interact(game.MOVETO,this,x,y)) return false;
        game.interact(game.ACTUATE,this,x,y);
        return true;
    }
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            this.setTexture(activeTexture);
        }
        else {
            this.setTexture(neutralTexure);
        }
    }
    public boolean isActive() {
        return active;
    }

    @Override
    public void update(PuppetMaster dm, TableTop l) {

    }

    @Override
    public Entity clone() {
        return null;
    }
}