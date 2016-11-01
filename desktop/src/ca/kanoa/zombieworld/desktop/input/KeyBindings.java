package ca.kanoa.zombieworld.desktop.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import java.io.File;
import java.util.HashMap;

public class KeyBindings {

    private HashMap<String, Integer> bindings;

    public KeyBindings(File configFile) {
        //TODO: Implement customizable keybindings
    }

    public KeyBindings() {
        bindings = new HashMap<String, Integer>();
        // static constant keybindings until config files are implemented
        bindings.put("up"           , Keys.W        );
        bindings.put("down"         , Keys.S        );
        bindings.put("left"         , Keys.A        );
        bindings.put("right"        , Keys.D        );
        bindings.put("aim_up"       , Keys.UP       );
        bindings.put("aim_down"     , Keys.DOWN     );
        bindings.put("aim_left"     , Keys.LEFT     );
        bindings.put("aim_right"    , Keys.RIGHT    );
    }

    public int getBinding(String command) {
        if (bindings.containsKey(command)) {
            return bindings.get(command);
        } else {
            return -1;
        }
    }

    public boolean keyDown(String keyCommand) {
        return Gdx.input.isKeyPressed(getBinding(keyCommand));
    }

    public boolean keyJustDown(String keyCommand) {
        return Gdx.input.isKeyJustPressed(getBinding(keyCommand));
    }

}
