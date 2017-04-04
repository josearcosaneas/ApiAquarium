package org.brandcrumb.aquarium;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FishTest extends CommonTest {

    @Test
    public void idShouldHaveSetterAndGetter() {
        int expected = 123;
        fish.setId(expected);
        assertThat(fish.getId(), is(equalTo(expected)));
    }

    @Test
    public void nameShouldHaveSetterAndGetter() {
        String expected = "Manolo";
        fish.setName(expected);
        assertThat(fish.getName(), is(equalTo(expected)));
    }

    @Test
    public void typeShouldHaveSetterAndGetter() {
        String expected = "shark";
        fish.setType(expected);
        assertThat(fish.getType(), is(equalTo(expected)));
    }

    @Test
    public void priceShouldHaveSetterAndGetter() {
        double expected = 123.45;
        fish.setPrice(expected);
        assertThat(fish.getPrice(), is(equalTo(expected)));
    }


    @Test
    public void equalsShouldFailIfIdIsNotTheSame() {
        Fish actual = new Fish(123);
        actual.setName(name_fish);
        actual.setType(type);
        actual.setDate(date);
        actual.setPrice(price);
        assertThat(actual.getId(), is(not(equalTo(fish.getId()))));
    }


    @Test
    public void equalsShouldReturnFalseIfTitleIsNotTheSame() {
        Fish actual = new Fish(id_fish1);
        actual.setName("Juan");
        actual.setType(type);
        actual.setTank(tank);
        actual.setPrice(price);
        assertThat(actual, is(not(equalTo(fish))));
    }

    @Test
    public void equalsShouldReturnTrueIfFishIsTheSame() {
        Fish actual = new Fish(id_fish);
        actual.setName(name_fish);
        actual.setType(type);
        actual.setDate(date);
        actual.setPrice(price);
        assertThat(actual, is((equalTo(fish))));
    }

    @Test
    public void equalsShouldReturnFalseIfPriceIsNotTheSame() {
        Fish actual = new Fish(id_fish);
        actual.setName(name_fish);
        actual.setType(type);
        actual.setPrice(123.45);
        assertThat(actual, is(not(equalTo(fish))));
    }

    @Test
    public void equalShouldReturnTrueIfIdNameTypePriceAreTheSame() {
        Fish actual = new Fish(id_fish);
        actual.setName(name_fish);
        actual.setType(type);
        actual.setDate(date);
        actual.setPrice(price);
        assertThat(actual, is(equalTo(fish)));
    }

}
