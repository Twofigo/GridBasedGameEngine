package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

abstract public class View implements ComponentListener {
    JPanel buttonPanel;

    protected JButton[] buttons;
    protected JPanel panel;
    public View(String[] options, ActionListener[] actions) {
        if (options.length!=actions.length) throw new IndexOutOfBoundsException();

        buttonPanel = new JPanel();
        buttons = new JButton[options.length];
        for(int k=0;k<options.length;k++){
            buttons[k] = new JButton(options[k]);
            buttons[k].addActionListener(actions[k]);
            buttonPanel.add(buttons[k]);
        }
        panel = new JPanel();
    }

    public void show(JFrame frame){
        Container contentPane = frame.getContentPane();
        contentPane.add(panel);
        frame.addComponentListener(this);
        for (int i = 0; i < buttons.length; i++) {
            System.out.println(buttons[i].getText());
        }



    }
    public void hide(JFrame frame){
        Container contentPane = frame.getContentPane();
        contentPane.remove(panel);
        frame.removeComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Rectangle r = e.getComponent().getBounds();
        setSize(r.width, r.height);
    }
    @Override
    public void componentMoved(ComponentEvent e){}
    @Override
    public void componentShown(ComponentEvent e){}
    @Override
    public void componentHidden(ComponentEvent e){}


    protected abstract void setSize(int width, int height);
}
