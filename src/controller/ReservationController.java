package controller;

import model.*;
import org.hibernate.Session;
import util.Main;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservationController {
    private Session session;

    public List<Reservation> getReservationsByCarType(CarType carType, LocalDate date) {
        String select = "select r from Reservation r where dateFrom > :dayStart and dateTo < :dayEnd and r.car.carType = :carType";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("dayStart", LocalDateTime.of(date, LocalTime.of(0, 0)));
        query.setParameter("dayEnd", LocalDateTime.of(date, LocalTime.of(23,59)));
        query.setParameter("carType", carType);
        List<Reservation> returnList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    public List<Reservation> getReservationByDate(LocalDateTime startDate) {
        String select = "select r from Reservation r where :startDate >= r.dateFrom and :startDate < r.dateTo";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("startDate", startDate);
        List<Reservation> returnList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    public void makeReservation(Car car, Client client, Employee employee, LocalDateTime start, LocalDateTime end) {
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new Reservation(car, client, employee, start, end));
        session.getTransaction().commit();
        session.close();
    }
}