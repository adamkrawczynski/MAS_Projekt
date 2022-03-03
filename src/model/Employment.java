package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employment {

    public Employment(ContractType contractType, LocalDate contractFrom, LocalDate contractTo, Employee employee, Dealership dealership) {
        this.contractType = contractType;
        this.contractFrom = contractFrom;
        this.contractTo = contractTo;
        this.employee = employee;
        this.dealership = dealership;
    }

    public Employment() {}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int employmentId;

    @NotNull
    private ContractType contractType;

    @NotNull
    private LocalDate contractFrom;

    private LocalDate contractTo;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Dealership dealership;

    public int getEmploymentId() {
        return employmentId;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public LocalDate getContractFrom() {
        return contractFrom;
    }

    public void setContractFrom(LocalDate contractFrom) {
        this.contractFrom = contractFrom;
    }

    public LocalDate getContractTo() {
        return contractTo;
    }

    public void setContractTo(LocalDate contractTo) {
        this.contractTo = contractTo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }
}
