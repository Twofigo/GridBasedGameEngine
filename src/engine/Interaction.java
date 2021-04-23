package engine;

import java.util.ArrayList;

public interface Interaction {
    public abstract boolean action(PuppetMaster p, TableTop tb, Entity obj1, int x, int y);
}
