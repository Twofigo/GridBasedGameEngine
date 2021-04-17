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

    public abstract boolean interact(Interaction i, Tile obj1, Tile obj2);
    public abstract boolean interact(Interaction i, int x, int y);

    protected abstract void onClick(int x, int y, int button);
    protected abstract void onMouseDown(int x, int y, int button);
    protected abstract void onMouseMove(int x, int y);
    protected abstract void onMouseUp(int x, int y, int button);
    protected abstract void onKeyStroke(int keyCode);
}
