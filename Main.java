// src/Main.java

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    // Initial settings for the stitch counter
    private static int stitchCount = 0;
    private static int rowCount = 0;
    private static ArrayList<String> rowHistory = new ArrayList<>();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public static void main(String[] args) {
    
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Modern UI
        } catch (Exception ignored) {}

        // Create the main window
        JFrame window = new JFrame("Stitch Counter for Fiber Arts");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000, 400);
        window.setLocationRelativeTo(null); 
    
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a panel for labels
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 2, 10, 10));
        
        JLabel stitchLabel = new JLabel("Stitch Count: " + stitchCount, SwingConstants.CENTER);
        JLabel rowLabel = new JLabel("Row: " + rowCount, SwingConstants.CENTER);
        
        stitchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rowLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        labelPanel.add(stitchLabel);
        labelPanel.add(rowLabel);
        mainPanel.add(labelPanel, BorderLayout.NORTH);

        // Create a panel for the buttons (3 rows)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column

        // First row: + and - buttons (larger size)
        JPanel row1 = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns
        JButton button5 = createStyledButton("+", new Color(76, 175, 80)); // Green (Add button)
        JButton button6 = createStyledButton("-", new Color(244, 67, 54)); // Red (Remove button)
        button5.setPreferredSize(new Dimension(250, 100)); // Make + and - buttons larger
        button6.setPreferredSize(new Dimension(250, 100)); // Make + and - buttons larger
        row1.add(button5);
        row1.add(button6);

        // Second row: stitch selection buttons (1, 3, 5, 10)
        JPanel row2 = new JPanel(new GridLayout(1, 4, 10, 10)); // 1 row, 4 columns
        JButton button1 = createStyledButton("1", new Color(33, 150, 243)); // Blue
        JButton button2 = createStyledButton("3", new  Color(33, 150, 243)); // Blue
        JButton button3 = createStyledButton("5", new Color(33, 150, 243)); // Blue
        JButton button4 = createStyledButton("10", new Color(33, 150, 243)); // Blue
        row2.add(button1);
        row2.add(button2);
        row2.add(button3);
        row2.add(button4);

        // Third row: "New Row", "Remove Row", and "Reset Everything" centered
        JPanel row3 = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 row, 3 columns
        JButton button7 = createStyledButton("New Row", new Color(81, 45, 109)); // Dark Purple
        JButton button8 = createStyledButton("Remove Row", new Color(128, 0, 32)); // Burgundy
        JButton button9 = createStyledButton("Reset Everything", new Color(34, 139, 34)); // Forest Green
        row3.add(button7);
        row3.add(button8);
        row3.add(button9);

        // Add all the rows to the button panel
        buttonPanel.add(row1);
        buttonPanel.add(row2);
        buttonPanel.add(row3);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Create row history list
        listModel.addElement("Row History:");
        JList<String> rowList = new JList<>(listModel);
        rowList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(rowList);
        scrollPane.setPreferredSize(new Dimension(180, 200)); // Set size for the list

        // Create a side panel for row history
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBorder(BorderFactory.createTitledBorder("Row History"));
        sidePanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(sidePanel, BorderLayout.EAST); // Add row history panel to the right

        // Create an instance of ButtonHandler and pass the labels and buttons
        ButtonHandler handler = new ButtonHandler(stitchLabel, rowLabel, listModel, button1, button2, button3, button4);

        // Attach the handler to each button
        button1.addActionListener(handler);
        button2.addActionListener(handler);
        button3.addActionListener(handler);
        button4.addActionListener(handler);
        button5.addActionListener(handler);
        button6.addActionListener(handler);
        button7.addActionListener(handler);
        button8.addActionListener(handler);
        button9.addActionListener(handler);
        
        // Add main panel to the window
        window.add(mainPanel);

        // Make the window visible
        window.setVisible(true);
    }
    // Method to create a styled button with color
    private static JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50)); // Set custom width and height
        return button;
    }
}


// Use https://stitchcount.xyz/ for reference ideas
