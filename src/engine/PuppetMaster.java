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
        //Container contentPane = this.getContentPane();
        //contentPane.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Little Game");
        this.setSize(400,400);
        this.addComponentListener(this);
        this.setVisible(true);

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
        /*Rectangle r = this.getBounds();
        int w = r.width;
        int h = r.height;
        */
        Dimension d = this.getSize();
        int w = d.width;
        int h = d.height;



        if((w/aspectX)*aspectY < h){
            h = (w/aspectX)*aspectY;
            System.out.println("width");
        }
        else{
            w = (h/aspectY)*aspectX;
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
