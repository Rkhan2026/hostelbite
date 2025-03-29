package Client;

import Database.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

/**
 * D_VendorDashboard displays a vendor dashboard with meal logs and summary.
 */
public class D_VendorDashboard {
    JFrame frame;
    JTable mealLogTable, mealSummaryTable;
    DefaultTableModel logModel, summaryModel;

    public D_VendorDashboard() {
        // === Create main JFrame ===
        frame = new JFrame("Vendor Meal Dashboard");
        frame.setSize(900, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center window
        frame.setLayout(new BorderLayout());

        // === Fonts ===
        Font tableFont = new Font("SansSerif", Font.PLAIN, 16);
        Font headerFont = new Font("SansSerif", Font.BOLD, 18);
        Font labelFont = new Font("SansSerif", Font.BOLD, 18);

        // === Meal Log Panel ===
        JLabel logLabel = new JLabel("Meal Logs (With TimeStamps)");
        logLabel.setFont(labelFont);
        logLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));

        logModel = new DefaultTableModel();
        logModel.setColumnIdentifiers(new String[]{"Meal Name", "Meal Type", "Time"});

        mealLogTable = new JTable(logModel);
        mealLogTable.setFont(tableFont);
        mealLogTable.setRowHeight(28);
        JTableHeader logHeader = mealLogTable.getTableHeader();
        logHeader.setFont(headerFont);

        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.add(logLabel, BorderLayout.NORTH);
        logPanel.add(new JScrollPane(mealLogTable), BorderLayout.CENTER);

        // === Meal Summary Panel ===
        JLabel summaryLabel = new JLabel("Meal Count Summary");
        summaryLabel.setFont(labelFont);
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));

        summaryModel = new DefaultTableModel();
        summaryModel.setColumnIdentifiers(new String[]{"Meal Name", "Meal Type", "Count"});

        mealSummaryTable = new JTable(summaryModel);
        mealSummaryTable.setFont(tableFont);
        mealSummaryTable.setRowHeight(28);
        JTableHeader summaryHeader = mealSummaryTable.getTableHeader();
        summaryHeader.setFont(headerFont);

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.add(summaryLabel, BorderLayout.NORTH);
        summaryPanel.add(new JScrollPane(mealSummaryTable), BorderLayout.CENTER);

        // === Combine Both Panels Vertically ===
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(logPanel);
        centerPanel.add(summaryPanel);

        // === Refresh Button Panel ===
        JButton refreshBtn = new JButton("Refresh Data");
        refreshBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        refreshBtn.setPreferredSize(new Dimension(200, 40));

        // Refresh button functionality
        refreshBtn.addActionListener(e -> {
            loadMealLogs();
            loadMealSummary();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(refreshBtn);

        // === Add everything to JFrame ===
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // === Load Data on Start ===
        loadMealLogs();
        loadMealSummary();

        frame.setVisible(true);
    }

    /**
     * Loads detailed meal log data from DB.
     */
    private void loadMealLogs() {
        logModel.setRowCount(0);
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT m.meal_name, m.meal_type, mc.meal_datetime " +
                           "FROM meal_counter mc " +
                           "JOIN meals m ON mc.meal_id = m.meal_id " +
                           "ORDER BY mc.meal_datetime DESC";

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                logModel.addRow(new Object[]{
                    rs.getString("meal_name"),
                    rs.getString("meal_type"),
                    rs.getTimestamp("meal_datetime")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading meal logs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads meal count summary from DB.
     */
    private void loadMealSummary() {
        summaryModel.setRowCount(0);
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT m.meal_name, m.meal_type, COUNT(*) AS student_count " +
                           "FROM meal_counter mc " +
                           "JOIN meals m ON mc.meal_id = m.meal_id " +
                           "GROUP BY m.meal_id, m.meal_name, m.meal_type " +
                           "ORDER BY student_count DESC";

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                summaryModel.addRow(new Object[]{
                    rs.getString("meal_name"),
                    rs.getString("meal_type"),
                    rs.getInt("student_count")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading meal summary: " + e.getMessage());
            e.printStackTrace();
        }
    }
}