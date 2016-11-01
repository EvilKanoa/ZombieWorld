package ca.kanoa.zombieworld;

public interface Updateable {

    /**
     * Updates an object which supports it and gives the time in milliseconds since the last update
     * @param delta The time in milliseconds since the last update
     */
    void update(long delta);

}
