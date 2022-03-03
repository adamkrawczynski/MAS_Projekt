package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.*;

@Entity
public class Employee {

    public Employee(EmployeeType employeeType, int salary, LocalTime workFrom, LocalTime workTo, CarType specialization,
                    String firstName, String lastName, String pesel, String phoneNumber,
                    String email, String city, String street, String houseNumber, String postcode) {
        this.employeeType = employeeType;
        this.salary = salary;
        this.workFrom = workFrom;
        this.workTo = workTo;
        this.specialization = specialization;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
    }

    public Employee() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int employeeId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @NotNull
    private int salary;

    @NotNull
    private LocalTime workFrom;

    @NotNull
    private LocalTime workTo;

    @Enumerated(EnumType.STRING)
    private CarType specialization;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(length = 11)
    private String pesel;

    @NotNull
    @Column(length = 25)
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String houseNumber;

    @NotNull
    @Column(length = 10)
    private String postcode;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employment> employees = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeLanguage> languages = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public int getEmployeeId() {
        return employeeId;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalTime getWorkFrom() {
        return workFrom;
    }

    public void setWorkFrom(LocalTime workFrom) {
        this.workFrom = workFrom;
    }

    public LocalTime getWorkTo() {
        return workTo;
    }

    public void setWorkTo(LocalTime workTo) {
        this.workTo = workTo;
    }

    public CarType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(CarType specialization) {
        this.specialization = specialization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<EmployeeLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<EmployeeLanguage> languages) {
        this.languages = languages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        return this.employeeId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) obj;
        return other.employeeId == this.employeeId;
    }
}
