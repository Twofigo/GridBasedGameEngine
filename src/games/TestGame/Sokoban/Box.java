package games.TestGame.Sokoban;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;
import engine.tools.TextureHandler;
import java.awt.*;

public class Box extends Entity implements MoveInto {
    private boolean active;

    private Image neutralTexture;
    private Image activeTexture;
    public Box(String textureName, String activeTextureName) {
        super(textureName);
        this.neutralTexture = TextureHandler.getInstance().getTexture(textureName);
        this.activeTexture = TextureHandler.getInstance().getTexture(activeTextureName);

        assert(activeTexture!=null);
        assert(neutralTexture!=null);

        this.active = false;
    }

    public boolean moveInto(Sokoban p, Entity e){
        if(e instanceof Box){return false;}
        assert(e!=null);
        assert(p!=null);

        Sokoban game = p;
        int x = this.getX()*2 - e.getX();
        int y = this.getY()*2 - e.getY();

        assert(game.MOVETO!=null);
        assert(game.ACTUATE!=null);

        if(!game.interact(game.MOVETO,this,x,y)) return false;
        game.interact(game.ACTUATE,this,x,y);
        return true;
    }
    public void setActive(boolean active) {
        this.active = active;
        assert(activeTexture!=null);
        assert(neutralTexture!=null);

        if (active) {
            this.setTexture(activeTexture);
        }
        else {
            this.setTexture(neutralTexture);
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

    @Override
    public Entity clone(Entity thing) {
        return null;
    }
}