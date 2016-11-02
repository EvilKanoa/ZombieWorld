package ca.kanoa.zombieworld.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public enum Settings {

    SCREEN_WIDTH("width", SettingsPlatform.DESKTOP, 640),
    SCREEN_HEIGHT("height", SettingsPlatform.DESKTOP, 480);

    private static Preferences preferences;

    private String name;
    private SettingsPlatform platform;
    private Object defaultValue;
    private Class type;

    Settings(String name, SettingsPlatform platform, Object defaultValue) {
        this.name = name;
        this.platform = platform;
        if (defaultValue.getClass() == Integer.class || defaultValue.getClass() == Float.class ||
                defaultValue.getClass() == Boolean.class || defaultValue.getClass() == Long.class) {
            this.defaultValue = defaultValue;
            this.type = defaultValue.getClass();
        } else {
            this.defaultValue = defaultValue.toString();
            this.type = String.class;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return preferences.getString(this.name, this.defaultValue.toString());
    }

    public Long getLongValue() {
        if (type == Long.class) {
            return (Long) preferences.getLong(this.name, (Long) this.defaultValue);
        } else {
            return null;
        }
    }

    public Boolean getBooleanValue() {
        if (type == Boolean.class) {
            return (Boolean) preferences.getBoolean(this.name, (Boolean) this.defaultValue);
        } else {
            return null;
        }
    }

    public Float getFloatValue() {
        if (type == Float.class) {
            return (Float) preferences.getFloat(this.name, (Float) this.defaultValue);
        } else {
            return null;
        }
    }

    public Integer getIntValue() {
        if (type == Integer.class || type == Float.class) {
            return (Integer) preferences.getInteger(this.name, (Integer) this.defaultValue);
        } else {
            return null;
        }
    }

    public void set(Object newValue) {
        if (newValue.getClass() == Integer.class) {
            preferences.putInteger(this.getName(), (Integer) newValue);
        } else if (newValue.getClass() == Long.class) {
            preferences.putLong(this.getName(), (Long) newValue);
        } else if (newValue.getClass() == Float.class) {
            preferences.putFloat(this.getName(), (Float) newValue);
        } else if (newValue.getClass() == Boolean.class) {
            preferences.putBoolean(this.getName(), (Boolean) newValue);
        } else if (newValue.getClass() == String.class) {
            preferences.putString(this.getName(), (String) newValue);
        }
    }

    public void reset() {
        set(defaultValue);
    }

    public static void loadFile(String file) {
        preferences = Gdx.app.getPreferences(file);
        for (Settings setting : values()) {
            if (!preferences.contains(setting.getName())) {
                setting.reset();
            }
        }
        save();
    }

    public static void save() {
        preferences.flush();
    }

    enum SettingsPlatform {
        ANDROID,
        DESKTOP,
        IOS;
    }

}
