package games.TestGame.Dungeon.Inventory;

import engine.Board;
import engine.TableTop;

public class Inventory extends TableTop {

    public Inventory(int width, int height){
        super();
        Board[] boards = new Board[2];
        boards[0] = new Board(width, height);
        boards[1] = new Board(width, height);
        try{
            setBoards(boards);
        }catch (Exception e){
            // this will never happen;
        }
    }
    public Board getBackground(){
        return getBoard(0);
    }
    public Board getForeground(){
        return getBoard(1);
    }
}