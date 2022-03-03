package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import view.MainFrame;

import javax.swing.*;
import java.awt.Color;

public class Main {
    private static StandardServiceRegistry registry = null;
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//
//            var employee1 = new Employee(EmployeeType.director, 10000, LocalTime.of(8, 0), LocalTime.of(16, 0), null,
//                    "Adam", "Krawczyński", "99093005183", "666513050", "test@mail.com",
//                    "Warszawa", "Marszałkowska", "28A", "04-312");
//            var employee2 = new Employee(EmployeeType.consultant, 7000, LocalTime.of(8, 0), LocalTime.of(16, 0), CarType.luxury,
//                    "Mikołaj", "Nowakowski", "99093305183", "421513050", "test2@mail.com",
//                    "Warszawa", "Lucerny", "28A", "04-696");
//            var employee3 = new Employee(EmployeeType.consultant, 3300, LocalTime.of(8, 0), LocalTime.of(16, 0), CarType.cheap,
//                    "Mateusz", "Kowal", "99093305183", "521512758", "test3@mail.com",
//                    "Warszawa", "Lucerny", "28A", "04-696");
//            var employee4 = new Employee(EmployeeType.consultant, 8800, LocalTime.of(9, 0), LocalTime.of(17, 0), CarType.luxury,
//                    "Waldemar", "Florkowski", "99093305183", "321515910", "test4@mail.com",
//                    "Warszawa", "Lucerny", "28A", "04-696");
//
//            var dealership = new Dealership(employee1, 100, "228124017", "Warszawa", "Ostrobramska", "70A", "05-293");
//
//            var employment1 = new Employment(null, LocalDate.of(2021, 6, 15), null, employee1, dealership);
//            var employment2 = new Employment(ContractType.oPrace, LocalDate.of(2021, 6, 17), null, employee2, dealership);
//            var employment3 = new Employment(ContractType.oPrace, LocalDate.of(2021, 6, 17), null, employee3, dealership);
//            var employment4 = new Employment(ContractType.oPrace, LocalDate.of(2021, 6, 17), null, employee4, dealership);
//
//            var design1 = new Design(PaintType.solid, formatColor(Color.BLACK), RimType.steel, "195/65R15",
//                    formatColor(Color.GRAY), InteriorMaterial.leather);
//            var design2 = new Design(PaintType.metallic, formatColor(Color.RED), RimType.alloy, "205/55R16",
//                    formatColor(Color.GRAY), InteriorMaterial.polyester);
//            var design3 = new Design(PaintType.pearl, formatColor(Color.GRAY), RimType.steel, "225/45R17",
//                    formatColor(Color.YELLOW), InteriorMaterial.alcantara);
//            var design4 = new Design(PaintType.metallic, formatColor(Color.WHITE), RimType.alloy, "205/55R16",
//                    formatColor(Color.GRAY), InteriorMaterial.nylon);
//            var design5 = new Design(PaintType.solid, formatColor(Color.BLACK), RimType.alloy, "205/55R16",
//                    formatColor(Color.GRAY), InteriorMaterial.nylon);
//
//            var airConditioning1 = new AirConditioning(2, "R134a");
//            var airConditioning2 = new AirConditioning(1, null);
//            var airConditioning3 = new AirConditioning(4, "R1234yf");
//            var airConditioning4 = new AirConditioning(1, "R1234a");
//
//            var engine1 = new Engine(EngineType.Diesel, 200, 3000, 450, false);
//            var engine2 = new Engine(EngineType.Electric, 400, 85, 500, false);
//            var engine3 = new Engine(EngineType.Combustion, 500, 3600, 540, false);
//            var engine4 = new Engine(EngineType.Combustion, 180, 1600, 190, true);
//            var engine5 = new Engine(EngineType.Hybrid, 120, 1200, 115, true);
//
//            var transmission = new Transmission(TransmissionType.Automatic, 7, 1);
//            var transmission2 = new Transmission(TransmissionType.Automatic, 7, 1);
//            var transmission3 = new Transmission(TransmissionType.SemiAutomatic, 8, 2);
//            var transmission4 = new Transmission(TransmissionType.Automatic, 6, 1);
//            var transmission5 = new Transmission(TransmissionType.Manual, 5, 1);
//
//            var car1 = new Car(StatusType.available, CarType.luxury, 1, false,
//                    "Audi", "Q5", 2017, 130000, "none", 90000, "AWD", dealership,
//                    design1, engine1, transmission, airConditioning1);
//            var car2 = new Car(StatusType.available, CarType.superLuxury, 1, false,
//                    "Tesla", "S Plaid", 2021, 2000, "none", 300000, "AWD", dealership,
//                    design2, engine2, transmission2, airConditioning2);
//            var car3 = new Car(StatusType.available, CarType.luxury, 2, false,
//                    "Audi", "R8", 2019, 14000, "rysy na drzwiach", 400000, "RWD", dealership,
//                    design3, engine3, transmission3, airConditioning3);
//            var car4 = new Car(StatusType.available, CarType.popular, 2, false,
//                    "Skoda", "Superb", 2013, 279012, "none", 37000, "4WD", dealership,
//                    design4, engine4, transmission4, airConditioning4);
//            var car5 = new Car(StatusType.available, CarType.cheap, 4, false,
//                    "Skoda", "Fabia", 2011, 401008, "wgnieciona maska", 3900, "FWD", dealership,
//                    design5, engine1, transmission5, null);
//            var car6 = new Car(StatusType.available, CarType.luxury, 2, false,
//                    "Volvo", "S60", 2017, 35021, "none", 163000, "4WD", dealership,
//                    design5, engine4, transmission5, airConditioning1);
//
//            var photo1 = new Photo(car1, "images/audi_q5_1.png");
//            var photo2 = new Photo(car1, "images/audi_q5_2.png");
//            var photo3 = new Photo(car1, "images/audi_q5_3.png");
//            var photo4 = new Photo(car1, "images/audi_q5_4.png");
//            var photo5 = new Photo(car1, "images/audi_q5_5.png");
//
//            var client1 = new Client("Grzegorz", "Bączek", "777213031", "mail.test@sx.com", "99093005396");
//            var client2 = new Client("Tadeusz", "Tarasewicz", "777213031", "mail.test@sx.com", "99093005396");
//            var client3 = new Client("Adam", "Nowak", "777213031", "mail.test@sx.com", "99093005396");
//            var client4 = new Client("Florian", "Żółtek", "777213031", "mail.test@sx.com", "99093005396");
//            var client5 = new Client("Super Firma S.A", "1923320219", "123123423", "testss.as@dsa.pl");
//
//            var reservation1 = new Reservation(car1, client1, employee2,
//                    LocalDateTime.of(2022, 6, 19, 10, 0),
//                    LocalDateTime.of(2022, 6, 19, 10, 15));
//            var reservation2 = new Reservation(car1, client2, employee2,
//                    LocalDateTime.of(2022, 6, 19, 11, 0),
//                    LocalDateTime.of(2022, 6, 19, 12, 0));
//            var reservation3 = new Reservation(car1, client3, employee4,
//                    LocalDateTime.of(2022, 6, 19, 11, 30),
//                    LocalDateTime.of(2022, 6, 19, 11, 45));
//            var reservation4 = new Reservation(car3, client1, employee2,
//                    LocalDateTime.of(2022, 6, 21, 10, 0),
//                    LocalDateTime.of(2022, 6, 21, 10, 15));
//            var reservation5 = new Reservation(car6, client1, employee2,
//                    LocalDateTime.of(2022, 6, 21, 10, 15),
//                    LocalDateTime.of(2022, 6, 21, 11, 15));
//            var reservation6 = new Reservation(car5, client5, employee3,
//                    LocalDateTime.of(2022, 6, 19, 13, 0),
//                    LocalDateTime.of(2022, 6, 19, 13, 15));
//            var reservation7 = new Reservation(car3, client4, employee4,
//                    LocalDateTime.of(2022, 6, 19, 11, 15),
//                    LocalDateTime.of(2022, 6, 19, 11, 30));
//            var reservation8 = new Reservation(car3, client5, employee4,
//                    LocalDateTime.of(2022, 6, 19, 12, 45),
//                    LocalDateTime.of(2022, 6, 19, 13, 0));
//            var reservation9 = new Reservation(car3, client3, employee4,
//                    LocalDateTime.of(2022, 6, 19, 12, 0),
//                    LocalDateTime.of(2022, 6, 19, 12, 15));
//            var reservation10 = new Reservation(car3, client2, employee4,
//                    LocalDateTime.of(2022, 6, 19, 13, 15),
//                    LocalDateTime.of(2022, 6, 19, 13, 30));
//
//            session.save(design1);
//            session.save(design2);
//            session.save(design3);
//            session.save(design4);
//            session.save(design5);
//
//            session.save(airConditioning1);
//            session.save(airConditioning2);
//            session.save(airConditioning3);
//            session.save(airConditioning4);
//
//            session.save(engine1);
//            session.save(engine2);
//            session.save(engine3);
//            session.save(engine4);
//            session.save(engine5);
//
//            session.save(transmission);
//            session.save(transmission2);
//            session.save(transmission3);
//            session.save(transmission4);
//            session.save(transmission5);
//
//            session.save(car1);
//            session.save(car2);
//            session.save(car3);
//            session.save(car4);
//            session.save(car5);
//            session.save(car6);
//
//            session.save(photo1);
//            session.save(photo2);
//            session.save(photo3);
//            session.save(photo4);
//            session.save(photo5);
//
//            session.save(employee1);
//            session.save(employee2);
//            session.save(employee3);
//            session.save(employee4);
//
//            session.save(dealership);
//
//            session.save(employment1);
//            session.save(employment2);
//            session.save(employment3);
//            session.save(employment4);
//
//            session.save(client1);
//            session.save(client2);
//            session.save(client3);
//            session.save(client4);
//            session.save(client5);
//
//            session.getTransaction().commit();
//            session.close();
            SwingUtilities.invokeLater(MainFrame::new);
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static String formatColor(Color color) {
        return String.format("#%06x", color.getRGB() & 0xFFFFFF);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}