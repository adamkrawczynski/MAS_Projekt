package controller;

import model.Car;
import model.Employee;
import model.EmployeeType;
import model.Reservation;
import org.hibernate.Session;
import util.Main;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class EmployeeController {
    private Session session;

    public List<Employee> getAllEmployees() {
        String select = "select e from Employee e";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        List<Employee> returnList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    public List<Employee> getEmployeesByCarType(EmployeeType employeeType, Car car) {
        String select = "select e from Employee e where e.employeeType = :employeeType and e.specialization = :specialization";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("employeeType", employeeType);
        query.setParameter("specialization", car.getCarType());
        List<Employee> returnList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    public List<Reservation> findReservations(int id) {
        String select = "select r from Reservation r inner join r.employee e where e.employeeId = :id";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("id", id);
        List<Reservation> reservations = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return reservations;
    }

    public Employee drawEmployee(Car car, LocalDateTime date) {
        ReservationController reservationController = new ReservationController();
        EmployeeController employeeController = new EmployeeController();
        List<Employee> availableEmployees = employeeController.getEmployeesByCarType(EmployeeType.consultant, car);
        List<Reservation> reservations = reservationController.getReservationByDate(date);
        reservations.forEach(r -> availableEmployees.removeIf(e -> e.equals(r.getEmployee())));
        if (availableEmployees.isEmpty()) {
            return null;
        } else {
            return availableEmployees.get(new Random().nextInt(availableEmployees.size()));
        }
    }
}
