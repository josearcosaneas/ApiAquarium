package org.brandcrumb.aquarium;

import org.junit.Before;

import java.util.*;

public class CommonTest {

    Calendar calendar = Calendar.getInstance();
    protected final int id_tank = 1;
    protected final String name_tank = "shark";
    protected final double temperature = 20.00;
    protected final double volumen = 5.99;
    protected Tank tank;
    protected TankDao tankDao = TankDao.getInstance();
    protected final int id_fish = 1;
    protected final String name_fish = "Antonio";
    protected final String type = "Blanco";
    protected final Date date = calendar.getTime();
    protected final double price = 500.99;
    protected final int id_fish1 = 2;
    protected final String name_fish1 = "Juan";
    protected final String type1 = "tigre";
    protected final Date date1 = calendar.getTime();
    protected final double price1 = 500.88;
    protected Fish fish;
    protected Fish fish1;
    protected FishDao fishDao = FishDao.getInstance();
    protected Set<Fish> fishSet;

    @Before
    public void beforeCommonTest() {

        tank = new Tank(id_tank);
        tank.setName(name_tank);
        tank.setTemperature(temperature);
        tank.setVolumen(volumen);
        fish = new Fish(id_fish);
        fish.setName(name_fish);
        fish.setType(type);
        fish.setTank(tank);
        fish.setDate(date);;
        fish.setPrice(price);
        fish1 = new Fish(id_fish1);
        fish1.setName(name_fish1);
        fish1.setType(type1);
        fish1.setTank(tank);
        fish1.setDate(date1);;
        fish1.setPrice(price1);
        fishSet = new HashSet<>();
        fishSet.add(fish);
        fishSet.add(fish1);
        tank.setFishes(fishSet);
    }


    protected List<Tank> insertTanks(int count) {
        List<Tank> tanks = new ArrayList<>();
        for (int index = 1; index <= count; index++) {

            Tank tank = new Tank(id_tank);
            tank.setName(name_tank);
            tank.setTemperature(temperature);
            tank.setVolumen(volumen);
            tankDao.saveOrUpdateTank(tank);
            Fish fish = new Fish(id_fish);
            fish.setName(name_fish);
            fish.setType(type);
            fish.setPrice(price);
            fish.setDate(date);
            Fish fish1 = new Fish(id_fish1);
            fish1.setName(name_fish1);
            fish1.setType(type1);
            fish1.setTank(tank);
            fish1.setPrice(price1);
            fish1.setDate(date1);
            Set<Fish> fishes = new HashSet<>();
            fishes.add(fish);
            fishes.add(fish1);
            tank.setFishes(fishes);
            fishDao.saveOrUpdateFish(fish);
            fishDao.saveOrUpdateFish(fish1);
            tankDao.saveOrUpdateTank(tank);

            tanks.add(tank);
        }
        return tanks;
    }

}
