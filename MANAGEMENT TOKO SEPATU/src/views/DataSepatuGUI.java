    package views;

    import controllers.SepatuController;
    import entity.SepatuEntity;

    import javax.swing.*;
    import javax.swing.table.DefaultTableCellRenderer;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.event.DocumentEvent;
    import javax.swing.event.DocumentListener;
    import java.util.List;

    public class DataSepatuGUI extends JFrame {
        private SepatuController sepatuController;
        private JTextField searchField;
        private JButton searchButton;
        private JTable sepatuTable;
        private JButton addButton;
        private JButton deleteButton;
        private JButton refreshButton;
        private JButton homeButton; // Tombol untuk beranda

        public DataSepatuGUI(SepatuController sepatuController, String loggedInUsername) {
            this.sepatuController = sepatuController;

            setTitle("Data Sepatu");
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Buka dalam mode fullscreen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            initComponents();
            initTable();

            setLocationRelativeTo(null);
            setVisible(true);
        }

        private void initComponents() {
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Mengubah alignment menjadi CENTER
            searchField = new JTextField(20);
            searchButton = new JButton("Search");
            topPanel.add(new JLabel("Pencarian:"));
            topPanel.add(searchField);
            topPanel.add(searchButton);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Mengubah alignment menjadi CENTER
            bottomPanel.setBackground(Color.decode("#000080")); // Menambahkan warna latar belakang
            addButton = new JButton("Add Sepatu");
            deleteButton = new JButton("Delete Sepatu");
            refreshButton = new JButton("Refresh");
            homeButton = new JButton("Beranda"); // Tombol untuk beranda
            bottomPanel.add(addButton);
            bottomPanel.add(deleteButton);
            bottomPanel.add(refreshButton);
            bottomPanel.add(homeButton); // Tombol beranda

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setBackground(Color.decode("#000080")); // Menambahkan warna latar belakang
            sepatuTable = new JTable();
            centerPanel.add(new JScrollPane(sepatuTable), BorderLayout.CENTER);

            add(topPanel, BorderLayout.NORTH);
            add(centerPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);

            // Set actions for buttons
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchSepatu();
                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showAddSepatuDialog();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteSepatu();
                }
            });

            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshData();
                }
            });

            homeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Arahkan ke class DashboardGUI
                    dispose(); // Tutup frame ini sebelum membuka frame baru
                    new DashboardGUI(); // Membuat instance DashboardGUI
                }
            });

            // Add document listener to search field for real-time search
            searchField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    searchSepatu();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    searchSepatu();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    searchSepatu();
                }
            });

            // Set listener untuk deteksi klik pada tabel
            sepatuTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = sepatuTable.rowAtPoint(evt.getPoint());
                    int col = sepatuTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        // Tindakan yang akan diambil saat tabel diklik
                        showEditSepatuDialog(row);
                    }
                }
            });
        }

        private void initTable() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Merk");
            model.addColumn("Nama");
            model.addColumn("Warna");
            model.addColumn("Stok");

            sepatuTable.setModel(model);

            // Set renderers and separators for table cells
            setTableCellRenderers();

            // Add separator between header and data rows
            sepatuTable.setShowGrid(true);
            sepatuTable.setGridColor(Color.WHITE);

            refreshData();
        }

        private void setTableCellRenderers() {
            // Set custom header renderer
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(CENTER);
                    setBackground(Color.decode("#000080"));
                    setForeground(Color.WHITE);
                    setFont(getFont().deriveFont(Font.BOLD, 14));
                }
            };

            // Apply the custom renderer to the table headers
            for (int i = 0; i < sepatuTable.getColumnModel().getColumnCount(); i++) {
                sepatuTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }

            // Set custom cell renderer
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(CENTER);
                }

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Beri warna latar belakang #000080 untuk semua baris
                    comp.setBackground(Color.decode("#000080"));
                    comp.setForeground(Color.WHITE);

                    return comp;
                }
            };

            // Apply the custom renderer to all cells
            sepatuTable.setDefaultRenderer(Object.class, cellRenderer);

            // Atur ukuran kolom
            sepatuTable.getColumnModel().getColumn(0).setPreferredWidth(150); // Merk
            sepatuTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Nama
            sepatuTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Warna
            sepatuTable.getColumnModel().getColumn(3).setPreferredWidth(50); // Stok
        }

        private void refreshData() {
            DefaultTableModel model = (DefaultTableModel) sepatuTable.getModel();
            model.setRowCount(0); // Clear existing rows

            List<SepatuEntity> sepatuList = sepatuController.getListSepatu();
            for (SepatuEntity sepatu : sepatuList) {
                Object[] row = { sepatu.getMerk(), sepatu.getNama(), sepatu.getWarna(), sepatu.getStok() };
                model.addRow(row);
            }
        }

        private void searchSepatu() {
            String keyword = searchField.getText().trim();

            // Call the controller method for searching
            List<SepatuEntity> searchResult = sepatuController.searchSepatu(keyword);

            // Update the table with search result
            updateTable(searchResult);

            if (searchResult.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mohon Maaf Untuk Sepatu Ini Kosong", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        private void showAddSepatuDialog() {
            JDialog addDialog = new JDialog(this, "Tambah Sepatu", true);
            addDialog.setLayout(new GridLayout(5, 2));

            JTextField merkField = new JTextField();
            JTextField namaField = new JTextField();
            JTextField warnaField = new JTextField();
            JTextField stokField = new JTextField();

            addDialog.add(new JLabel("Merk:"));
            addDialog.add(merkField);
            addDialog.add(new JLabel("Nama:"));
            addDialog.add(namaField);
            addDialog.add(new JLabel("Warna:"));
            addDialog.add(warnaField);
            addDialog.add(new JLabel("Stok:"));
            addDialog.add(stokField);

            JButton addButton = new JButton("Tambah");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ambil nilai dari input fields
                    String merk = merkField.getText().trim();
                    String nama = namaField.getText().trim();
                    String warna = warnaField.getText().trim();
                    int stok = Integer.parseInt(stokField.getText().trim());

                    // Panggil controller untuk menambah sepatu ke database
                    sepatuController.tambahSepatu(merk, nama, warna, stok);

                    // Refresh data di tabel setelah penambahan
                    refreshData();

                    // Tutup dialog setelah penambahan
                    addDialog.dispose();
                }
            });

            JButton cancelButton = new JButton("Batal");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Tutup dialog jika dibatalkan
                    addDialog.dispose();
                }
            });

            addDialog.add(addButton);
            addDialog.add(cancelButton);

            addDialog.setSize(300, 200);
            addDialog.setLocationRelativeTo(this);
            addDialog.setVisible(true);
        }

        private void showEditSepatuDialog(int selectedRow) {
            JDialog editDialog = new JDialog(this, "Edit Sepatu", true);
            editDialog.setLayout(new GridLayout(5, 2));

            // Ambil data dari tabel sesuai dengan baris yang diklik
            String merk = (String) sepatuTable.getValueAt(selectedRow, 0);
            String nama = (String) sepatuTable.getValueAt(selectedRow, 1);
            String warna = (String) sepatuTable.getValueAt(selectedRow, 2);
            int stok = (int) sepatuTable.getValueAt(selectedRow, 3);

            JTextField merkField = new JTextField(merk);
            JTextField namaField = new JTextField(nama);
            JTextField warnaField = new JTextField(warna);
            JTextField stokField = new JTextField(String.valueOf(stok));

            editDialog.add(new JLabel("Merk:"));
            editDialog.add(merkField);
            editDialog.add(new JLabel("Nama:"));
            editDialog.add(namaField);
            editDialog.add(new JLabel("Warna:"));
            editDialog.add(warnaField);
            editDialog.add(new JLabel("Stok:"));
            editDialog.add(stokField);

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ambil nilai dari input fields
                    String newMerk = merkField.getText().trim();
                    String newNama = namaField.getText().trim();
                    String newWarna = warnaField.getText().trim();
                    int newStok = Integer.parseInt(stokField.getText().trim());

                    // Panggil controller untuk mengedit sepatu ke database
                    sepatuController.editSepatu(nama, new SepatuEntity(newNama, newMerk, newWarna, newStok));

                    // Refresh data di tabel setelah pengeditan
                    refreshData();

                    // Tutup dialog setelah pengeditan
                    editDialog.dispose();
                }
            });

            JButton cancelButton = new JButton("Batal");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Tutup dialog jika dibatalkan
                    editDialog.dispose();
                }
            });

            editDialog.add(editButton);
            editDialog.add(cancelButton);

            editDialog.setSize(300, 200);
            editDialog.setLocationRelativeTo(this);
            editDialog.setVisible(true);
        }

        private void deleteSepatu() {
            int selectedRow = sepatuTable.getSelectedRow();

            if (selectedRow != -1) {
                int option = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data sepatu?",
                        "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String nama = (String) sepatuTable.getValueAt(selectedRow, 1);

                    // Panggil controller untuk menghapus sepatu dari database
                    sepatuController.hapusSepatu(nama);

                    // Refresh data di tabel setelah penghapusan
                    refreshData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.", "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        private void updateTable(List<SepatuEntity> data) {
            DefaultTableModel model = (DefaultTableModel) sepatuTable.getModel();
            model.setRowCount(0); // Clear existing rows

            for (SepatuEntity sepatu : data) {
                Object[] row = { sepatu.getMerk(), sepatu.getNama(), sepatu.getWarna(), sepatu.getStok() };
                model.addRow(row);
            }
        }

    }
