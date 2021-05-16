package engine.Graphics;

import java.awt.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SpriteRenderer extends RealTimeRenderer{
    protected PriorityQueue<Sprite> sprites;

    public static void main(String[] args) {
        System.out.println("Test SpriteRenderer: "+(new SpriteRenderer(100,100)).test_SpriteRenderer());

    }
    public SpriteRenderer(int innerWidth, int innerHeight) {
        super(innerWidth, innerHeight);
        sprites = new PriorityQueue<Sprite>(4, new Comparator<Sprite>() {
            @Override
            public int compare(Sprite o1, Sprite o2) {
                return (int)(o1.getLifespan()+o1.getTimeStamp() - (o2.getLifespan()+o2.getTimeStamp()));
            }
        });
    }

    public boolean test_SpriteRenderer() {
        FadeSprite sprite = new FadeSprite("Pow!",50,50,10,System.currentTimeMillis());
        sprite.setSize(200,200);

        this.addSprite(sprite.clone(10,100)); //110 2
        this.addSprite(sprite.clone(15,100)); //115 3
        this.addSprite(sprite.clone(30,100)); //130 4
        this.addSprite(sprite.clone(10,50)); //60 0
        this.addSprite(sprite.clone(40,50)); //90 1
        Sprite s = sprites.poll();
        if(s.getLifespan()+s.getTimeStamp() != 60) return false;
        s = sprites.poll();
        if(s.getLifespan()+s.getTimeStamp() != 90) return false;
        s = sprites.poll();
        if(s.getLifespan()+s.getTimeStamp() != 110) return false;
        s = sprites.poll();
        if(s.getLifespan()+s.getTimeStamp() != 115) return false;
        s = sprites.poll();
        if(s.getLifespan()+s.getTimeStamp() != 130) return false;

        return true;
    }

    public void addSprite(Sprite s){
        sprites.offer(s);
    }

    @Override
    public void render(Graphics g, long timeStamp) {

        do { // remove all expired effects
            Sprite s = sprites.peek();
            if (s==null)return;
            if ((s.getTimeStamp()+s.getLifespan())<timeStamp){
                sprites.poll();
                continue;
            }
        }while(false);
        // the IDE complains about this, but i see no reason why this logic would be problematic, so it stays

        for (Sprite s:sprites) {
            s.render(g,timeStamp);
        }
    }

    @Override
    public void render(Graphics g){

        ;
    }

}
