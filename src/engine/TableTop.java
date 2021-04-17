package engine;

public class TableTop {
    protected final Board[] boards;
    private final int width;
    private final int height;

    public TableTop(Board[] boards) throws Exception {
        this.boards = boards;
        this.width = boards[0].width();
        this.height = boards[0].height();
        for (Board b: boards) {
            if(width!=b.width() || height!=b.height()) throw new Exception("board size mismatch");
        }
    }
    public TableTop(Board board) {
        this.boards = new Board[]{board};
        this.width = boards[0].width();
        this.height = boards[0].height();
    }
    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
    public Board[] getBoards(){
        return boards;
    }
}
