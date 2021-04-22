package games.TestGame.Dungeon;

import engine.TableTop;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DungeonGenerator{
    int[][] bitmap = new int[60][60];
    private static final int NUMBER_OF_POINTS = 10000;

    private final List<Point> points = new ArrayList<>();
    private final List<Point> tree = new ArrayList<>();

    private static final int ROOM_MAX_SIZE = 20;
    private static final int ROOM_MIN_SIZE = 10;
    private static final int ROOM_MIN_DISTANCE = 2;

    private final List<Rectangle> rooms = new ArrayList<>();

    public DungeonGenerator(){

        Set<Integer> xs = new HashSet<>();
        Set<Integer> ys = new HashSet<>();

        points.clear();
        tree.clear();
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
            Rectangle ra = new Rectangle(p.x - w / 2 , p.y - h / 2 ,w,h);
            for (Rectangle rb: rooms){
                if(ra.intersects(rb)){
                    continue outer;
                }
            }

            //makes sure each room does not touch others
            ra.x += ROOM_MIN_DISTANCE;
            ra.y += ROOM_MIN_DISTANCE;
            ra.width -= 2 * ROOM_MIN_DISTANCE;
            ra.height -= 2 * ROOM_MIN_DISTANCE;

            if (xs.contains(ra.x) || xs.contains(ra.x + ra.width / 2)
                    || xs.contains(ra.x + ra.width)
                    || ys.contains(ra.y) || ys.contains(ra.y + ra.height / 2)
                    || ys.contains(ra.y + ra.height)) {

                continue;
            }
            int d = 0;
            xs.add(ra.x + d);
            xs.add(ra.x + ra.width / 2 + d);
            xs.add(ra.x + ra.width + d);
            ys.add(ra.y + d);
            ys.add(ra.y + ra.height / 2 + d);
            ys.add(ra.y + ra.height + d);

            rooms.add(ra);

            bitmap = drawRectangle(bitmap, ra.x, ra.y,ra.width,ra.height,2);
            points.add(p);

        }

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

            if (a == null || b == null) {
                throw new RuntimeException("we're in deep shit if we crashed here");
            }

            points.remove(b);
            tree.add(b);

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
                bitmap[walker.y+i][walker.x] = 3;
            }
            walker.y+=Math.abs(a.y-b.y);
            if (a.x < b.x){
                walker.x = a.x;
            }
            else{
                walker.x = b.x;
            }
            for (int i = 0; i <= Math.abs(a.x-b.x); i++) {
                bitmap[walker.y][walker.x+i] = 3;
            }

            for (Rectangle room : rooms) {
                //bitmap = drawRectangle(bitmap,room.x + 1, room.y + 1, room.width - ROOM_MIN_DISTANCE / 2, room.height - ROOM_MIN_DISTANCE / 2,4);
                bitmap = drawRectangle(bitmap,room.x + 1, room.y + 1, room.width - 2, room.height - 2,4);
            }

            System.out.println("\n\n\n\n");
            System.out.println(Arrays.
                    deepToString(bitmap
                    ).replace("], ", "]\n"
                    ).replace("[[", "["
                    ).replace("]]", "]"
                    ).replace("0"," " //void
                    ).replace("3","@" // path
                    ).replace("2","#" // wall
                    ).replace("4","-" //floor
                    ));
        }
    }

    public int[][] getBitmap(){
        return bitmap;
    }

    public int[][] drawRectangle(int[][] map, int x, int y, int width, int height,int fillValue )
    {
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                map[j+y][k+x] = fillValue;
            }
        }
        return map;
    }
}
