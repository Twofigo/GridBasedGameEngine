package engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextureHandler {
    private static TextureHandler TH = new TextureHandler();
    private ArrayList<TextureNode> list;
    private String rootPath;

    public static TextureHandler getInstance(){
        return TH;
    }
    private TextureHandler() { // TextureHandler is a singleton
        list = new ArrayList<TextureNode>();
        rootPath = "";
        list.add(new TextureNode(null, "default"));
    }

    public Image getTexture(String name){
        for (TextureNode n: list) {
            if(n.name.equals(name)){
                return n.image;
                // using a unsorted arraylist here hurts my soul,
                // but java (for some dumb reason) does not have a self sorting data structure that can use binary search
                // to find an object based on a comparator, so this is the solution we had to go with.
                // priority cue has a "contains()" which is great.... if it could please return the object it found
            }
        }
        return list.get(0).image;
    }
    public void setRootPath(String path){
        rootPath=path;
    }
    public boolean setDefaultTexture(String path){
        Image img = ImgFromPath(path);
        if (img!=null){
            list.get(0).image = img;
            return true;
        }
        return false;
    }
    public boolean addTexture(String path, String name){
        for (TextureNode n: list) {
            if(n.name.equals(name)) return false;
        }
        Image img = ImgFromPath(path);
        if (img!=null){
            list.add(new TextureNode(img,name));
            return true;
        }
        return false;
    }
    private Image ImgFromPath(String path){
        Image img;
        try {
            File f = new File(rootPath+path);
            img = ImageIO.read(f);
        } catch (IOException e) {
            return null;
        }
        return img;
    }
}
class TextureNode{
    public String name;
    public Image image;

    public TextureNode(Image image,String name) {
        this.name = name;
        this.image = image;
    }
}
