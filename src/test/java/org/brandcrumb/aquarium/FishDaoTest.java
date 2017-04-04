package org.brandcrumb.aquarium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FishDaoTest extends CommonTest {

    @Before
    public void beforeFishDaoTest() {
        insertTanks(1);
    }

    @After
    public void afterFishDaoTest() {
        fishDao.deleteAllFishes();
    }

    @Test
    public void getAllFishesShouldReturnAllFishes() {
        assertThat(fishDao.getAllFishes().size(), is(equalTo(2)));
    }

    @Test
    public void getAllFishesShouldReturnsFishesWithId() {
        Fish actual = fishDao.getAllFishes().get(0);
        Fish actual1 = fishDao.getAllFishes().get(1);
        assertThat(actual.getId(), is(equalTo(id_fish)));
        assertThat(actual1.getId(), is(equalTo(id_fish1)));
        assertThat(actual.getName(), is(equalTo(name_fish)));
        assertThat(actual1.getName(), is(equalTo(name_fish1)));
    }

    @Test
    public void DeleteAllFishesShouldDeleteAllFishes() {
        fishDao.deleteAllFishes();
        assertThat(fishDao.getAllFishes().size(), is(equalTo(0)));
    }

    @Test
    public void getAllFishesShouldReturnFishesAllFishes() {
        assertThat(fishDao.getAllFishes().size(), is(equalTo(2)));
    }

    @Test
    public void getFisheshouldReturnFishWithTheSpecifiedId() {
        Fish actual = fishDao.getFish(id_fish);
        assertThat(actual.getId(), is(equalTo(id_fish)));
    }

    @Test
    public void getFisheshouldReturnNullIfIdDoesNotExist() {
        Fish actual = fishDao.getFish(3);
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void deleteFisheshouldReturnDeletedFish() {
        Fish actual = fishDao.deleteFish(id_fish1);
        assertThat(fishDao.getAllFishes().size()  , is(equalTo(1)));
    }

}
