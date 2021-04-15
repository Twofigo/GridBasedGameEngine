package engine;

public abstract class PuppetMaster {
    Board b;

    public boolean interract(Interraction i, Tile obj1, Tile obj2){
        return i.action(b,obj1,obj2);
    }

}
