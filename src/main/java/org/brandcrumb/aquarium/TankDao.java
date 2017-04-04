package org.brandcrumb.aquarium;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class TankDao {

    private static volatile TankDao instance = null;
    private TankDao() { }

    public static synchronized TankDao getInstance() {
        if (instance == null) {
            instance = new TankDao();
        }
        return instance;
    }

    public void deleteAllTanks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery("delete from Fish").executeUpdate();
        session.createQuery("delete from Tank").executeUpdate();
        session.close();
    }

    public Fish getFish(int id) {
        List<Fish> fishes = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select id, name, type, date, price from Fish where id = :fish_id");
        query.setParameter("fish_id", id);
        List allUsers = query.list();
        for (Iterator it = allUsers.iterator(); it.hasNext(); ) {
            Object[] fishObject = (Object[]) it.next();
            Fish fish = new Fish((Integer) fishObject[0]);
            fish.setName((String) fishObject[1]);
            fish.setType((String) fishObject[2]);
            fish.setDate((Date) fishObject[3]);
            fish.setPrice((Double) fishObject[4]);
            fishes.add(fish);
        }
        session.close();
        if (fishes.size() == 0){
            return null;
        }
        return fishes.get(0);
    }

    public List<Tank> getAllTanks() {
        List<Tank> tanks = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select id, name, temperature, volumen from Tank");

        @SuppressWarnings("unchecked")
        List allUsers = query.list();
        for (Iterator it = allUsers.iterator(); it.hasNext(); ) {
            Object[] tankObject = (Object[]) it.next();
            Tank tank = new Tank((Integer) tankObject[0]);
            tank.setName((String) tankObject[1]);
            tank.setTemperature((Double) tankObject[2]);
            tank.setVolumen((double) tankObject[3]);
            Query query1 = session.createQuery("select id, name, type, date, price from Fish where tank.id = :tank_id");
            query1.setParameter("tank_id", tankObject[0]);
            tanks.add(tank);
            if (query1.getFirstResult() != null){
                List allFish = query1.list();
                if (allFish.size() == 0){
                    for (Iterator it1 = allFish.iterator(); it.hasNext();){
                        Object[] fishObject = (Object[]) it1.next();
                        Fish fish = new Fish((Integer) fishObject[0]);
                    }

                }
            }
        }
        session.close();
        return tanks;
    }

   public Tank getTank(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tank tank = (Tank) session.get(Tank.class, id);
        System.out.println("dddddd");
        session.close();
        return tank;
    }


    public void saveOrUpdateTank(Tank tank) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.saveOrUpdate(tank);
        session.flush();
        session.close();
    }


    public Tank deleteTank(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tank tank = getTank(id);
        if (tank != null) {
            session.delete(tank);
            session.flush();
        }
        session.close();
        return tank;
    }

}
