package ca.kanoa.zombieworld;

import ca.kanoa.zombieworld.entities.Player;

public class GameWorld implements Updateable, Drawable {

    private Player player;

    public GameWorld() {
        this.player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void render() {
        player.render();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update(long delta) {
        player.update(delta);
    }
}
