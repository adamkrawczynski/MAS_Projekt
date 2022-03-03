package model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AirConditioning {

    public AirConditioning(int numberOfZones, String refrigerant) {
        this.numberOfZones = numberOfZones;
        this.refrigerant = refrigerant;
    }

    public AirConditioning() {

    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int airConditioningId;

    @NotNull
    private int numberOfZones;

    @Column(length = 10)
    private String refrigerant;

    public int getAirConditioningId() {
        return airConditioningId;
    }

    public int getNumberOfZones() {
        return numberOfZones;
    }

    public void setNumberOfZones(int numberOfZones) {
        this.numberOfZones = numberOfZones;
    }

    public String getRefrigerant() {
        return refrigerant;
    }

    public void setRefrigerant(String refrigerant) {
        this.refrigerant = refrigerant;
    }
}
