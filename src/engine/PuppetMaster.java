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

    private int width = 400;
    private int height = 300;

    public PuppetMaster() {
        aspectX = 1;
        aspectY = 1;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        //Container contentPane = this.getContentPane();
        //contentPane.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Little Game");
        this.setSize(width,height);
        this.addComponentListener(this);
        this.setVisible(true);
        this.setResizable(false);
    }

    public boolean interract(Interaction i, Tile obj1, Tile obj2){
        return i.action(this,obj1,obj2);
    }

    public void setAspect(int x,int y){
        aspectX=x;
        aspectY=y;
        width = aspectX*100;
        height = aspectY*100;
        updateSize();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        updateSize();
        Rectangle d = this.getBounds();
        width = d.width;
        height = d.height-40;
    }
    public void updateSize(){
        int w = width;
        int h = height;
        if((w/aspectX)*aspectY < h){
            h = (w/aspectX)*aspectY;
        }
        else{
            w = (h/aspectY)*aspectX;
        }
        //this.setSize(new Dimension(w,h));
        if(currentView!=null) {
            currentView.setPreferredSize(new Dimension(w, h));
            currentView.updateSize(w, h);
        }
    }

    public void setView(View view){
        Container contentPane = getContentPane();
        if(currentView!=null) {
            contentPane.remove(currentView);
        }
        contentPane.add(view);
        currentView = view;
        updateSize();
        this.pack();
    }

    @Override
    public void componentMoved(ComponentEvent e){}
    @Override
    public void componentShown(ComponentEvent e){}
    @Override
    public void componentHidden(ComponentEvent e){}
}
