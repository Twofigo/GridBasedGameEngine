package engine;

import java.util.ArrayList;

/**
 * User configurable interactions between entities
 */
public interface Interaction {
    /**
     * action is configurable from game to game since interactions might differ between entities.
     * @param p
     * @param tb
     * @param obj1
     * @param x
     * @param y
     * @return boolean
     */
    public abstract boolean action(PuppetMaster p, TableTop tb, Entity obj1, int x, int y);
}
