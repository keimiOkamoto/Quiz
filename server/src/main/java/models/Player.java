package models;

public interface Player {

    /**
     * Getter for a players ID.
     *
     * @return The ID of the player.
     */
    int getId();

    /**
     * Getter for a players name.
     *
     * @return The name of the player.
     */
    String getName();

    /**
     * Getter for a players country.
     *
     * @return The country of the player.
     */
    String getCountry();
}
