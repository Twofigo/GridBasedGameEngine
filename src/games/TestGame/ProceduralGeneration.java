package games.TestGame;

import java.util.*;

public class ProceduralGeneration {
    public int[][] createArray(int initialNum, int width, int height)
    {
        int[][] arr = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                arr[i][j] = initialNum;
            }
        }
        return createRooms(arr);
    }
    public void printArray(int[][] array)
    {
        System.out.println(Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    private int[][] createRooms(int[][] map)
    {
        Random rand = new Random();
        int width = rand.nextInt((int)(map.length/6)+(int)(map.length/6));
        int height = rand.nextInt((int)(map[0].length/6)+(int)(map[0].length/6));
        int[][] room = createArray(0,width,height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = room[i][j];
            }
        }
        System.out.println(Arrays.deepToString(room).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        return room;
    }


}

class map
{
    int[][] array;
    int maxTunnels;
    int maxLength;

    public map(int size, int maxTunnels, int maxLength)
    {

    }

}
