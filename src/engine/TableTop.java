package engine;

public class TableTop {
    protected final Board[] boards;
    private final int width;
    private final int height;

    public TableTop(Board[] boards, int width, int height) {
        this.boards = boards;
        this.width = width;
        this.height = height;
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
