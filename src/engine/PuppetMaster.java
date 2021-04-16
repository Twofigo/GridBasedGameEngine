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
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        //Container contentPane = this.getContentPane();
        //contentPane.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Little Game");
        this.setAspect(1,1);
        this.setSize(width,height);
        this.addComponentListener(this);
        this.setVisible(true);

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
    public void paint(Graphics g) {
        super.paint(g);

        Dimension d = this.getSize();
        width = d.width;
        height = d.height-40;
        updateSize();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //this.repaint();
        //Rectangle d = this.getBounds();
/*
        Dimension d = this.getSize();
        width = d.width;
        height = d.height-40;
        updateSize();
*/
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
