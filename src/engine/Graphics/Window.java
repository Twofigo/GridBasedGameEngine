package engine.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


/**
 * The window class describes the window.
 * it contains multiple views than can be changed by their name
 */
public class Window extends JFrame implements ActionListener {

    private View currentView;
    private ArrayList<View> views;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    private int aspectX;
    private int aspectY;

    private javax.swing.Timer timer;

    /**
     * sets up the window from a given aspect ratio
     * @param aspectX
     * @param aspectY
     */
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

    /**
     * sets aspect ratio of window
     * @param x
     * @param y
     */
    public void setAspect(int x,int y){
        aspectX=x;
        aspectY=y;
        repaint();
    }

    /**
     * Redraws the View
     */
    public void draw(){
        for (View v:views) {
            v.draw();
        }
        this.repaint();
    }

    /**
     * starts the real-time renderer loop with a given time interval
     *
     * @param timeMS interval in milliseconds
     */
    public void start(int timeMS){
        this.timer = new javax.swing.Timer(timeMS,this);
        this.timer.start();
    }
    /**
     * stops the realtime renderer loop, if it's running
     *
     * @param timeMS interval in milliseconds
     */
    public void stop(int timeMS){
        if(this.timer==null) return;
        this.timer.stop();
        this.timer = null;
    }

    /**
     * updates the size of the whole window and view
     * @param width
     * @param height
     */
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

    /**
     * disable the dynamic resizing
     * @param lock
     */
    public void lockResize(boolean lock){
        this.setResizable(!lock);
    }

    /**
     * Adds a new view to the window
     * @param view
     */
    public void addView(View view){
        views.add(view);
        //cardLayout.addLayoutComponent(view, view.getName());
        cardPanel.add(view.getName(), view);
    }

    /**
     * Changes focus to view by given name, if such a view had been added.
     * @param name name of view
     */
    public void setView(String name){
        for (View v:views) {
            if(v.getName().equals(name)){
                currentView = v;
                cardLayout.show(cardPanel, name);
                this.requestFocusInWindow();
                draw();
                return;
            }
        }

    }

    /**
     * Adds a MouseListener to the window
     * See ActionListener Interface from java awt.
     * @param l
     */
    // overwriting these is probably a horrible idea, but horrible ideas are more fun when the bugs arrive, so this stays for now.
    @Override
    public void addMouseListener(MouseListener l){
        cardPanel.addMouseListener(l);
    }

    /**
     * Adds a MouseMotionListener to the window
     * See ActionListener Interface from java awt.
     * @param l
     */
    @Override
    public void addMouseMotionListener(MouseMotionListener l){
        cardPanel.addMouseMotionListener(l);
    }

    /**
     * Called by the realtime render loop.
     * Please ignore
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        long timestamp = System.currentTimeMillis()+1;
        for (View v:views) {
            v.draw(timestamp);
        }
    }
}
