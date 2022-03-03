package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Dealership {

    public Dealership(Employee director, int numberOfCars, String phoneNumber, String city, String street,
                      String houseNumber, String postcode) {
        this.directorId = director;
        this.numberOfCars = numberOfCars;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
    }

    public Dealership() {}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int dealershipId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "directorId", referencedColumnName = "employeeId")
    private Employee directorId;

    @NotNull
    private int numberOfCars;

    @NotNull
    @Column(length = 25)
    private String phoneNumber;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String houseNumber;

    @NotNull
    private String postcode;

    @NotNull
    @OneToMany(mappedBy = "dealership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employment> employees = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "dealership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    public Employee getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Employee directorId) {
        this.directorId = directorId;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<Employment> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employment> employees) {
        this.employees = employees;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
