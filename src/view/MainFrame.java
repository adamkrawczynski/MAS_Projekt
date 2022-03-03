package view;

import controller.CarController;
import controller.ClientController;
import controller.EmployeeController;
import model.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private static final CarController carController = new CarController();
    private static final EmployeeController employeeController = new EmployeeController();
    private static final ClientController clientController = new ClientController();

    public MainFrame() {
        setLayout(new BorderLayout());

        DefaultTableModel carModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        carModel.addColumn("Id");
        carModel.addColumn("Brand");
        carModel.addColumn("Model");
        carModel.addColumn("Year");
        carModel.addColumn("Mileage");
        carModel.addColumn("Capacity");
        carModel.addColumn("Engine Type");

        DefaultTableModel employeeModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        employeeModel.addColumn("Id");
        employeeModel.addColumn("First Name");
        employeeModel.addColumn("Last Name");
        employeeModel.addColumn("Specialization");
        employeeModel.addColumn("Title");

        DefaultTableModel clientModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clientModel.addColumn("Id");
        clientModel.addColumn("First Name");
        clientModel.addColumn("Last Name");
        clientModel.addColumn("NIP");

        JTable carTable = prepareTable(carModel);
        JTable employeeTable = prepareTable(employeeModel);
        JTable clientTable = prepareTable(clientModel);

        List<Car> cars = carController.getAllCars();
        for (Car car : cars) {
            Object[] row = new Object[carModel.getColumnCount()];
            row[0] = car.getCarId();
            row[1] = car.getBrand();
            row[2] = car.getModel();
            row[3] = String.valueOf(car.getModelYear());
            row[4] = car.getMileage() + " km";
            if (car.getEngine().getEngineType() == EngineType.Electric) {
                row[5] = car.getEngine().getCapacity() + " kWh";
            } else {
                row[5] = car.getEngine().getCapacity() + "cm³";
            }
            row[6] = String.valueOf(car.getEngine().getEngineType());
            carModel.addRow(row);
        }

        carTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                int row = table.rowAtPoint(e.getPoint());
                if (e.getButton() == MouseEvent.BUTTON1 && row != -1) {
                    int id = (int) carTable.getModel().getValueAt(carTable.getSelectedRow(), 0);
                    List<Reservation> reservations = carController.findReservations(id);
                    if (reservations.isEmpty()) {
                        JOptionPane.showMessageDialog(getContentPane(), "This car hasn't been reserved yet");
                    } else {
                        new ReservationListFrame(reservations.get(0).getCar(), reservations);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3 && row != -1) {
                    int id = (int) carTable.getModel().getValueAt(row, 0);
                    new CarDetailsFrame(id);
                }
            }
        });

        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                int row = table.rowAtPoint(e.getPoint());
                if (e.getButton() == MouseEvent.BUTTON1 && row != -1) {
                    int id = (int) employeeTable.getModel().getValueAt(employeeTable.getSelectedRow(), 0);
                    List<Reservation> reservations = employeeController.findReservations(id);
                    if (reservations.isEmpty()) {
                        JOptionPane.showMessageDialog(getContentPane(), "This employee hasn't been assigned to any reservation yet");
                    } else {
                        new ReservationListFrame(reservations.get(0).getEmployee(), reservations);
                    }
                }
            }
        });

        clientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                int row = table.rowAtPoint(e.getPoint());
                if (e.getButton() == MouseEvent.BUTTON1 && row != -1) {
                    int id = (int) clientTable.getModel().getValueAt(clientTable.getSelectedRow(), 0);
                    List<Reservation> reservations = clientController.findReservations(id);
                    if (reservations.isEmpty()) {
                        JOptionPane.showMessageDialog(getContentPane(), "This client hasn't made any reservations");
                    } else {
                        new ReservationListFrame(reservations.get(0).getClient(), reservations);
                    }
                }
            }
        });

        JScrollPane carScrollPane = new JScrollPane(carTable);
        JScrollPane employeePane = new JScrollPane(employeeTable);
        JScrollPane clientPane = new JScrollPane(clientTable);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Cars", carScrollPane);
        tabbedPane.addTab("Employees", employeePane);
        tabbedPane.addTab("Clients", clientPane);
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) {
                List<Car> carList = carController.getAllCars();
                carModel.setRowCount(0);
                for (Car car : carList) {
                    Object[] row = new Object[carModel.getColumnCount()];
                    row[0] = car.getCarId();
                    row[1] = car.getBrand();
                    row[2] = car.getModel();
                    row[3] = String.valueOf(car.getModelYear());
                    row[4] = car.getMileage() + "km";
                    if (car.getEngine().getEngineType() == EngineType.Electric) {
                        row[5] = car.getEngine().getCapacity() + "kWh";
                    } else {
                        row[5] = car.getEngine().getCapacity() + "cm³";
                    }
                    row[6] = String.valueOf(car.getEngine().getEngineType());
                    carModel.addRow(row);
                }
            } else if (tabbedPane.getSelectedIndex() == 1) {
                List<Employee> employees = employeeController.getAllEmployees();
                employeeModel.setRowCount(0);
                for (Employee employee : employees) {
                    Object[] row = new Object[employeeModel.getColumnCount()];
                    row[0] = employee.getEmployeeId();
                    row[1] = employee.getFirstName();
                    row[2] = employee.getLastName();
                    if (employee.getSpecialization() != null) {
                        row[3] = employee.getSpecialization();
                    } else {
                        row[3] = "-";
                    }
                    row[4] = employee.getEmployeeType();
                    employeeModel.addRow(row);
                }
            } else if (tabbedPane.getSelectedIndex() == 2) {
                List<Client> clients = clientController.getAllClients();
                clientModel.setRowCount(0);
                for (Client client : clients) {
                    Object[] row = new Object[clientModel.getColumnCount()];
                    row[0] = client.getClientId();
                    row[1] = client.getFirstName();
                    if (client.getLastName() != null) {
                        row[2] = client.getLastName();
                    } else {
                        row[2] = "-";
                    }
                    if (client.getNip() != null) {
                        row[3] = client.getNip();
                    } else {
                        row[3] = "-";
                    }
                    clientModel.addRow(row);
                }
            }
        });
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Verdana", Font.BOLD, 16));
        tabbedPane.setBackground(new Color(0x696969));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFocusable(false);

        carScrollPane.setPreferredSize(new Dimension(800, 600));

        add(tabbedPane, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Car Dealership System");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
        returnTable.removeColumn(returnTable.getColumnModel().getColumn(0));
        returnTable.setRowHeight(25);
        returnTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
        returnTable.getTableHeader().setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        returnTable.setFont(new Font("Verdana", Font.PLAIN, 15));
        returnTable.setFocusable(false);
        returnTable.setFillsViewportHeight(true);
        returnTable.setRowSelectionAllowed(false);
        returnTable.setPreferredScrollableViewportSize(returnTable.getPreferredSize());
        return returnTable;
    }
}


