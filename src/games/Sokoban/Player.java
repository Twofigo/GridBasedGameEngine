package games.Sokoban;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;

public class Player extends Entity {

    public Player(String name) {
        super(name);
    }

    @Override
    public void update(PuppetMaster dm, TableTop l) {}

    @Override
    public Entity clone() {
        return null;
    }

    @Override
    public Entity clone(Entity thing) {
        return null;
    }
}