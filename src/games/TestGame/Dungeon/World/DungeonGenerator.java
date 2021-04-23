package games.TestGame.Dungeon.World;

import engine.TableTop;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DungeonGenerator{
    private final List<Rectangle> rooms = new ArrayList<>();
    private final List<Point> points = new ArrayList<>();

    Set<Integer> xs = new HashSet<>();
    Set<Integer> ys = new HashSet<>();

    private int SIZE_X;
    private int SIZE_Y;
    private int NUMBER_OF_POINTS;
    private int ROOM_MAX_SIZE;
    private int ROOM_MIN_SIZE;
    private int ROOM_MIN_DISTANCE;
    private int RANDOM_PATHS;

    public DungeonGenerator(){
        SIZE_X = 60;
        SIZE_Y = 60;
        NUMBER_OF_POINTS = 10000;
        ROOM_MAX_SIZE = 20;
        ROOM_MIN_SIZE = 10;
        ROOM_MIN_DISTANCE = 0;
        RANDOM_PATHS = 10;
    }
    public void setSIZE_X(int SIZE_X) {
        this.SIZE_X = SIZE_X;
    }

    public void setSIZE_Y(int SIZE_Y) {
        this.SIZE_Y = SIZE_Y;
    }

    public void setNUMBER_OF_POINTS(int NUMBER_OF_POINTS) {
        this.NUMBER_OF_POINTS = NUMBER_OF_POINTS;
    }

    public void setROOM_MAX_SIZE(int ROOM_MAX_SIZE) {
        this.ROOM_MAX_SIZE = ROOM_MAX_SIZE;
    }

    public void setROOM_MIN_SIZE(int ROOM_MIN_SIZE) {
        this.ROOM_MIN_SIZE = ROOM_MIN_SIZE;
    }

    public void setROOM_MIN_DISTANCE(int ROOM_MIN_DISTANCE) {
        this.ROOM_MIN_DISTANCE = ROOM_MIN_DISTANCE;
    }

    public void setRANDOM_PATHS(int RANDOM_PATHS) {
        this.RANDOM_PATHS = RANDOM_PATHS;
    }

    public int[][] generateMap(){

        int[][] bitmap = new int[SIZE_X][SIZE_Y];

        points.clear();
        rooms.clear();

        outer:
        for (int i = 0; i < NUMBER_OF_POINTS; i++) {

            int width = bitmap[0].length;
            int height = bitmap.length;

            int x = (int)(10+((width)-20)*Math.random());
            int y = (int)(10+((height)-20)*Math.random());

            Point p = new Point(x,y);

            int w = (int)(ROOM_MIN_SIZE + ((ROOM_MAX_SIZE - ROOM_MIN_SIZE) * Math.random()));
            int h = (int)(ROOM_MIN_SIZE + ((ROOM_MAX_SIZE - ROOM_MIN_SIZE) * Math.random()));

            //check for intersects between rooms
            Rectangle ra = new Rectangle(p.x - w / 2 , p.y - h / 2 ,w-1,h-1);
            for (Rectangle rb: rooms){
                if(ra.intersects(rb)){
                    continue outer;
                }
            }

            //makes sure each room does not touch others
            ra.x += ROOM_MIN_DISTANCE;
            ra.y += ROOM_MIN_DISTANCE;
            ra.width +=1;
            ra.height +=1;
            ra.width -= 2 * ROOM_MIN_DISTANCE;
            ra.height -= 2 * ROOM_MIN_DISTANCE;

            // checks to make sure rooms center don't align up with edges of other rooms
            if (    xs.contains(ra.x)
                    || xs.contains(p.x)
                    || xs.contains(ra.x + ra.width-1)
                    || ys.contains(ra.y)
                    || ys.contains(p.y)
                    || ys.contains(ra.y + ra.height-1)) {
                continue;
            }
            xs.add(ra.x);
            xs.add(p.x);
            xs.add(ra.x + ra.width-1);
            ys.add(ra.y);
            ys.add(p.y);
            ys.add(ra.y + ra.height-1);

            rooms.add(ra);

            drawRectangle(bitmap, ra.x, ra.y,ra.width,ra.height,2);
            points.add(p);
        }

        drawPaths(bitmap, points);

        for (Rectangle room : rooms) {
            //bitmap = drawRectangle(bitmap,room.x + 1, room.y + 1, room.width - ROOM_MIN_DISTANCE / 2, room.height - ROOM_MIN_DISTANCE / 2,4);
            drawRectangle(bitmap,room.x + 1, room.y + 1, room.width - 2, room.height - 2,4);
        }
        printMap(bitmap);

        return bitmap;
    }
    public void drawPaths(int[][] map, List<Point> points){
        // the rooms that are already attached with one another
        List<Point> tree = new ArrayList<>();
        //tree.clear();
        tree.add(points.remove(0));

        while(!points.isEmpty()) {
            Point a = null;
            Point b = null;

            double minDistance = Double.MAX_VALUE;

            for (Point p1 : tree) {
                for (Point p2 : points) {
                    double dx = p2.x - p1.x;
                    double dy = p2.y - p1.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance < minDistance) {
                        minDistance = distance;
                        a = p1;
                        b = p2;
                    }
                }
            }
            // finds shortest distance from the tree to any un-attached room

            if (a == null || b == null) {
                throw new RuntimeException("we're in deep shit if we crashed here");
            }

            points.remove(b);
            tree.add(b);

            drawPath(map, a, b);
        }

        for(int k=0;k<RANDOM_PATHS;k++){
            int a = (int)(tree.size()*Math.random());
            int b = (int)(tree.size()*Math.random());
            drawPath(map, tree.get(a), tree.get(b));
        }
    }

    public static void drawPath(int[][] map, Point a, Point b){
        //från (a.x;a.y) till (a.x;b.y)

        //från (a.x;b.y) till (b.x;b.y)

        Point walker = new Point();
        if (a.y < b.y){
            walker.y = a.y;
            walker.x = a.x;
        }
        else{
            walker.y = b.y;
            walker.x = b.x;
        }
        for (int i = 0; i <= Math.abs(a.y-b.y); i++) {
            drawPathPoint(map,walker.x, walker.y+i);
        }
        walker.y+=Math.abs(a.y-b.y);
        if (a.x < b.x){
            walker.x = a.x;
        }
        else{
            walker.x = b.x;
        }
        for (int i = 0; i <= Math.abs(a.x-b.x); i++) {
            drawPathPoint(map,walker.x+i, walker.y);
        }
    }
    public static void drawPathPoint(int[][] map, int x, int y){
        map[y][x] = 3;
        for (int ky = -1; ky <= 1; ky++) {
            for (int kx = -1; kx <= 1; kx++) {
                if(map[ky+y][kx+x]==0)map[ky+y][kx+x] = 2;
            }
        }
    }

    public static void printMap(int[][] map){
        System.out.println("\n\n\n\n");
        System.out.println(Arrays.
                deepToString(map
                ).replace("], ", "]\n"
        ).replace("[[", "["
        ).replace("]]", "]"
        ).replace("0"," " //void
        ).replace("3","@" // path
        ).replace("2","#" // wall
        ).replace("4","-" //floor
        ));
    }

    public static void drawRectangle(int[][] map, int x, int y, int width, int height,int fillValue )
    {
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                map[j+y][k+x] = fillValue;
            }
        }
    }
}
