package engine;

import java.util.ArrayList;

public abstract class Interaction {
    public abstract boolean action(PuppetMaster p, TableTop tb, Entity obj1, int x, int y);
}
