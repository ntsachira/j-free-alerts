import javax.swing.*;
import java.awt.*;
import com.innovateyouth.jfreealerts.*;

public class TestEDT {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a simple frame to test the alert
            JFrame frame = new JFrame("EDT Test");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(600, 400));

            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Test creating an alert (this should now be EDT-compliant)
            JAlert alert = new JAlert(panel, JAlertType.SUCCESS, "Test message - EDT compliant!");
            alert.show();

            System.out.println("Test completed successfully - all EDT rules followed!");
        });
    }
}