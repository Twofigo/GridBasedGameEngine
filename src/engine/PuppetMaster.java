package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class PuppetMaster extends JFrame implements ComponentListener {

    private Board boa;
    private View currentView;

    private int aspectX;
    private int aspectY;

    private int width;
    private int height;

    public PuppetMaster() {
        aspectX = 1;
        aspectY = 1;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));

        //contentPane.setLayout(new GridLayout(1,1));
        this.setSize(400,400);
        this.setVisible(true);
        this.addComponentListener(this);
    }

    public boolean interract(Interaction i, Tile obj1, Tile obj2){
        return i.action(this,obj1,obj2);
    }

    public void setAspect(int x,int y){
        aspectX=x;
        aspectY=y;
        updateSize();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        updateSize();
    }
    public void updateSize(){
        Rectangle r = this.getBounds();
        int w;
        int h;


        if((r.width/aspectX)*aspectY < r.height){
            w = r.width;
            h = (r.width/aspectX)*aspectY;
            System.out.println("width");
        }
        else{
            w = (r.height/aspectY)*aspectX;
            h = r.height;
            System.out.println("height");
        }
        //this.setSize(new Dimension(w,h));
        if(currentView!=null)
            currentView.updateSize(w, h);
    }

    public void setView(View view){
        Container contentPane = getContentPane();
        if(currentView!=null) {
            contentPane.remove(currentView);
        }
        contentPane.add(view);
        currentView = view;

        this.pack();
    }

    @Override
    public void componentMoved(ComponentEvent e){}
    @Override
    public void componentShown(ComponentEvent e){}
    @Override
    public void componentHidden(ComponentEvent e){}
}
