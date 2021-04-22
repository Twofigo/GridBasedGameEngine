package games.TestGame.Dungeon.World;

import engine.Board;
import engine.TableTop;

public class Level extends TableTop {

    public Level(int width, int height){
        super();
        Board[] boards = new Board[4];
        boards[0] = new Board(width, height);
        boards[1] = new Board(width, height);
        boards[2] = new Board(width, height);
        boards[3] = new Board(width, height);
        try{
            setBoards(boards);
        }catch (Exception e){
            // this will never happen;
        }
    }
    public Board getBackground(){
        return getBoard(0);
    }
    public Board getFloor(){
        return getBoard(1);
    }
    public Board getMiddleground(){
        return getBoard(2);
    }
    public Board getForeground(){
        return getBoard(3);
    }
}
