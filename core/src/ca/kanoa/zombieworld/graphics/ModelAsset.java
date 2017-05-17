package ca.kanoa.zombieworld.graphics;

public enum ModelAsset {

    ZOMBIE_1("models/zombie1.g3db"),
    CUBE_1("models/cube1.g3dj");

    private String filename;

    ModelAsset(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }

}
