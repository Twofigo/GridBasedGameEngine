package engine.tools;
/*
public static void runTests()
  runs all methods with test data to make sure everything works, and prints to the console.

public static int[][] pathfinder(int[][] matrix) throws IllegalArgumentException
  Calls pathfinder with start at (0,0) and end in bottom right corner

public static int[][] pathfinder(int[][] matrix, int fromX, int fromY, int toX, int toY) throws IllegalArgumentException
  uses "Dijkstra’s shortest path" algorithm
  returns a matrix with the shortest path written as 1 in a matrix of 0.
  takes a matrix of a labyrinth (-1 is a wall) and start end enp point
  throws IllegalArgumentException error if no path was found

private static int[][] distaceCloud(int[][] matrix, int fromX, int fromY, int toX, int toY) throws IllegalArgumentException
  returns a matrix of the shortest distance to every point in the course matrix.
  takes a matrix of a labyrinth (-1 is a wall) and start end enp point
  throws IllegalArgumentException error if no path was found

private static int[][] createMatix(int rows, int columns, int value)
  creates an empty matrix (2-dimentional int array), with specified size and default value-

private static int[][] copyMatix(int[][] m)
  creates a copy of a given matrix

private static void printMatrix(int[][] m)
  prints a matrix to the console.
*/

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args){
        int[][] matrix =
                {{4, 3, 8, 7, 2, 7},
                        {2, 8, 8, 9, 3, 5},
                        {1, 4, 1, 9, 4, 2},
                        {2, 8, 3, 9, 5, 9},
                        {9, 9, 8, 3, 1, 7},
                        {5, 6, 1, 3, 7, 2},
                        {9, 2, 2, 5, 6, 3},
                        {1, 3, 3, 8, 3, 1},
                        {8, 4, 6, 8, 2, 5}};
        try{
            System.out.println("mazeMatrix");
            printMatrix(matrix);
            System.out.println("pathMatrix");
            printMatrix(pathfinder(matrix));
        } catch(Exception e){
            System.out.println(e);
        }

        int[][] matrix2 =
                {{4, -1, 8, 7, 2, 7},
                        {2, -1, 8, 9, 3, 5},
                        {1, -1, 1, 9, 4, 2},
                        {2, -1, 3, -1, 5, 9},
                        {9, 9, 8, -1, 1, 7},
                        {-1, -1, -1, -1, 7, 2},
                        {9, 2, 2, 5, 6, 3},
                        {1, 3, 3, -1, -1, -1},
                        {8, 4, 6, 8, 2, 5}};
        try{

            System.out.println("mazeMatrix");
            printMatrix(matrix2);
            System.out.println("pathMatrix");
            printMatrix(pathfinder(matrix2));
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public static int[][] pathfinder(int[][] matrix) throws IllegalArgumentException
    {
        return pathfinder(matrix, 0,0, matrix[0].length-1, matrix.length-1);
    }
    public static int[][] pathfinder(int[][] matrix, int fromX, int fromY, int toX, int toY) throws IllegalArgumentException
    {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] pathMatrix = createMatrix(rows, columns, 0);
        int[][] distMatrix = distanceCloud(matrix, fromX, fromY, toX, toY);
        int dist;
        int[] walker = {toY,toX};
        int[] newWalker = {toY,toX};
        pathMatrix[toY][toX] = 1;

        while(true){
            dist=-1;

            int x=0;
            int y=0;
            for(int k=0;k<4;k++) {
                if (k==0 && walker[0] >= 1) {               // walk up
                    y = walker[0] - 1;
                    x = walker[1];
                }
                else if (k==1 && walker[1] >= 1) {               // walk left
                    y = walker[0];
                    x = walker[1] - 1;
                }
                else if (k==2 && walker[0] < (rows - 1)) {         // walk down
                    y = walker[0] + 1;
                    x = walker[1];
                }
                else if (k==3 && walker[1] < (columns - 1)) {      // walk right
                    y = walker[0];
                    x = walker[1] + 1;
                }
                if(distMatrix[y][x]!=-1) {
                    if (dist==-1 || dist > distMatrix[y][x]) {
                        dist = distMatrix[y][x];
                        newWalker[0] = y;
                        newWalker[1] = x;
                    }
                }
            }
            walker[0] = newWalker[0];
            walker[1] = newWalker[1];
            pathMatrix[walker[0]][walker[1]]=1;
            if (walker[0]==fromY && walker[1]==fromX) break;
        }

        return pathMatrix;
    }
    private static int[][] distanceCloud(int[][] matrix, int fromX, int fromY, int toX, int toY) throws IllegalArgumentException
    {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] distMatrix = createMatrix(rows, columns, -1);
        int[][] walkedMatrix = createMatrix(rows, columns, 0);
        int[] walker = {fromY,fromX};// row,colum : y,x
        int dist;
        distMatrix[fromY][fromX] = matrix[fromY][fromX];
        walkedMatrix[fromY][fromX] = 1;

        // making value of -1 not count as path (-1 = wall)
        for(int row=0;row<rows;row++){
            for(int col=0;col<columns;col++){
                if(matrix[row][col]==-1){
                    distMatrix[row][col] = -1;
                    walkedMatrix[row][col] = 2;
                }
            }
        }
        while(true){
            // find smallest walker
            walker[0] = -1;
            walker[1] = -1;
            for(int row=0;row<rows;row++){
                for(int col=0;col<columns;col++){
                    if(walkedMatrix[row][col]!=1) continue;
                    if(walker[0]==-1 || distMatrix[row][col]<distMatrix[walker[0]][walker[1]]){
                        walker[0] = row;
                        walker[1] = col;
                    }
                }
            }
            if (walker[0]==-1){
                throw new IllegalArgumentException("no path found");
            }
            // walk one walker 1 step in all directions
            int x=0;
            int y=0;
            for(int k=0;k<4;k++) {
                if (k==0 && walker[0] >= 1) {               // walk up
                    y = walker[0] - 1;
                    x = walker[1];
                }
                else if (k==1 && walker[1] >= 1) {               // walk left
                    y = walker[0];
                    x = walker[1] - 1;

                }
                else if (k==2 && walker[0] < (rows - 1)) {         // walk down
                    y = walker[0] + 1;
                    x = walker[1];
                }
                else if (k==3 && walker[1] < (columns - 1)) {      // walk right
                    y = walker[0];
                    x = walker[1] + 1;
                }

                if(walkedMatrix[y][x] != 2) {
                    dist = matrix[y][x] + distMatrix[walker[0]][walker[1]];
                    if (walkedMatrix[y][x] == 0 || distMatrix[y][x] > dist) {
                        distMatrix[y][x] = dist;
                        walkedMatrix[y][x] = 1;
                    }
                }
            }
            walkedMatrix[walker[0]][walker[1]] = 2;

            // if goal reached
            if (walkedMatrix[toY][toX]==1) break;
        }
        // remove distance to all walkers who did not reach the goal
        for(int row=0;row<rows;row++){
            for(int col=0;col<columns;col++){
                if(walkedMatrix[row][col]!=1) continue;
                if(row==toY && col==toX) continue;
                distMatrix[row][col]=-1;
            }
        }
        return distMatrix;
    }
    private static int[][] createMatrix(int rows, int columns, int value){
        int[][] m = new int[rows][columns];
        for(int row=0;row<rows;row++){
            for(int col=0;col<columns;col++){
                m[row][col] = value;
            }
        }
        return m;
    }
    private static int[][] copyMatrix(int[][] m){
        int[][] newM = new int[m.length][m[0].length];
        for(int row=0;row<m.length;row++){
            for(int col=0;col<m[0].length;col++){
                newM[row][col] = m[row][col];
            }
        }
        return newM;
    }
    private static void printMatrix(int[][] m){
        for(int row=0;row<m.length;row++){
            System.out.println(Arrays.toString(m[row]));
        }
        System.out.println();
    }
}
