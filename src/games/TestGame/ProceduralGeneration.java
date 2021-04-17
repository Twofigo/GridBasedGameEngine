package games.TestGame;

import java.util.Arrays;

public class ProceduralGeneration {
    public int[][] createArray(int initialNum, int size)
    {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = initialNum;
            }
        }
        return array;
    }
    public void printArray(int[][] array)
    {
        System.out.println(Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}

class map
{
    int size;
    int maxTunnels;
    int maxLength;

    public map(int size, int maxTunnels, int maxLength)
    {

    }

}
