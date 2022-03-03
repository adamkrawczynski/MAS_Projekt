package view;

import controller.EmployeeController;
import controller.ReservationController;
import model.*;
import util.NoEmployeeException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationBoxPanel extends JPanel {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    private static final EmployeeController employeeController = new EmployeeController();
    private static final ReservationController reservationController = new ReservationController();

    public ReservationBoxPanel(Car car, Client client, LocalDate date, boolean testDrive, JFrame[] parents) throws NoEmployeeException {
        GridLayout grid = new GridLayout();
        grid.setColumns(10);
        grid.setHgap(2);
        grid.setVgap(3);
        Map<LocalTime, Integer> freeTimes = getFreeTimes(car, date, testDrive);
        int mapSize = freeTimes.size();
        if (mapSize == 0) {
            parents[0].dispose();
            throw new NoEmployeeException("There isn't any employee available");
        }
        if (mapSize <= 30) {
            grid.setRows(3);
        } else if (mapSize <= 40) {
            grid.setRows(4);
        } else if (mapSize <= 50) {
            grid.setRows(5);
        }
        setLayout(grid);
        setBackground(new Color(0xD2D2D3));
        freeTimes.entrySet().stream().filter(e -> e.getValue() == 0).map(Map.Entry::getKey).collect(Collectors.toList()).forEach(freeTimes.keySet()::remove);
        freeTimes.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(t -> {
            JButton button = new JButton(t.getKey().format(dtf));
            button.setFocusable(false);
            button.setFont(new Font("Open Sans", Font.BOLD, 12));
            button.setMargin(new Insets(0, 0, 0, 0));
            add(button);
            button.addActionListener(e -> {
                LocalDateTime start = LocalDateTime.of(date, t.getKey());
                LocalDateTime end = testDrive ? LocalDateTime.of(date, t.getKey()).plusHours(1) : LocalDateTime.of(date, t.getKey()).plusMinutes(15);
                Employee employee = employeeController.drawEmployee(car, start);
                if (employee == null) {
                    JOptionPane.showMessageDialog(this, "There isn't any employee available", "Internal error", JOptionPane.ERROR_MESSAGE);
                } else {
                    reservationController.makeReservation(car, client, employee, start, end);
                }
                parents[0].dispose();
                parents[1].dispose();
            });
        });
    }

    private static Map<LocalTime, Integer> getFreeTimes(Car car, LocalDate date, boolean testDrive) {
        Map<LocalTime, Integer> freeTimes = new HashMap<>();
        Map<LocalTime, Integer> busyTimes = new HashMap<>();
        List<Employee> employees = employeeController.getEmployeesByCarType(EmployeeType.consultant, car);
        List<Reservation> reservations = reservationController.getReservationsByCarType(car.getCarType(), date);
        reservations.sort(Comparator.comparing(Reservation::getDateFrom));

        for (Reservation reservation : reservations) {
            LocalTime start = reservation.getDateFrom().toLocalTime();
            while (start.isBefore(reservation.getDateTo().toLocalTime())) {
                busyTimes.merge(start, 1, Integer::sum);
                start = start.plusMinutes(15);
            }
            if (testDrive) {
                LocalTime startCopy = reservation.getDateFrom().toLocalTime();
                if (reservations.stream().noneMatch(r -> r.getDateTo().toLocalTime().equals(startCopy) && r.getEmployee().equals(reservation.getEmployee()))) {
                    if (reservations.stream().anyMatch(r -> r.getDateTo().toLocalTime().equals(startCopy.minusMinutes(15)) && r.getEmployee().equals(reservation.getEmployee()))) {
                        busyTimes.merge(startCopy.minusMinutes(15), 1, Integer::sum);
                    } else if (reservations.stream().anyMatch(r -> r.getDateTo().toLocalTime().equals(startCopy.minusMinutes(30)) && r.getEmployee().equals(reservation.getEmployee()))) {
                        busyTimes.merge(startCopy.minusMinutes(15), 1, Integer::sum);
                        busyTimes.merge(startCopy.minusMinutes(30), 1, Integer::sum);
                    } else if (reservations.stream().anyMatch(r -> r.getDateTo().toLocalTime().equals(startCopy.minusMinutes(45)) && r.getEmployee().equals(reservation.getEmployee()))) {
                        busyTimes.merge(startCopy.minusMinutes(15), 1, Integer::sum);
                        busyTimes.merge(startCopy.minusMinutes(30), 1, Integer::sum);
                        busyTimes.merge(startCopy.minusMinutes(45), 1, Integer::sum);
                    }
                }
            }
        }

        for (Employee employee : employees) {
            LocalTime start = employee.getWorkFrom();
            LocalTime end = employee.getWorkTo();

            if (testDrive) {
                while (start.isBefore(end.minusMinutes(59))) {
                    freeTimes.merge(start, 1, Integer::sum);
                    start = start.plusMinutes(15);
                }
            } else {
                while (start.isBefore(end)) {
                    freeTimes.merge(start, 1, Integer::sum);
                    start = start.plusMinutes(15);
                }
            }
        }

        busyTimes.forEach((key, value) -> {
            if (freeTimes.containsKey(key)) {
                while (busyTimes.get(key) > 0) {
                    freeTimes.merge(key, -1, Integer::sum);
                    busyTimes.merge(key, -1, Integer::sum);
                }
            }
        });
        freeTimes.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
        return freeTimes;
    }
}
