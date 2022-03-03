package view;

import model.*;
import util.NoEmployeeException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationFrame extends JFrame {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");

    public ReservationFrame(Client client, Car car, boolean testDrive, JFrame parent) throws NoEmployeeException {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(212, 212, 212));
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JFrame[] parents = new JFrame[2];
        parents[0] = parent;
        parents[1] = this;
        for (int i = 0; i < 14; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            String dateToFormat = date.format(dtf);
            String dateString = dateToFormat.substring(0, 1).toUpperCase() + dateToFormat.substring(1);
            JLabel dateLabel = new JLabel(dateString);
            dateLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
            dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(dateLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            ReservationBoxPanel reservationPanel = new ReservationBoxPanel(car, client, date, testDrive, parents);
            reservationPanel.setMaximumSize(new Dimension(600, 100));
            reservationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(reservationPanel);
            panel.add(Box.createRigidArea(new Dimension(100, 20)));
        }

        panel.add(Box.createVerticalGlue());
        getContentPane().add(scrollPane);

        setTitle("Choose a date");
        setSize(800, 800);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}