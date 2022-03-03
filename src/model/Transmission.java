package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Transmission {

    public Transmission(TransmissionType transmissionType, int numberOfGears, int numberOfClutches) {
        this.transmissionType = transmissionType;
        this.numberOfGears = numberOfGears;
        this.numberOfClutches = numberOfClutches;
    }

    public Transmission() {}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int transmissionId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @NotNull
    private int numberOfGears;

    @NotNull
    private int numberOfClutches;

    public int getTransmissionId() {
        return transmissionId;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getNumberOfGears() {
        return numberOfGears;
    }

    public void setNumberOfGears(int numberOfGears) {
        this.numberOfGears = numberOfGears;
    }

    public int getNumberOfClutches() {
        return numberOfClutches;
    }

    public void setNumberOfClutches(int numberOfClutches) {
        this.numberOfClutches = numberOfClutches;
    }
}
