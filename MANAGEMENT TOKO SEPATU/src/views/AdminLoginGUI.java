package views;

import javax.swing.*;
import controllers.AdminLoginController;
import controllers.LoginSuccessListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AdminLoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AdminLoginController loginController;
    private LoginSuccessListener loginSuccessListener;

    public AdminLoginGUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Load the background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\ryans\\Downloads\\Desain tanpa judul (1).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(backgroundPanel);

        loginController = new AdminLoginController();

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(180, 40, 100, 40));
        loginPanel.setOpaque(false); // Make panel transparent

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(10); // Adjust field size
        passwordField = new JPasswordField(10); // Adjust field size

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLoginButtonClicked();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancelButtonClicked();
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(cancelButton);

        // Adjust the layout to put loginPanel on the left
        backgroundPanel.add(loginPanel);

        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Adjust the size as needed
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onLoginButtonClicked() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        if (loginController.cekLogin(username, new String(password))) {
            if (loginSuccessListener != null) {
                loginSuccessListener.onLoginSuccess(username);
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed Anda Penyusup Kah!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the password from memory
        java.util.Arrays.fill(password, ' ');
    }

    private void onCancelButtonClicked() {
        dispose();
    }

    public void addLoginSuccessListener(LoginSuccessListener listener) {
        this.loginSuccessListener = listener;
    }

    // Custom panel class to display a background image
    private static class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
