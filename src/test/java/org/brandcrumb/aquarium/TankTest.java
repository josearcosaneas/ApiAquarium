package org.brandcrumb.aquarium;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TankTest extends CommonTest {

    @Test
    public void idShouldHaveSetterAndGetter() {
        int expected = 123;
        tank.setId(expected);
        assertThat(tank.getId(), is(equalTo(expected)));
    }


    @Test
    public void nameShouldHaveSetterAndGetter() {
        String expected = "new name";
        tank.setName(expected);
        assertThat(tank.getName(), is(equalTo(expected)));
    }

    @Test
    public void typeShouldHaveSetterAndGetter() {
        double expected = 32.54;
        tank.setTemperature(expected);
        assertThat(tank.getTemperature(), is(equalTo(expected)));
    }

    @Test
    public void priceShouldHaveSetterAndGetter() {
        double expected = 123.45;
        tank.setVolumen(expected);
        assertThat(tank.getVolumen(), is(equalTo(expected)));
    }

    @Test
    public void equalsShouldFailIfIdIsNotTheSame() {
        Tank actual = new Tank(123);
        actual.setName(name_tank);
        actual.setTemperature(temperature);
        actual.setVolumen(volumen);
        assertThat(actual, is(not(equalTo(tank))));
    }

    @Test
    public void equalsShouldReturnFalseIfNameIsNotTheSame() {
        Tank actual = new Tank(id_tank);
        actual.setName(name_tank);
        assertThat(actual.getName(), is((equalTo(name_tank))));
    }


    @Test
    public void equalsShouldReturnFalseIfVolumenIsNotTheSame() {
        Tank actual = new Tank(id_tank);
        actual.setVolumen(volumen);
        assertThat(actual.getVolumen(), is((equalTo(volumen))));
    }

    @Test
    public void equalsShouldReturnFalseIfTemperatureIsNotTheSame() {
        Tank actual = new Tank(id_tank);
        actual.setTemperature(temperature);
        assertThat(actual.getTemperature(), is(equalTo(temperature)));
    }

    @Test
    public void equalShouldReturnTrueIfIdNameTemperatureAndVolumenAreTheSame() {
        Tank actual = new Tank(id_tank);
        actual.setName(name_tank);
        actual.setTemperature(temperature);
        actual.setVolumen(volumen);
        actual.setFishes(fishSet);
        assertThat(actual, is(equalTo(tank)));
    }

}
