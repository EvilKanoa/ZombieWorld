package ca.kanoa.zombieworld;

import com.badlogic.gdx.ApplicationAdapter;

public abstract class OrganizedApplicationAdapter extends ApplicationAdapter {

    @Override
    public void render() {
        updateGame();
        renderGame();
    }

    public abstract void updateGame();
    public abstract void renderGame();

}
