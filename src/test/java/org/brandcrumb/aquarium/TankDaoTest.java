package org.brandcrumb.aquarium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TankDaoTest extends CommonTest {

    @Before
    public void beforeTankDaoTest() {
        insertTanks(1);
    }

    @Test
    public void deleteAllTanksShouldDeleteAllTanks() {
        tankDao.deleteAllTanks();
        assertThat(tankDao.getAllTanks().size(), is(equalTo(0)));
    }

    @Test
    public void getAllTanksShouldReturnsTanksWithId() {
        Tank actual = tankDao.getAllTanks().get(0);
        System.out.println(actual.getName());
        assertThat(actual.getId(), is(equalTo(id_tank)));
    }

    @Test
    public void getAllTanksShouldReturnsTanksWithName() {
        Tank actual = tankDao.getAllTanks().get(0);
        System.out.println(actual.getName());
        assertThat(actual.getName(), is(equalTo(name_tank)));
    }

    @Test
    public void getAllTanksShouldReturnsTanksWithoutNotNullValue() {
        Tank actual = tankDao.getAllTanks().get(0);
        assertThat(actual, is(not(nullValue())));
    }

    @Test
    public void getTankshouldReturnTankWithTheSpecifiedId() {
        Tank actual = tankDao.getTank(1);
        assertThat(actual.getId(), is(equalTo(tank.getId())));
    }

    @Test
    public void saveOrUpdateTankshouldUpdateExistingTank() {
        tank.setVolumen(2.54);
        tankDao.saveOrUpdateTank(tank);
        assertThat(tankDao.getAllTanks().size(), is(equalTo(1)));
        assertThat(tankDao.getAllTanks().get(0).getVolumen(), is(equalTo(tank.getVolumen())));
    }

}
