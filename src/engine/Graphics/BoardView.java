package engine.Graphics;

import engine.TableTop;

/**
 *
 */
public class BoardView extends CanvasView {
    /**
     * Canvas view with a Board renderer, quick way to create a game, similar to BasicTableTop
     * @param name
     * @param tb
     */
    public BoardView(String name, TableTop tb) {
        super(name);
        BoardRenderer br = new BoardRenderer(tb);
        addRenderer(br);
        int w = tb.width();
        br.setOffset(w/2,w/2);
        draw();
    }

    /**
     * @return BoardRenderer
     */
    public BoardRenderer getBoardRenderer(){
        return (BoardRenderer) getRenderer(0);
    }
}
