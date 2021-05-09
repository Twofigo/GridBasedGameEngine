package engine.Graphics;

import java.awt.*;

public class FadeSprite extends Sprite {
    /**
     * Constructor
     *
     * @param textureName
     * @param lifespan
     * @param posX
     * @param posY
     */
    protected float alphaStart;
    protected float alphaEnd;

    public FadeSprite(String textureName, int posX, int posY, int lifespan, long startTime){
        super(textureName, posX, posY, lifespan, startTime);
        this.alphaStart = 1;
        this.alphaEnd = 0;
    }

    public void setFade(float alphaStart, float alphaEnd){
        this.alphaStart = alphaStart;
        this.alphaEnd = alphaEnd;
    }

    /**
     * The only way to change opacity in java is with a AlphaComposite object.
     * But despite restoring the composite after changing it, there is still some graphic
     * glitching on the other effects when a new one is added.
     *
     * This can be solved, by drawing every effect on a new separate graphics context. but that would be too inefficient.
     * So the bug remains. It's only visible when the lifetime of the effect is set quite high
     *
     * @param g
     * @param timeStamp
     */
    @Override
    public void render(Graphics g, long timeStamp) {
        long dt = timeStamp-this.timeStamp;

        float alpha = alphaStart + ((alphaEnd - alphaStart) / lifespan)*dt;

        //Composite c = ((Graphics2D) g).getComposite();
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(texture, posX-width/2, posY-height/2, width, height, null);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        //((Graphics2D) g).setComposite(c);
    }

    @Override
    public FadeSprite clone(int lifespan, int startTime) {
        FadeSprite s = new FadeSprite("",0,0,lifespan,startTime);
        s = this.clone(s);
        return s;
    }
    @Override
    public FadeSprite clone(Sprite s){
        return clone((FadeSprite)s);
    }

    public FadeSprite clone(FadeSprite s){
        super.clone(s);
        s.alphaStart = this.alphaStart;
        s.alphaEnd = this.alphaEnd;
        return s;
    }
}
