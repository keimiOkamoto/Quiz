package controllers;

public interface PlayerFactory {
    /**
     * Generates a player.
     *
     * @param name The name of the player.
     * @param country The country of the player.
     * @param age The age of the player.
     * @return A player.
     */
    Player generatePlayer(String name, String country, int age);
}
