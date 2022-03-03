package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    public Car(StatusType status, CarType carType, int numberOfOwners, boolean wasCrashed, String brand, String model,
               int modelYear, int mileage, String flaws, int price, String driveType, Dealership dealership,
               Design design, Engine engine, Transmission transmission, AirConditioning airConditioning) {
        this.status = status;
        this.carType = carType;
        this.numberOfOwners = numberOfOwners;
        this.wasCrashed = wasCrashed;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
        this.mileage = mileage;
        this.flaws = flaws;
        this.price = price;
        this.driveType = driveType;
        this.dealership = dealership;
        this.design = design;
        this.engine = engine;
        this.transmission = transmission;
        this.airConditioning = airConditioning;
    }

    public Car() {

    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int carId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarType carType;

    private int numberOfOwners;

    private boolean wasCrashed;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private int modelYear;

    @NotNull
    private int mileage;

    @NotNull
    private String flaws;

    @NotNull
    private int price;

    @NotNull
    private String driveType;

    @NotNull
    @OneToOne
    @JoinColumn(name = "designId", referencedColumnName = "designId")
    private Design design;

    @NotNull
    @OneToOne
    @JoinColumn(name = "engineId", referencedColumnName = "engineId")
    private Engine engine;

    @NotNull
    @OneToOne
    @JoinColumn(name = "transmissionId", referencedColumnName = "transmissionId")
    private Transmission transmission;

    @OneToOne
    @JoinColumn(name = "airConditioningId", referencedColumnName = "airConditioningId")
    private AirConditioning airConditioning;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealershipId")
    private Dealership dealership;

    @NotNull
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    public int getCarId() {
        return carId;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    public void setNumberOfOwners(int numberOfOwners) {
        this.numberOfOwners = numberOfOwners;
    }

    public boolean isWasCrashed() {
        return wasCrashed;
    }

    public void setWasCrashed(boolean wasCrashed) {
        this.wasCrashed = wasCrashed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getFlaws() {
        return flaws;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public AirConditioning getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(AirConditioning airConditioning) {
        this.airConditioning = airConditioning;
    }

    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return brand + " " + model + ", ID: " + carId;
    }
}
