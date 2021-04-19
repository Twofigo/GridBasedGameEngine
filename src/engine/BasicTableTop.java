package engine;

public class BasicTableTop extends TableTop{

    public BasicTableTop(int width, int height){
        super();
        Board[] boards = new Board[3];
        boards[0] = new Board(width, height);
        boards[1] = new Board(width, height);
        boards[2] = new Board(width, height);
        try{
            setBoards(boards);
        }catch (Exception e){
            // this will never happen;
        }
    }
    public Board getBackground(){
        return getBoard(0);
    }
    public Board getMiddleground(){
        return getBoard(1);
    }
    public Board getForeground(){
        return getBoard(2);
    }
}
