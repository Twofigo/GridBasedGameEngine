package engine;

public class TableTop {
    private Board[] boards;
    private int width;
    private int height;

    protected TableTop(){}
    public TableTop(Board[] boards) throws Exception {
        setBoards(boards);
    }
    public TableTop(Board board) {
        try {
            setBoards(new Board[]{board});
        }catch (Exception e){
            // this will never happen
        }
    }

    public void setBoards(Board[] boards) throws Exception{
        this.boards = boards;
        this.width = boards[0].width();
        this.height = boards[0].height();
        for (Board b: boards) {
            if(width!=b.width() || height!=b.height()) throw new Exception("board size mismatch");
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
    public Board getBoard(int index){
        return boards[index];
    }
    public Board[] getBoards(){
        return boards;
    }
}
