package engine;

public abstract class PuppetMaster {
    Board boa;
    public boolean interract(Interaction i, Tile obj1, Tile obj2){
        return i.action(this,obj1,obj2);
    }
    
}
