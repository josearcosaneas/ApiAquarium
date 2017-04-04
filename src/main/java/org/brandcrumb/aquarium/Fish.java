package org.brandcrumb.aquarium;


import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "Fish", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Fish {

    public Fish() { }
    public Fish(int id) {
        setId(id);
    }
    public Fish(int id, Date date) {
        setId(id);
        setDate(date);
    }
    public Fish(int id, String name, String type,  Tank tank, double price, double volume, Date date) {
        setId(id);
        setName(name);
        setType(type);
        setTank(tank);
        setPrice(price);
        setDate(date);
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    @ManyToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.ALL })
    @JoinColumn(name = "tank_id")
    @JsonBackReference
    private Tank tank;
    public Tank getTank() { return tank;}
    public void setTank(Tank tank) {
        this.tank = tank;
    }


    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "price")
    private double price;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "date", unique = true, nullable = false, length = 10)
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fish fish = (Fish) o;

        if (id != fish.id) return false;
        if (Double.compare(fish.price, price) != 0) return false;
        if (date.compareTo(fish.date) != 0) return false;
        if (type != null ? !type.equals(fish.type) : fish.type != null) return false;
        if (name != null ? !name.equals(fish.name) : fish.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}
