package view;

import model.Car;
import model.Client;
import model.Employee;
import model.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationListFrame extends JFrame {
    public ReservationListFrame(Car car, List<Reservation> reservations) {
        DefaultTableModel reservationModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reservationModel.addColumn("Start Date");
        reservationModel.addColumn("End Date");
        reservationModel.addColumn("Client");
        reservationModel.addColumn("Employee");

        for (Reservation reservation : reservations) {
            Object[] row = new Object[reservationModel.getColumnCount()];
            row[0] = reservation.getDateFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            row[1] = reservation.getDateTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            row[2] = reservation.getClient();
            row[3] = reservation.getEmployee();
            reservationModel.addRow(row);
        }

        JLabel carName = prepareLabel("Rezerwacje samochodu " + car);
        JTable reservationTable = prepareTable(reservationModel);

        prepareFrame(reservationTable, carName);
    }

    public ReservationListFrame(Employee employee, List<Reservation> reservations) {
        DefaultTableModel reservationModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reservationModel.addColumn("Start Date");
        reservationModel.addColumn("End Date");
        reservationModel.addColumn("Client");
        reservationModel.addColumn("Car");

        for (Reservation reservation : reservations) {
            Object[] row = new Object[reservationModel.getColumnCount()];
            row[0] = reservation.getDateFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            row[1] = reservation.getDateTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            row[2] = reservation.getClient();
            row[3] = reservation.getCar();
            reservationModel.addRow(row);
        }

        JLabel carName = prepareLabel("Rezerwacje pracownika " + employee);
        JTable reservationTable = prepareTable(reservationModel);

        prepareFrame(reservationTable, carName);
    }

    public ReservationListFrame(Client client, List<Reservation> reservations) {
        DefaultTableModel reservationModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reservationModel.addColumn("Start Date");
        reservationModel.addColumn("End Date");
        reservationModel.addColumn("Car");
        reservationModel.addColumn("Employee");

        for (Reservation reservation : reservations) {
            Object[] row = new Object[reservationModel.getColumnCount()];
            row[0] = reservation.getDateFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            row[1] = reservation.getDateTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            row[2] = reservation.getCar();
            row[3] = reservation.getEmployee();
            reservationModel.addRow(row);
        }

        JLabel carName = prepareLabel("Rezerwacje klienta " + client);
        JTable reservationTable = prepareTable(reservationModel);

        prepareFrame(reservationTable, carName);
    }

    private void prepareFrame(JTable table, JLabel label) {
        JScrollPane reservationScrollPane = new JScrollPane(table);
        reservationScrollPane.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), new LineBorder(Color.BLACK)));

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(reservationScrollPane, BorderLayout.CENTER);

        setTitle("Lista Rezerwacji");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel prepareLabel(String text) {
        JLabel returnLabel = new JLabel(text);
        returnLabel.setHorizontalAlignment(JLabel.CENTER);
        returnLabel.setVerticalAlignment(JLabel.CENTER);
        returnLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        returnLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        returnLabel.setPreferredSize(new Dimension(600, 50));
        return returnLabel;
    }

    private JTable prepareTable(DefaultTableModel model) {
        JTable returnTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                ((JLabel) comp).setHorizontalAlignment(JLabel.CENTER);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = Color.WHITE;
                comp.setBackground(row % 2 == 0 ? alternateColor : whiteColor);
                return comp;
            }
        };
        returnTable.setRowHeight(20);
        returnTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
        returnTable.getTableHeader().setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        returnTable.setFont(new Font("Verdana", Font.PLAIN, 11));
        returnTable.setFocusable(false);
        returnTable.setFillsViewportHeight(true);
        returnTable.setRowSelectionAllowed(false);
        returnTable.setPreferredScrollableViewportSize(returnTable.getPreferredSize());
        return returnTable;
    }
}
