package org.brandcrumb.aquarium;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FishDao {

    private static volatile FishDao instance = null;
    private FishDao() { }

    public static synchronized FishDao getInstance() {
        if (instance == null) {
            instance = new FishDao();
        }
        return instance;
    }

    public void deleteAllFishes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery("delete from Fish").executeUpdate();
        session.close();
    }

    public List<Fish> getAllFishes() {
        List<Fish> fishes = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select id, name, type, date, price from Fish");
        @SuppressWarnings("unchecked")
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
        return fishes;
    }

    public List<Fish> getAllFishesOfTank(int tank_id) {
        List<Fish> fishes = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select id, name, type, date, price from Fish where tank.id = :tank_id");
        query.setParameter("tank_id", tank_id);
        @SuppressWarnings("unchecked")
        List allUsers = query.list();
        for (Iterator it = allUsers.iterator(); it.hasNext(); ) {
            Object[] fishObject = (Object[]) it.next();
            Fish fish = new Fish((Integer) fishObject[0]);
            fish.setName((String) fishObject[1]);
            fishes.add(fish);
        }
        session.close();
        return fishes;
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

    public void saveOrUpdateFish(Fish fish) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.saveOrUpdate(fish);
        session.flush();
        session.close();
    }

    public Fish deleteFish(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Fish fish = getFish(id);
        System.out.println(fish);
        if (fish != null) {
            session.delete(fish);
        }
        else {
            session.flush();
            session.close();
            return  fish;
        }
        session.flush();
        session.close();
        return null;
    }

}
