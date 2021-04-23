package games.TestGame.Dungeon;

public class Monster extends Creature{

    private int damage;
    public Monster(String name, int dmg) {
        super(name);
        damage = dmg;
    }

    @Override
    public void update() {

    }
}
