package controller;

import model.Car;
import model.Reservation;
import org.hibernate.Session;
import util.Main;

import javax.persistence.Query;
import java.util.List;

public class CarController {
    private Session session;

    public Car getCar(int id) {
        String select = "select c from Car c inner join c.design left join c.airConditioning inner join " +
                "c.transmission inner join c.engine left join fetch c.photos where c.carId = :id";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("id", id);
        Car returnCar = (Car) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return returnCar;
    }

    public List<Car> getAllCars() {
        String select = "select c from Car c inner join c.design left join c.airConditioning " +
                "inner join c.transmission inner join c.engine";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        List<Car> returnList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    public List<Reservation> findReservations(int id) {
        String select = "select r from Reservation r inner join r.car c where c.carId = :id";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("id", id);
        List<Reservation> reservations = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return reservations;
    }
}
