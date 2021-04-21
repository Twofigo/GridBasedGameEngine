package games.TestGame;

import engine.Board;
import engine.Tile;

import java.io.File;
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
        int[][] room = createArray(0,width+2,height+2);
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

        return map;
    }

    public int[][] createRooms(int[][] map){
        int fails = 0;
        while(fails < 20){
            if(createRoom(map) == null);
            {
                fails++;
            }
        }
        return map;
    }


}

class map
{
    int[][] array;
    String walls;

    public map(int[][] bitmap, String texturePath)
    {
        array = bitmap;
        walls = texturePath;
    }

    public Board getBoard(){
        Board board = new Board(array.length,array[0].length);
        Tile Wall = new Tile("src/Texture/dc-dngn/wall/marble_wall9.png");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j]==1){
                    board.set(Wall,i,j);
                }

            }
        }
        return board;
    }



}
