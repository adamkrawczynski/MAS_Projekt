package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class EmployeeLanguage {

    public EmployeeLanguage(Employee employee, Language language) {
        this.employee = employee;
        this.language = language;
    }

    public EmployeeLanguage() {}

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int employeeLanguageId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Language language;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
