package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame{

    private View currentView;

    private int aspectX;
    private int aspectY;

    private int width;
    private int height;

    private int innerWidth;
    private int innerHeight;

    public Window(int aspectX, int aspectY) {
        this.setAspect(aspectX,aspectY);
        this.setSize(600,600);
        this.setMinimumSize(new Dimension(200,200));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Little Game");
        this.setVisible(true);
        repaint();
    }

    public void setAspect(int x,int y){
        aspectX=x;
        aspectY=y;
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = this.getSize();
        width = d.width;
        height = d.height;
        updateSize();
        super.paint(g);
    }
    public void updateSize(){
        innerWidth = width;
        innerHeight = height-40;
        if((innerWidth/aspectX)*aspectY<innerHeight){
            innerHeight = (innerWidth/aspectX)*aspectY;
        }
        else{
            innerWidth = (innerHeight/aspectY)*aspectX;
        }
        if(currentView!=null) {
            currentView.setPreferredSize(new Dimension(innerWidth, innerHeight));
            currentView.updateSize(innerWidth, innerHeight);
        }
    }
    public void lockResize(boolean lock){
        this.setResizable(!lock);
    }

    public void setView(View view){
        Container contentPane = getContentPane();
        if(currentView!=null) {
            contentPane.remove(currentView);
            this.setSize(600,600);

        }
        contentPane.add(view);
        currentView = view;
        updateSize();
        this.pack();
    }
}
