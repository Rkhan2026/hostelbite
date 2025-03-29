package Client;

import Database.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 * B_StudentDashboard creates a student dashboard screen
 * allowing students to select and confirm their meal.
 */
public class B_StudentDashboard {
    JFrame frame;                  // Main application window
    JPanel panel;                  // Panel holding all UI elements
    JComboBox<String> mealBox;     // Dropdown to select meal
    JButton submitBtn;             // Button to confirm meal
    String enrolmentNo;            // Student's enrolment number

    /**
     * Constructor to build and show the dashboard UI.
     * @param enrolmentNo the student's enrolment number (used for meal tracking)
     */
    public B_StudentDashboard(String enrolmentNo) {
        this.enrolmentNo = enrolmentNo;

        // === Create the main JFrame window ===
        frame = new JFrame("Select Meal");
        frame.setSize(600, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the app when closed
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setLayout(null);             // Absolute layout for root

        // === Create a panel to hold the form ===
        panel = new JPanel(null);                 // Absolute layout for precise positioning
        panel.setBounds(0, 0, 800, 620);           // Match frame size
        panel.setBackground(Color.WHITE);         // Background color

        // === Fonts for labels and buttons ===
        Font labelFont = new Font("SansSerif", Font.BOLD, 20);
        Font comboFont = new Font("SansSerif", Font.PLAIN, 20);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);

        // === Label: "Select Meal" ===
        JLabel label = new JLabel("Select Meal:");
        label.setFont(labelFont);
        label.setBounds(50, 50, 120, 25);
        panel.add(label);

        // === ComboBox: meal selection dropdown ===
        mealBox = new JComboBox<>();
        mealBox.setFont(comboFont);
        mealBox.setBounds(180, 50, 250, 25);
        panel.add(mealBox);

        // === Button: Confirm meal selection ===
        submitBtn = new JButton("Confirm");
        submitBtn.setFont(buttonFont);
        submitBtn.setBounds(130, 110, 120, 35);
        panel.add(submitBtn);

        // === Load meals from the database ===
        loadMeals();

        // === Action: When button is clicked, record the meal ===
        submitBtn.addActionListener(e -> recordMeal());

        // === Add the panel to the frame and show ===
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Loads available meals from the database into the combo box.
     */
    void loadMeals() {
        try (Connection con = DBConnection.getConnection()) {
            // SQL to get meal names
            String sql = "SELECT meal_name FROM meals";
            ResultSet rs = con.createStatement().executeQuery(sql);

            // Add each meal to the dropdown
            while (rs.next()) {
                mealBox.addItem(rs.getString("meal_name"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading meals: " + e.getMessage());
        }
    }

    /**
     * Records the selected meal in the database under the student's enrolment.
     */
    void recordMeal() {
        String mealName = (String) mealBox.getSelectedItem(); // Get selected meal

        try (Connection con = DBConnection.getConnection()) {
            // Get meal_id from meals table
            String getMealId = "SELECT meal_id FROM meals WHERE meal_name = ?";
            PreparedStatement pst = con.prepareStatement(getMealId);
            pst.setString(1, mealName);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int mealId = rs.getInt("meal_id");

                // Insert meal into meal_counter table
                PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO meal_counter(enrolment_no, meal_id) VALUES (?, ?)");
                insert.setString(1, enrolmentNo);
                insert.setInt(2, mealId);
                insert.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Meal recorded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}