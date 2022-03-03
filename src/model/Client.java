package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    public Client(boolean isCompany, String nip, String firstName, String lastName, String pesel, String phoneNumber,
                  String email, String city, String street, int houseNumber, String postcode) {
        this.isCompany = isCompany;
        this.nip = nip;
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

    public Client(String firstName, String lastName, String phoneNumber, String email, boolean isCompany) {
        if (isCompany) {
            this.firstName = firstName;
            this.nip = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }
    }

    public Client() {}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int clientId;

    private boolean isCompany;

    @Column(length = 10)
    private String nip;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(length = 11)
    private String pesel;

    @NotNull
    @Column(length = 25)
    private String phoneNumber;

    @NotNull
    private String email;

    private String city;

    private String street;

    private int houseNumber;

    @Column(length = 10)
    private String postcode;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactionSet = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservationSet = new HashSet<>();

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    @Override
    public String toString() {
        if (lastName != null) {
            return firstName + " " + lastName;
        } else {
            return firstName;
        }
    }
}