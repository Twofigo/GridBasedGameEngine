package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Window extends JFrame{

    private View currentView;
    private ArrayList<View> views;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    private int aspectX;
    private int aspectY;

    public Window(int aspectX, int aspectY) {
        this.setAspect(aspectX,aspectY);
        this.setSize(600,600);
        this.setMinimumSize(new Dimension(200,200));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Little Game");
        this.setVisible(true);

        this.cardLayout = new CardLayout(0,0);
        this.cardPanel = new JPanel(this.cardLayout);
        this.cardPanel.setBackground(Color.PINK);
        this.add(this.cardPanel);
        this.views = new ArrayList<View>();
        repaint();
    }

    public void setAspect(int x,int y){
        aspectX=x;
        aspectY=y;
        repaint();
    }

    public void draw(){
        for (View v:views) {
            v.draw();
        }
        this.repaint();

    }
    public void updateSize(int width, int height){
        int innerWidth = width;
        int innerHeight = height-40;
        if((innerWidth/aspectX)*aspectY<innerHeight){
            innerHeight = (innerWidth/aspectX)*aspectY;
        }
        else{
            innerWidth = (innerHeight/aspectY)*aspectX;
        }
        setSize(new Dimension(innerWidth, innerHeight+40));
        cardPanel.setPreferredSize(new Dimension(innerWidth, innerHeight));
        for (View v:views) {
            v.updateSize(innerWidth,innerHeight);
        }
        draw();
    }
    public void lockResize(boolean lock){
        this.setResizable(!lock);
    }

    public void addView(View view){
        views.add(view);
        //cardLayout.addLayoutComponent(view, view.getName());
        cardPanel.add(view.getName(), view);
    }
    public void setView(String name){
        for (View v:views) {
            if(v.getName().equals(name)){
                currentView = v;
                cardLayout.show(cardPanel, name);
                this.requestFocusInWindow();
                draw();
            }
        }

    }

    // overwriting these is probably a horrible idea, but horrible ideas are more fun when the bugs arrive, so this stays for now.
    @Override
    public void addMouseListener(MouseListener l){
        cardPanel.addMouseListener(l);
    }
    @Override
    public void addMouseMotionListener(MouseMotionListener l){
        cardPanel.addMouseMotionListener(l);
    }
}
