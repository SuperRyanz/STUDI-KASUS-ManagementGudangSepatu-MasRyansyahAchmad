package views;

import javax.swing.*;
import controllers.SepatuController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardGUI extends JFrame {
    private JLabel titleLabel;
    private String loggedInUsername;

    public DashboardGUI() {
        initializeComponents();
    }

    public DashboardGUI(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
        initializeComponents();
    }

    private void initializeComponents() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 0, 128));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel = new JLabel("MANAGEMENT GUDANG SEPATU RAIANZU.ID", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new GridLayout(3, 1, 0, 5));
        featurePanel.setBackground(Color.WHITE);
        featurePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton dataSepatuButton = createStyledButton("Data Sepatu", "sepatu-icon.png", 14);
        JButton tentangKamiButton = createStyledButton("Tentang Kami", "about-icon.png", 14);
        JButton logoutButton = createStyledButton("Logout", null, 14);

        dataSepatuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDataSepatuButtonClicked();
            }
        });

        tentangKamiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTentangKamiButtonClicked();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLogoutButtonClicked();
            }
        });

        featurePanel.add(createFeaturePanel(dataSepatuButton));
        featurePanel.add(createFeaturePanel(tentangKamiButton));
        featurePanel.add(createFeaturePanel(logoutButton));

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\ryans\\Downloads\\bbbb.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBorder(null);
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(featurePanel, BorderLayout.WEST);
        container.add(imagePanel, BorderLayout.EAST);

        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private JButton createStyledButton(String text, String iconName, int fontSize) {
        JButton button;
        if (iconName != null) {
            button = new JButton(text, new ImageIcon(iconName));
        } else {
            button = new JButton(text);
        }

        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }

    private void onDataSepatuButtonClicked() {
        setVisible(false);
        SwingUtilities.invokeLater(() -> new DataSepatuGUI(new SepatuController(), loggedInUsername));
    }

    private void onTentangKamiButtonClicked() {
        setVisible(false);
        SwingUtilities.invokeLater(() -> new TentangKamiGUI());
    }

    private void onLogoutButtonClicked() {
        if (confirmLogout()) {
            dispose();
            new AdminLoginGUI();
        }
    }

    private boolean confirmLogout() {
        int option = JOptionPane.showConfirmDialog(this, "Anda yakin ingin logout?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    private JPanel createFeaturePanel(JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

}
