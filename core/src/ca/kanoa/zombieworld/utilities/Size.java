package ca.kanoa.zombieworld.utilities;

import com.badlogic.gdx.Gdx;

public class Size {

    public static float getWidthCenti(float centimeters) {
        return Gdx.graphics.getPpcX() * centimeters;
    }

    public static float getHeightCenti(float centimeters) {
        return Gdx.graphics.getPpcY() * centimeters;
    }

}
