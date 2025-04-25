package Client;

import Database.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * A_StudentLogin uses a JFrame as the main window
 * and a JPanel inside it to display the student login form.
 */
public class A_StudentLogin {
    JFrame frame;                  // Main application window
    JPanel formPanel;             // Panel to hold login form
    JTextField enrolField;        //textfield to hold enrollemnt number
    JTextField dobField;          //textfield to hold dob field
    JButton loginBtn;             //button to submit and send data

    public A_StudentLogin() {
        // === Create the JFrame ===
        frame = new JFrame("Student Login - HostelBite");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setLayout(null); // Absolute layout for root

        // === Inner "frame-like" panel ===
        formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(0, 0, 400, 300);

        // === Fonts ===
        Font headingFont = new Font("SansSerif", Font.BOLD, 20);
        Font subHeadingFont = new Font("SansSerif", Font.PLAIN, 14);
        Font labelFont = new Font("SansSerif", Font.BOLD, 16);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);

        // === Heading ===
        JLabel heading = new JLabel("Welcome To HostelBite", SwingConstants.CENTER);
        heading.setFont(headingFont);
        heading.setBounds(60, 10, 300, 30);
        formPanel.add(heading);

        // === Subheading ===
        JLabel subHeading = new JLabel("Enter Student details to login", SwingConstants.CENTER);
        subHeading.setFont(subHeadingFont);
        subHeading.setBounds(50, 40, 300, 20);
        formPanel.add(subHeading);

        // === Enrolment No ===
        JLabel enrolLabel = new JLabel("Enrolment No:");
        enrolLabel.setFont(labelFont);
        enrolLabel.setBounds(40, 80, 130, 25);
        formPanel.add(enrolLabel);

        enrolField = new JTextField();
        enrolField.setFont(fieldFont);
        enrolField.setBounds(180, 80, 160, 25);
        formPanel.add(enrolField);

        // === DOB ===
        JLabel dobLabel = new JLabel("DOB (yyyy-mm-dd):");
        dobLabel.setFont(labelFont);
        dobLabel.setBounds(20, 120, 150, 25);
        formPanel.add(dobLabel);

        dobField = new JTextField();
        dobField.setFont(fieldFont);
        dobField.setBounds(180, 120, 160, 25);
        formPanel.add(dobField);

        // === Login Button ===
        loginBtn = new JButton("Login");
        loginBtn.setFont(buttonFont);
        loginBtn.setBounds(130, 180, 120, 35);
        formPanel.add(loginBtn);

        // === Login Action ===
        // Lambda expression, introduced in Java 8. It's a shorter way to define an implementation of the ActionListener interface.
        // loginBtn.addActionListener(e -> verifyStudent());
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    verifyStudent();
                }
            }
        );

        // === Add Form Panel to Frame ===
        frame.add(formPanel);
        frame.setVisible(true);
    }

    /**
     * Verifies student credentials using enrollment number and DOB.
     */
    void verifyStudent() {
        String enrol = enrolField.getText();
        String dob = dobField.getText();//as dobField.getPassword() gives char array 
                                                        //so converted tos string

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE enrolment_no = ? AND dob = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, enrol);
            pst.setString(2, dob);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close the login window
                new B_StudentDashboard(enrol); // Open student dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to launch the login form.
     */
    public static void main(String[] args) {
        new A_StudentLogin(); // Launch the login GUI
    }
}