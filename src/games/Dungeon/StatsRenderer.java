package games.Dungeon;

import engine.Graphics.Renderer;
import engine.Tile;
import games.Dungeon.Inventory.Armor;
import games.Dungeon.Inventory.Consumable;
import games.Dungeon.Inventory.Weapon;

import java.awt.*;

public class StatsRenderer extends Renderer {
    private Tile t = null;
    private Font f;
    public StatsRenderer(){
        super(1000,1000);
        String str="";
        if (str.equals("")) str = findFont("moon");
        if (str.equals("")) str = findFont("times");
        if (str.equals("")) str = findFont("roman");
        if (str.equals("")) str = findFont("serif");
        if (str.equals("")) str = findFont("arial");
        if (str.equals("")) System.out.println("There are literally no font's installed, it's impressive you can read this");
        f = new Font(str,Font.PLAIN, 32);
    }

    /**
     * seaches for a keyword among avalable fonts.
     * because cross compatability between linux and windows is a pain, and windows does not know what TimesRoman is
     *
     * @param query to seach for
     * @return Font family name
     *
     * returns empty string if no font was found
     */
    public String findFont(String query){
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String s[] = g.getAvailableFontFamilyNames();
        for (String str:s) {
            if(str.toLowerCase().contains(query.toLowerCase())){
                return str; // found font
            }
        }
        return "";
    }

    @Override
    public void render(Graphics g) {
        int row =0;
        if(t instanceof Description){
            Description d = (Description)t;
            row+=printLine(g,"Name: "+d.getName(),row);
            row+=printLine(g,d.getDescription(),row);
            row++;
        }
        if(t instanceof Player){
            Player p = (Player)t;
            row+=printLine(g,"Health: "+p.getHealth()+"/"+p.getMaxHealth(),row);
            row+=printLine(g,"Armor: "+p.getArmorRating(),row);
            row+=printLine(g,"Damage: "+p.getDamage(),row);
            row++;
            row+=printLine(g,"Endurance: "+p.getEndurance(),row);
            row+=printLine(g,"Strength: "+p.getStrength(),row);
            row+=printLine(g,"Intelligence: "+p.getIntelligence(),row);
        }
        else if(t instanceof Monster){
            Monster m = (Monster)t;
            printLine(g,"Health: "+m.getHealth()+"/"+m.getMaxHealth(),row++);
            printLine(g,"Damage: "+m.getDamage(),row++);
        }
        else if(t instanceof Armor){
            Armor a = (Armor)t;
            row+=printLine(g,"type: Armor",row);
            row+=printLine(g,"Armor: "+a.getArmorRating(),row);
            row+=printLine(g,"Eq slot: "+a.getEquipmentSlot(),row);
        }
        else if(t instanceof Weapon){
            Weapon w = (Weapon)t;
            row+=printLine(g,"type: Weapon",row);
            row+=printLine(g,"Damage: "+w.getDamage(),row);
        }
        else if(t instanceof Consumable){
            Consumable c = (Consumable)t;
            row+=printLine(g,"type: Consumable",row);
            row+=printLine(g,"Duration: "+c.getDuration(),row);
            row++;
            row+=printLine(g,"Health: "+c.getHealth(),row);
            row+=printLine(g,"Intelligence: "+c.getIntelligence(),row);
            row+=printLine(g,"Strength: "+c.getStrength(),row);
            row+=printLine(g,"Endurance: "+c.getEndurance(),row);
        }
    }

    private int printLine(Graphics g, String s, int row){
        g.setFont(f);
        int rowHeight=40;
        int xOffset=20;
        int yOffset=60;
        int charWidth = 20;
        String st;
        int rows =0;
        while(true){
            if(s.equals("")) break;
            int i = s.indexOf("\n");
            if(i!=-1){
                st = s.substring(0,i);
                s = s.substring(i+1);
            }
            else{
                st = s;
                s = "";
            }
            g.setColor(new Color(0,0,0,150));
            g.fillRect(xOffset-10,yOffset+(row+rows)*rowHeight-30, st.length()*charWidth+10, rowHeight);
            g.setColor(Color.WHITE);
            g.drawString(st,xOffset,yOffset+(row+rows)*rowHeight);
            rows++;
        }
        return rows;
    }
    public void setFocusEntity(Tile t){
        this.t = t;
    }
}
