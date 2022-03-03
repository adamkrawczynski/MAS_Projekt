package view;

import controller.ClientController;
import model.Car;
import model.Client;
import util.NoEmployeeException;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationPopUp extends JFrame {
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField emailText;
    private JTextField phoneText;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberLabel;
    private JCheckBox testDriveCheckBox;
    private JCheckBox statuteCheckBox;
    private JSeparator upperSeparator;
    private JSeparator lowerSeparator;
    private JLabel reserveLabel;
    private JPanel titleLabelPanel;
    private JPanel contentPanel;
    private JButton reserveButton;
    private JPanel mainPanel;
    private JButton cancelButton;

    private static final ClientController clientController = new ClientController();

    public ReservationPopUp(Car car, JFrame parent) {
        reserveLabel.setText("Reservation of " + car);
        mainPanel.setBackground(new Color(212, 212, 212));
        setContentPane(mainPanel);
        setTitle("Enter personal data");
        setSize(300, 320);
        setResizable(false);
        setLocationRelativeTo(parent);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        cancelButton.addActionListener(e -> dispose());
        reserveButton.addActionListener(e -> {
            List<String> errors = validateForm();
            if (errors.isEmpty()) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String email = emailText.getText();
                String phoneNumber = phoneText.getText();
                boolean testDrive = testDriveCheckBox.isSelected();
                Client client = null;
                try {
                    client = clientController.searchClient(email);
                } catch (NoResultException ex) {
                    dispose();
                }
                if (client == null) {
                    client = new Client(firstName, lastName, phoneNumber, email, false);
                    clientController.createClient(client);
                }
                try {
                    new ReservationFrame(client, car, testDrive, parent);
                } catch (NoEmployeeException ex) {
                    dispose();
                    JOptionPane.showMessageDialog(this, "There isn't any employee available", "Internal error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    dispose();
                }
            } else {
                StringBuilder message = new StringBuilder();
                for (String error : errors) {
                    message.append(error).append("\n");
                }
                JOptionPane.showMessageDialog(this, message.toString(), "Validation error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private List<String> validateForm() {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String email = emailText.getText();
        String phoneNumber = phoneText.getText();
        boolean statue = statuteCheckBox.isSelected();

        Pattern numPattern = Pattern.compile("\\d");
        Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
        Pattern phonePattern = Pattern.compile("[\\d-+() ]{0,15}");
        Matcher firstNameMatcher = numPattern.matcher(firstName);
        Matcher lastNameMatcher = numPattern.matcher(lastName);
        Matcher emailMatcher = emailPattern.matcher(email);
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);

        List<String> errors = new ArrayList<>();

        if (firstNameMatcher.find()) {
            errors.add("First name cannot contain digits");
        } else if (firstName.isEmpty()) {
            errors.add("Please enter your first name");
        }
        if (lastNameMatcher.find()) {
            errors.add("Last name cannot contain digits");
        } else if (lastName.isEmpty()) {
            errors.add("Please enter your last name");
        }
        if (email.isEmpty()) {
            errors.add("Please enter your e-mail");
        } else if (!emailMatcher.find()) {
            errors.add("Wrong e-mail address");
        }
        if (!phoneMatcher.find()) {
            errors.add("Wrong phone number");
        } else if (phoneNumber.isEmpty()) {
            errors.add("Please enter your phone number");
        }
        if (!statue) {
            errors.add("You have to accept the terms of service");
        }

        return errors;
    }
}
