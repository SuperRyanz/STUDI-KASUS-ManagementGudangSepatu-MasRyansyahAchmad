package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TentangKamiGUI extends JFrame {

    public TentangKamiGUI() {
        setTitle("Tentang Kami");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat panel dengan layout border
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#000080")); // Warna background #000080

        // Menambahkan label Selamat Datang di tengah atas dengan font putih
        JLabel welcomeLabel = new JLabel("Selamat Datang di Halaman \"Tentang Kami\"\nManagement Gudang Sepatu");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(Color.WHITE); // Set warna font putih
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Menambahkan area teks untuk teks informasi
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setOpaque(false);
        infoTextArea.setForeground(Color.WHITE); // Set warna font putih
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        // Menambahkan informasi tentang kami
        infoTextArea.append(
                "Selamat datang di halaman \"Tentang Kami\" yang dirancang khusus untuk memberikan gambaran tentang manajemen gudang sepatu kami. Di sinilah Anda dapat menemukan informasi penting tentang visi, misi, serta nilai-nilai inti yang menjadi landasan kami dalam menjalankan operasi gudang sepatu ini.\n\n");

        infoTextArea.append("Visi\n");
        infoTextArea.append(
                "Menjadi pusat distribusi terkemuka dalam industri sepatu, memberikan pelayanan terbaik, dan menjadi mitra strategis bagi merek-merek ternama di seluruh dunia.\n\n");

        infoTextArea.append("Misi\n");
        infoTextArea.append(
                "Pengelolaan Stok yang Efisien:\nMenyediakan solusi pengelolaan stok yang inovatif dan efisien, memastikan ketersediaan sepatu terkini dan berkualitas tinggi.\n\n");
        infoTextArea.append(
                "Pelayanan Pelanggan yang Unggul:\nMenyediakan layanan pelanggan yang prima, responsif, dan personal untuk memenuhi kebutuhan pelanggan dengan sempurna.\n\n");
        infoTextArea.append(
                "Inovasi Teknologi:\nMengadopsi teknologi terkini dalam manajemen gudang untuk meningkatkan efisiensi operasional dan kepuasan pelanggan.\n\n");
        infoTextArea.append(
                "Kemitraan yang Berkelanjutan:\nMembangun kemitraan yang kokoh dan berkelanjutan dengan merek-merek sepatu terkemuka, distributor, dan pemasok.\n\n");
        infoTextArea.append(
                "Kepedulian Lingkungan:\nBerkomitmen untuk praktik-praktik ramah lingkungan dalam operasional gudang, termasuk pengelolaan limbah dan pengurangan jejak karbon.\n\n");

        infoTextArea.append("Nilai Inti\n");
        infoTextArea.append(
                "Kualitas Terbaik:\nKami berkomitmen untuk menyediakan sepatu berkualitas tinggi dan memastikan setiap pasang sepatu yang kami kelola memenuhi standar kualitas yang ketat.\n\n");
        infoTextArea.append(
                "Integritas:\nKami bertindak dengan integritas dan kejujuran dalam setiap aspek operasional, membangun kepercayaan pelanggan dan mitra bisnis.\n\n");
        infoTextArea.append(
                "Ketepatan Waktu:\nKami menghargai waktu pelanggan kami dan berkomitmen untuk pengiriman yang tepat waktu dan efisien.\n\n");
        infoTextArea.append(
                "Keterbukaan dan Transparansi:\nKami menjunjung tinggi keterbukaan dan transparansi dalam semua interaksi, baik itu dengan pelanggan, mitra bisnis, atau anggota tim internal.\n\n");
        infoTextArea.append(
                "Inovasi Berkelanjutan:\nKami terus mendorong inovasi dalam teknologi dan proses operasional untuk tetap relevan dan memenuhi kebutuhan pasar yang terus berkembang.\n\n");

        infoTextArea.append(
                "Terima kasih telah memilih kami sebagai mitra dalam penyediaan dan distribusi sepatu. Kami berkomitmen untuk terus meningkatkan layanan kami demi kepuasan pelanggan dan kesuksesan bersama. Jangan ragu untuk menghubungi kami jika Anda memiliki pertanyaan lebih lanjut atau membutuhkan bantuan.");

        // Menambahkan area teks ke dalam panel
        panel.add(infoTextArea, BorderLayout.CENTER);

        // Tombol Beranda
        JButton berandaButton = new JButton("Beranda");
        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Memanggil class DashboardGUI
                new DashboardGUI().setVisible(true);

                // Menutup panel TentangKamiGUI
                dispose();
            }
        });
        berandaButton.setFont(new Font("Arial", Font.BOLD, 18));
        berandaButton.setForeground(Color.BLACK); // Set warna font hitam
        berandaButton.setBackground(Color.WHITE); // Warna background tombol putih
        berandaButton.setBorderPainted(false);
        berandaButton.setFocusPainted(false);
        panel.add(berandaButton, BorderLayout.SOUTH);

        // Menampilkan panel ke frame
        add(panel);

        // Menampilkan frame
        setLocationRelativeTo(null); // Posisikan jendela di tengah layar
        setVisible(true);
    }

}