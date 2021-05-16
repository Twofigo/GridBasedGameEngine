package games.Dungeon.Inventory;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;
import games.Dungeon.*;

public class Item extends Entity implements MoveInto, Description {
    public Item(String name) {
        super(name);
    }

    private String name = "";
    private String description = "";
    @Override
    public void update(PuppetMaster dm, TableTop l) {

    }
    @Override
    public boolean moveInto(DungeonMaster p, Entity e) {
        if (!(e instanceof Player)) return true;
        p.interact(p.PICKUP, this, this.getX(), this.getY());
        return true;
    }
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
    public Item clone(){
        Item i = new Item("");
        i.x = this.x;
        i.y = this.y;
        i.texture = this.texture;

        i.name = this.name;
        i.description = this.description;
        return i;
    }

    @Override
    public Entity clone(Entity thing) {
        if(!(thing instanceof Item)) {
            return null;
        }
        Item i = (Item)thing;
        i.x = this.x;
        i.y = this.y;
        i.texture = this.texture;
        i.name = this.name;
        i.description = this.description;

        return i;
    }
}
