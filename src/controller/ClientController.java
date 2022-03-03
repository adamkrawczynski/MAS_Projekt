package controller;

import model.Client;
import model.Reservation;
import org.hibernate.Session;
import util.Main;

import javax.persistence.Query;
import java.util.List;

public class ClientController {
    private Session session;

    public List<Client> getAllClients() {
        String select = "select c from Client c";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        List<Client> returnList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    public List<Reservation> findReservations(int id) {
        String select = "select r from Reservation r inner join r.client c where c.clientId = :id";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("id", id);
        List<Reservation> reservations = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return reservations;
    }

    public void createClient(Client client) {
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    public Client searchClient(String email) {
        String select = "select c from Client c where email = :email";
        session = Main.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(select);
        query.setParameter("email", email);
        Client client = (Client) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return client;
    }
}
