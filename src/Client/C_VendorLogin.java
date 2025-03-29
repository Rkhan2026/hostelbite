package Client;

import Database.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 * C_VendorLogin is the login form for vendors.
 */
public class C_VendorLogin {
    JFrame frame;                 // Main window
    JPanel panel;                // Panel to hold form components
    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    public C_VendorLogin() {
        // === Create the main window ===
        frame = new JFrame("Vendor Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(null);             // Manual positioning

        // === Create the form panel ===
        panel = new JPanel(null);
        panel.setBounds(0, 0, 400, 300);
        panel.setBackground(Color.WHITE); // Clean background

        // === Fonts ===
        Font headingFont = new Font("SansSerif", Font.BOLD, 20);
        Font subHeadingFont = new Font("SansSerif", Font.PLAIN, 14);
        Font labelFont = new Font("SansSerif", Font.BOLD, 16);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);

        // === Heading Label ===
        JLabel heading = new JLabel("Welcome To HostelBite", SwingConstants.CENTER);
        heading.setFont(headingFont);
        heading.setBounds(50, 10, 300, 30);
        panel.add(heading);

        // === Subheading ===
        JLabel subHeading = new JLabel("Enter Vendor details to login", SwingConstants.CENTER);
        subHeading.setFont(subHeadingFont);
        subHeading.setBounds(50, 40, 300, 20);
        panel.add(subHeading);

        // === Username Label and Field ===
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);
        userLabel.setBounds(50, 80, 100, 25);
        panel.add(userLabel);

        userField = new JTextField();
        userField.setFont(fieldFont);
        userField.setBounds(160, 80, 160, 25);
        panel.add(userField);

        // === Password Label and Field ===
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(labelFont);
        passLabel.setBounds(50, 120, 100, 25);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setFont(fieldFont);
        passField.setBounds(160, 120, 160, 25);
        panel.add(passField);

        // === Login Button ===
        loginBtn = new JButton("Login");
        loginBtn.setFont(buttonFont);
        loginBtn.setBounds(130, 180, 120, 35);
        panel.add(loginBtn);

        // === Button Action: Authenticate Vendor ===
        loginBtn.addActionListener(e -> loginVendor());

        // === Add the panel to the frame and show ===
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Authenticates the vendor using database credentials.
     */
    void loginVendor() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT vendor_id FROM vendors WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close login window
                new D_VendorDashboard(); // Proceed to vendor dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Main method to launch the vendor login form.
     */
    public static void main(String[] args) {
        new C_VendorLogin(); // Launch the form
    }
}