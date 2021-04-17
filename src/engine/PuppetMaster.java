package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class PuppetMaster extends JFrame{
    private final Window window;

    public PuppetMaster() {
        window = new Window(1, 1);
    }

    public Window getWindow() {
        return window;
    }

    public boolean interract(Interaction i, Tile obj1, Tile obj2){
        return i.action(this,obj1,obj2);
    }
}
