package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Penjemputan;

public class HistoryView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField = new JTextField(20);
    private JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Semua Status", "Dalam Perjalanan", "Selesai"});
    private List<Penjemputan> originalData; // Menyimpan data asli untuk filter

    public HistoryView() {
        setTitle("Penjemputan View");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define table columns
        String[] columnNames = {"Status Penjemputan", "Nama Kurir", "Waktu Penjemputan", "Lokasi", "Jenis Sampah", "Poin"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Add search and filter panel
        add(createSearchFilterPanel(), BorderLayout.NORTH);
    }

    private JPanel createSearchFilterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Cari: "));
        panel.add(searchField);
        panel.add(new JLabel("Filter: "));
        panel.add(filterCombo);
        return panel;
    }

    public JTextField getSearchField() {
        return searchField; // Mengembalikan instance searchField
    }

    public JComboBox<String> getFilterCombo() {
        return filterCombo; // Mengembalikan instance filterCombo
    }

    public String getSelectedFilter() {
        return (String) filterCombo.getSelectedItem(); // Mengembalikan nilai filter yang dipilih
    }

    public void setTableData(List<Penjemputan> penjemputanList) {
        originalData = penjemputanList; // Menyimpan data asli untuk filtering
        updateTable(penjemputanList); // Memperbarui tabel dengan data
    }

    private void updateTable(List<Penjemputan> penjemputanList) {
        tableModel.setRowCount(0); // Clear existing data
        for (Penjemputan penjemputan : penjemputanList) {
            tableModel.addRow(new Object[]{
                penjemputan.getStatus(),
                penjemputan.getNamaKurir(),
                penjemputan.getWaktuPenjemputan(),
                penjemputan.getLokasi(),
                penjemputan.getJenisSampah(),
                penjemputan.getPoinDidapatkan()
            });
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
