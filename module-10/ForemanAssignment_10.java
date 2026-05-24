/*
Name: Natasha Foreman
Course: CSD 420 - Advanced Java
Date: May 24th, 2026
Assignment: Module 10
Purpose: View and update fan information from the databasedb database.
*/

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ForemanAssignment_10 extends JFrame {

    private JTextField idField = new JTextField(10);
    private JTextField firstNameField = new JTextField(15);
    private JTextField lastNameField = new JTextField(15);
    private JTextField favoriteTeamField = new JTextField(15);
    private JLabel statusLabel = new JLabel("Enter an ID and click Display.");

    private final String URL = "jdbc:mysql://localhost:3306/databasedb";
    private final String USER = "student1";
    private final String PASSWORD = "pass";

    public ForemanAssignment_10() {
        setTitle("Fan Information");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");

        add(new JLabel("ID:"));
        add(idField);

        add(new JLabel("First Name:"));
        add(firstNameField);

        add(new JLabel("Last Name:"));
        add(lastNameField);

        add(new JLabel("Favorite Team:"));
        add(favoriteTeamField);

        add(displayButton);
        add(updateButton);

        add(statusLabel);

        displayButton.addActionListener(e -> displayRecord());
        updateButton.addActionListener(e -> updateRecord());

        testConnection();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void displayRecord() {
        String sql = "SELECT * FROM fans WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int id = Integer.parseInt(idField.getText());
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("firstname"));
                lastNameField.setText(resultSet.getString("lastname"));
                favoriteTeamField.setText(resultSet.getString("favoriteteam"));
                statusLabel.setText("Record displayed.");
            } else {
                statusLabel.setText("No record found for that ID.");
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("ID must be a number.");
        } catch (SQLException e) {
            statusLabel.setText("Database error: " + e.getMessage());
        }
    }

    public void updateRecord() {
        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int id = Integer.parseInt(idField.getText());

            statement.setString(1, firstNameField.getText());
            statement.setString(2, lastNameField.getText());
            statement.setString(3, favoriteTeamField.getText());
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                statusLabel.setText("Record updated successfully.");
            } else {
                statusLabel.setText("No record found to update.");
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("ID must be a number.");
        } catch (SQLException e) {
            statusLabel.setText("Database error: " + e.getMessage());
        }
    }

    public void testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Test Passed: Database connection successful.");
        } catch (SQLException e) {
            System.out.println("Test Failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ForemanAssignment_10 app = new ForemanAssignment_10();
            app.setVisible(true);
        });
    }
}