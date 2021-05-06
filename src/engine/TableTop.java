package engine;

/**
 * A tableTop with a number of boards which all have the same width an height
 */
public class TableTop {
    private Board[] boards;
    private int width;
    private int height;

    protected TableTop(){}

    /**
     * Initiates a user decided amount of boards
     * @param boards
     * @throws Exception
     */
    public TableTop(Board[] boards) throws Exception {
        setBoards(boards);
    }

    /**
     * Initiates a single board
     * @param board
     */
    public TableTop(Board board) {
        try {
            setBoards(new Board[]{board});
        }catch (Exception e){
            // this will never happen
        }
    }

    /**
     * Makes sure all boards are the same size before adding them
     * @param boards
     * @throws Exception
     */
    public void setBoards(Board[] boards) throws Exception{
        this.boards = boards;
        this.width = boards[0].width();
        this.height = boards[0].height();
        for (Board b: boards) {
            if(width!=b.width() || height!=b.height()) throw new Exception("board size mismatch");
        }
    }

    /**
     * @return int
     */
    public int width() {
        return width;
    }

    /**
     * @return int
     */
    public int height() {
        return height;
    }

    /**
     * @param index
     * @return Board
     */
    public Board getBoard(int index){
        return boards[index];
    }

    /**
     * @return Board[]
     */
    public Board[] getBoards(){
        return boards;
    }
}
