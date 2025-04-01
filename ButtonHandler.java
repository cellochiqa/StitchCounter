// src/ButtonHandler.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;;

public class ButtonHandler implements ActionListener {
    private JLabel stitchLabel;
    private JLabel rowLabel;
    private DefaultListModel<String> listModel; 
    private static int stitchCount = 0;
    private static int rowCount = 0;
    private static int stitchChangeValue = 1;  // Default value for stitch change (1 stitch)

    // References to the buttons for 1, 3, 5, and 10
    private JButton button1, button2, button3, button4;

    public ButtonHandler(JLabel stitchLabel, JLabel rowLabel, DefaultListModel<String> listModel, 
                         JButton button1, JButton button2, JButton button3, JButton button4) {
        this.stitchLabel = stitchLabel;
        this.rowLabel = rowLabel;
        this.listModel = listModel;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Button clicked
        JButton source = (JButton) e.getSource();

        // Handle color change only for 1, 3, 5, 10 buttons
        if (source == button1 || source == button2 || source == button3 || source == button4) {
            resetButtonColors();  // Reset all buttons to blue
            source.setBackground(Color.YELLOW);  // Highlight the selected button in yellow
        }
        // Handle button actions
        String command = source.getText();  // Get the button's text (e.g., "+", "1", "3", "New Row")
        switch (command) {
            case "+":
                stitchCount += stitchChangeValue;  // Add the selected number of stitches
                break;
            case "-":
                if (stitchCount >= stitchChangeValue) {
                    stitchCount -= stitchChangeValue;  // Remove the selected number of stitches
                }
                break;
            case "1":
                stitchChangeValue = 1;  // Set change value to 1
                button1.setBackground(Color.YELLOW);  // Highlight the selected button
                break;
            case "3":
                stitchChangeValue = 3;  // Set change value to 3
                button2.setBackground(Color.YELLOW);  // Highlight the selected button
                break;
            case "5":
                stitchChangeValue = 5;  // Set change value to 5
                button3.setBackground(Color.YELLOW);  // Highlight the selected button
                break;
            case "10":
                stitchChangeValue = 10;  // Set change value to 10
                button4.setBackground(Color.YELLOW);  // Highlight the selected button
                break;
            case "New Row":
                if (stitchCount > 0) {
                    rowCount++;
                    // Add new row at the top of the list (invert order)
                    listModel.insertElementAt("Row " + rowCount + ": " + stitchCount + " stitches", 1);
                    stitchCount = 0; // Reset stitch count for new row
                }
                break;
            case "Remove Row":
                if (rowCount > 0) {
                    rowCount--;
                    listModel.remove(1);  // Remove the first row entry, not the "Row History:" label
                }
                break;
            case "Reset Everything":
                stitchCount = 0;
                rowCount = 0;
                listModel.clear();
                listModel.addElement("Row History:");  // Keep "Row History:" at the top
                break;
        }
        
        // Update the labels to reflect the changes
        stitchLabel.setText("Stitch Count: " + stitchCount);
        rowLabel.setText("Row: " + rowCount);
    }
// Helper method to reset the button colors to blue
private void resetButtonColors() {
    button1.setBackground(new Color(33, 150, 243)); // Blue
    button2.setBackground(new Color(33, 150, 243)); // Blue
    button3.setBackground(new Color(33, 150, 243)); // Blue
    button4.setBackground(new Color(33, 150, 243)); // Blue
}
}
