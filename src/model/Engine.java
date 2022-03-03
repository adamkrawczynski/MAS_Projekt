package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Engine {

    public Engine(EngineType engineType, int power, int capacity, int torque, boolean gasFitting) {
        this.engineType = engineType;
        this.power = power;
        this.capacity = capacity;
        this.torque = torque;
        this.gasFitting = gasFitting;
    }

    public Engine(){}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int engineId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @NotNull
    private int power;

    @NotNull
    private int capacity;

    @NotNull
    private int torque;

    @NotNull
    private boolean gasFitting;

    public int getEngineId() {
        return engineId;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public boolean isGasFitting() {
        return gasFitting;
    }

    public void setGasFitting(boolean gasFitting) {
        this.gasFitting = gasFitting;
    }
}
