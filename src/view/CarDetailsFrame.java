package view;

import controller.CarController;
import model.Car;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CarDetailsFrame extends JFrame {

    public CarDetailsFrame(int id) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        CarController carController = new CarController();
        Car car = carController.getCar(id);

        JLabel descriptionLabel = new JLabel("Details of " + car.getBrand() + " " + car.getModel());
        descriptionLabel.setBorder(new CompoundBorder(new LineBorder(new Color(60, 63, 65)), new EmptyBorder(5, 10, 5, 10)));
        descriptionLabel.setOpaque(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTable detailsTable = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                JComponent component = (JComponent) super.prepareRenderer(renderer, row, col);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = new Color(0xECECED);
                if (col % 2 == 0) {
                    component.setBackground(alternateColor);
                    component.setFont(new Font("Verdana", Font.BOLD, 13));
                } else if (col % 2 == 1) {
                    component.setBackground(whiteColor);
                    component.setFont(new Font("Verdana", Font.PLAIN, 13));
                }
                if (col == 1) {
                    component.setBorder(new MatteBorder(0, 0, 0, 1, new Color(60, 63, 65)));
                }
                return component;
            }
        };

        detailsTable.setShowGrid(false);
        detailsTable.setIntercellSpacing(new Dimension(0, 0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(true);

        JLabel location = new JLabel("Location: " + car.getDealership().getStreet() + " " + car.getDealership().getHouseNumber());
        JLabel priceTag = new JLabel("Price: ");
        JLabel price = new JLabel(car.getPrice() + " PLN");

        location.setBorder(new EmptyBorder(0, 5, 0, 0));
        price.setBorder(new EmptyBorder(0, 0, 0, 5));

        bottomPanel.add(location);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(priceTag);
        bottomPanel.add(price);

        bottomPanel.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, new Color(60, 63, 65)), new EmptyBorder(10, 0, 10, 0)));

        mainPanel.add(detailsTable);
        mainPanel.add(bottomPanel);
        mainPanel.setBackground(new Color(0xD2D2D3));
        mainPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 20, 0, 20), new MatteBorder(0, 1, 0, 1, new Color(60, 63, 65))));

        descriptionLabel.setMaximumSize(new Dimension(300, 30));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setOpaque(true);
        buttonsPanel.setBackground(new Color(0xD2D2D3));

        JButton reserveButton = new JButton("RESERVE");
        reserveButton.setMargin(new Insets(2, 20, 2, 20));
        reserveButton.setFocusable(false);
        reserveButton.addActionListener(e -> new ReservationPopUp(car, this));

        JButton closeButton = new JButton("CLOSE");
        closeButton.setMargin(new Insets(2, 25, 2, 25));
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> dispose());

        buttonsPanel.add(closeButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        buttonsPanel.add(reserveButton);
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        String brand = car.getBrand();
        String model = car.getModel();
        String year = String.valueOf(car.getModelYear());
        String mileage = car.getMileage() + " km";
        String capacity = car.getEngine().getCapacity() + "cm³";
        String fuel = car.getEngine().getEngineType().toString();
        String power = car.getEngine().getPower() + " HP";
        String transmissionType = car.getTransmission().getTransmissionType().toString();
        String drive = car.getDriveType();
        String crashed = "No";
        if (car.isWasCrashed()) {
            crashed = "Yes";
        }
        String airConditioning = "No";
        if (car.getAirConditioning() != null) {
            airConditioning = "Yes";
        }
        String paintColor = car.getDesign().getPaintColor();
        String interiorColor = car.getDesign().getInteriorColor();
        String material = car.getDesign().getInteriorMaterial().toString();
        String owners = String.valueOf(car.getNumberOfOwners());
        String gears = String.valueOf(car.getTransmission().getNumberOfGears());
        String rimType = car.getDesign().getRimType().toString();
        String rimSize = car.getDesign().getRimSize();

        detailsTable.setModel(new DefaultTableModel(
                new String[][]{
                        {"Brand", brand, "Drive", drive},
                        {"Model", model, "Transmission", transmissionType},
                        {"Model year", year, "No. of gears", gears},
                        {"Mileage", mileage, "Paint color", paintColor},
                        {"Capacity", capacity, "Color inside", interiorColor},
                        {"Fuel type", fuel, "Material inside", material},
                        {"Power", power, "Rim type", rimType},
                        {"Air Cond.", airConditioning, "Rim size", rimSize},
                        {"Crashed", crashed, "No. of owners", owners}
                },
                new String[]{
                        null, null, null, null
                }
        ));

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(descriptionLabel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(mainPanel);
        add(buttonsPanel);

        descriptionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        detailsTable.setOpaque(true);
        detailsTable.setFocusable(false);
        detailsTable.setRowSelectionAllowed(false);

        detailsTable.getColumnModel().getColumn(0).setPreferredWidth(110);
        detailsTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        detailsTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        detailsTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        detailsTable.setRowHeight(20);
        detailsTable.setBorder(new MatteBorder(1, 0, 1, 0, new Color(60, 63, 65)));

        getContentPane().setBackground(new Color(0xD2D2D3));
        setTitle(car.toString());
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
