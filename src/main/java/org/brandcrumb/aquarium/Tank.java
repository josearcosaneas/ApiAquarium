package org.brandcrumb.aquarium;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tank", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@XmlRootElement
public class Tank {

    public Tank() { }
    public Tank(int id) {
        setId(id);
    }
    public Tank(int id, String name) {
        setId(id);
        setName(name);
    }
    public Tank(int id, String name, Set<Fish> fishes) {
        setId(id);
        setName(name);
        setFishes(fishes);
    }
    public Tank(int id, String name,double temperature, double volumen, Set<Fish> fishes) {
        setId(id);
        setName(name);
        setFishes(fishes);
        setVolumen(volumen);
        setTemperature(temperature);
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

    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "volumen")
    private double volumen;
    public double getVolumen() {
        return volumen;
    }
    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    @Column(name = "temperature")
    private double temperature;
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "tank", orphanRemoval=true)
    @JsonManagedReference
    @Column(nullable = true)
    private Set<Fish> Fishes = new HashSet<>();
    public Set<Fish> getFishes() {
        return this.Fishes;
    }

    public void setFishes(Set<Fish> Fishes) {
        this.Fishes = Fishes;
    }

    public void addFish(Fish fish){
        this.Fishes.add(fish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tank tank = (Tank) o;
        if (id != tank.id) return false;
        if (Double.compare(tank.volumen, volumen) != 0) return false;
        if (Fishes != null ? !Fishes.equals(tank.getFishes()) : tank.getFishes() != null) return false;
        if (Double.compare(tank.temperature, temperature) != 0) return false;
        if (name != null ? !name.equals(tank.name) : tank.name != null) return false;
        return true;
    }


    @Override
    public int hashCode() {
        int result, aux1, aux2;
        long temp_temperature, temp_volumen;

        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp_volumen = Double.doubleToLongBits(volumen);
        temp_temperature = Double.doubleToLongBits(temperature);
        aux1 = (int) (temp_volumen ^ (temp_volumen >>> 32));
        aux2 = (int) (temp_temperature ^ (temp_temperature >>> 32));
        result = 31 * result + aux1 + aux2 + Fishes.hashCode();
        return result;
    }
}
