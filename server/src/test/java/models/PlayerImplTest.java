package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerImplTest {

    private Player player;
    private int id;
    private int age;
    private String country;
    private String name;

    @Before
    public void buildUp() {
        name = "Spiderman";
        country = "America";
        age = 25;
        id = 5;
        player = new PlayerImpl(name, country, age, id);
    }

    @Test
    public void shouldBeAbleToGetId() {
        int actualId = player.getId();
        assertEquals(id, actualId);
    }

    @Test
    public void shouldBeAbleToGetName() {
        String actualName = player.getName();
        assertEquals(name, actualName);
    }

    @Test
    public void shouldBeAbleToGetTheCountryOfThePlayer() {
        String actualCountry = player.getCountry();
        assertEquals(country, actualCountry);
    }

}
