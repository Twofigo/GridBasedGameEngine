package games.TestGame;
import engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShittyGame {
    public static void main(String[] args) {
        PuppetMaster p = new PuppetMaster(){};

        MenuView mv = new MenuView(new String[]{"hej","hopp","snopp"}, new ActionListener[]{null,null,null});
        p.setView(mv);
    }
}
