package games.TestGame.Sokoban;

import engine.BasicTableTop;

public class Level extends BasicTableTop {
    private int playerSpawnX;
    private int playerSpawnY;
    private int victoryCondition;

    public Level(int width, int height, int victoryCondition) {
        super(width, height);
        this.victoryCondition = victoryCondition;
    }

    public int getPlayerSpawnX() {
        return playerSpawnX;
    }

    public void setPlayerSpawn(int x, int y) {
        this.playerSpawnX = x;
        this.playerSpawnY = y;
    }

    public int getPlayerSpawnY() {
        return playerSpawnY;
    }

    public int getVictoryCondition() {
        return victoryCondition;
    }
}