package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Penjemputan;

public class LatestStatusView23 extends JFrame {
    private JTable table;
    public DefaultTableModel tableModel;
    private JButton tambahButton = new JButton("Tambah Penjemputan");
    public LatestStatusView23() {
        // Konfigurasi utama frame
        setTitle("GreenBytes - Status Penjemputan");
        setSize(800, 600); // Lebar diperbesar untuk membagi layar
        setLocationRelativeTo(null); // Posisi di tengah layar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Sidebar (Kiri)
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(34, 139, 34)); // Hijau gelap sebagai warna utama
        sidebar.setPreferredSize(new Dimension(200, getHeight())); // Lebar tetap 200px
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Layout vertikal

        // Judul besar di sidebar
        JLabel titleLabel = new JLabel("GreenBytes");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE); // Warna tulisan putih
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Font besar
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding atas-bawah
        sidebar.add(titleLabel);

        // Daftar menu
        String[] menuItems = {
            "Halaman Utama", "Kurir", "Status Penjemputan", "Permintaan",
            "Riwayat Penjemputan", "Status Tracking", "Total Berat & Point"
        };
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setMaximumSize(new Dimension(180, 40)); // Ukuran tetap
            menuButton.setBackground(new Color(50, 205, 50)); // Hijau yang lebih soft
            menuButton.setForeground(Color.WHITE); // Tulisan warna putih
            menuButton.setFocusPainted(false);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Font sedang
            sidebar.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi antar tombol
            sidebar.add(menuButton);
        }

        // Panel Konten (Kanan)
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);

        // Judul besar di konten
        JLabel contentTitle = new JLabel("Status Penjemputan");
        contentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 20));
        contentTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        content.add(contentTitle, BorderLayout.NORTH);

        // Panel untuk Table dan Button
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        // Tombol "Tambah Penjemputan"
        tambahButton.setBackground(new Color(50, 205, 50));
        tambahButton.setForeground(Color.WHITE);
        tambahButton.setFocusPainted(false);
        tambahButton.setFont(new Font("Arial", Font.PLAIN, 14));
        tambahButton.setPreferredSize(new Dimension(160, 40));
        tablePanel.add(tambahButton, BorderLayout.NORTH);

        // Tabel Data
        String[] columnNames = {"ID","Status Penjemputan", "Nama Kurir", "Waktu Penjemputan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        content.add(tablePanel, BorderLayout.CENTER);

        // Tambahkan panel sidebar dan konten ke frame utama
        add(sidebar, BorderLayout.WEST); // Sidebar di kiri
        add(content, BorderLayout.CENTER); // Konten di kanan
    }

    // Fungsi untuk mengisi data ke tabel
    public void setTableData(List<Penjemputan> penjemputanList) {
        tableModel.setRowCount(0); // Menghapus data sebelumnya
        for (Penjemputan penjemputan : penjemputanList) {
            tableModel.addRow(new Object[]{
                penjemputan.getId(),
                penjemputan.getStatus(),
                penjemputan.getNamaKurir(),
                penjemputan.getWaktuPenjemputan()
            });
        }
    }

    public void addTambahJemputan(ActionListener listener) {
        tambahButton.addActionListener(listener);
    }

    public void editPenjemputan(MouseListener listener) {
        table.addMouseListener(listener);
    }

    public JTable getTable() {
        return table;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
}