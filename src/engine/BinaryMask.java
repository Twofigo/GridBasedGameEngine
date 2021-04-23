package engine;

public class BinaryMask {
    private int width;
    private int height;
    private boolean[][] mask;

    public BinaryMask(int width, int height){
        this.width = width;
        this.height = height;
        this.mask = new boolean[width][height];
        clear(false);
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    public boolean get(int x, int y){
        if(outOfBounds(x,y)) return false;
        return mask[x][y];
    }
    public void set(boolean state, int x, int y){
        if(outOfBounds(x,y)) return;
        mask[x][y] = state;
    }
    public boolean outOfBounds(int x, int y){
        if (x<0 || x>=width) return true;
        if (y<0 || y>=height) return true;
        return false;
    }
    public BinaryMask mergeAND(BinaryMask obj){
        for (int y=0;y<height; y++){
            for (int x=0;x<width; x++){
                set(get(x,y) && obj.get(x,y), x,y);
            }
        }
        return this;
    }
    public BinaryMask mergeOR(BinaryMask obj){
        for (int y=0;y<height; y++){
            for (int x=0;x<width; x++){
                set(get(x,y) || obj.get(x,y), x,y);
            }
        }
        return this;
    }
    public BinaryMask clear(boolean state){
        for (int y=0;y<height; y++){
            for (int x=0;x<width; x++){
                set(state, x,y);
            }
        }
        return this;
    }
    public BinaryMask invert(){
        for (int y=0;y<height; y++){
            for (int x=0;x<width; x++){
                set(!get(x,y), x,y);
            }
        }
        return this;
    }
    public BinaryMask clearRect(boolean state, int x, int y, int width, int height){
        for (int ky=0;ky<height; ky++){
            for (int kx=0;kx<width; kx++){
                set(state, kx+x,ky+y);
            }
        }
        return this;
    }
}
