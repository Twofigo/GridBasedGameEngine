package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Window extends JFrame{

    private View currentView;
    private ArrayList<View> views;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;

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
        cardLayout = new CardLayout(0,0);
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.PINK);
        this.add(cardPanel);
        views = new ArrayList<View>();
        repaint();
    }

    public void setAspect(int x,int y){
        aspectX=x;
        aspectY=y;
        repaint();
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

        cardPanel.setPreferredSize(new Dimension(innerWidth, innerHeight));
        if(currentView!=null){
            currentView.updateSize(innerWidth, innerHeight);
        }

    }
    public void lockResize(boolean lock){
        this.setResizable(!lock);
        this.setSize(innerWidth, innerHeight);
        repaint();
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
                repaint();
            }
        }

    }
}
