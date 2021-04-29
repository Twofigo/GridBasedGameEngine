package games.TestGame.Dungeon;

import engine.Entity;
import engine.Renderer;

import java.awt.*;

public class StatsRenderer extends Renderer {
    private Entity e = null;
    @Override
    public void draw(Graphics g) {
        int row =0;
        if(e instanceof Player){
            Player p = (Player)e;
            printLine(g,"Name: Player",row++);
            printLine(g,"Health: "+p.getHealth()+"/"+p.getMaxHealth(),row++);
            printLine(g,"Armor: "+p.getArmorRating(),row++);
            printLine(g,"Damage: "+p.getDamage(),row++);
            row++;
            printLine(g,"Endurance: "+p.getEndurance(),row++);
            printLine(g,"Strength: "+p.getStrength(),row++);
            printLine(g,"Intelligence: "+p.getIntelligence(),row++);
        }
    }
    private void printLine(Graphics g, String s, int row){
        int rowHeight=20;
        int xOffset=20;
        g.drawString(s,xOffset,row*rowHeight);
    }
    public void setFocusEntity(Entity e){
        this.e = e;
    }
}
