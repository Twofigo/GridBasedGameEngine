package engine;

/**
 *  An abstract class for tiles that move
 */
abstract public class Entity extends Tile{
    protected int x;
    protected int y;

    /**
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * @param name
     */
    public Entity(String name) {
        super(name);
    }

    /**
     * @param x
     * @param y
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * This is called each time tick
     * @param dm
     * @param l
     */
    public abstract void update(PuppetMaster dm, TableTop l);

    /**
     * Clones entitiy
     * @return Entity
     */
    public abstract Entity clone();

    /**
     * Clones entitiy
     * @param thing
     * @return  Entity
     */
    public abstract Entity clone(Entity thing);
}
