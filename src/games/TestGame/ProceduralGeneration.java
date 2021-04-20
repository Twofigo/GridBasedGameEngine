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
        return arr;
    }
    public void printArray(int[][] array)
    {
        System.out.println(Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    public int[][] createRoom(int[][] map){
        Random rand = new Random();
        int width = rand.nextInt((int)(map.length/6)+(int)(map.length/6));
        int height = rand.nextInt((int)(map[0].length/6)+(int)(map[0].length/6));
        int[][] room = createArray(0,width+3,height+3);
        int posX = rand.nextInt(map.length-room.length);
        int posY = rand.nextInt(map[0].length-room[0].length);
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if(map[posX+i][posY+j]==room[i][j]){
                    return null;
                }
                map[posX+i][posY+j] = room[i][j];
            }
        }
        System.out.println(Arrays.deepToString(map).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println(Arrays.deepToString(room).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        return map;
    }

    public int[][] createRooms(int[][] map){

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
