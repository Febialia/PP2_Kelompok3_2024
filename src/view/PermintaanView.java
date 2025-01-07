package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

public class PermintaanView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton btnKembali;
    private JButton btnHapus;
    private JButton btnRefresh;

    public PermintaanView() {
        setTitle("Daftar Permintaan");
        setSize(750, 500);
        setLocationRelativeTo(null);
        
        // Create table model with columns
        String[] columns = {"ID", "Nama Pelanggan", "Alamat", "Jenis Sampah", "Berat Sampah", "Tanggal Penjemputan" };
        tableModel = new DefaultTableModel(columns, 0);
        
        // Create table and scroll pane
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        
        // Add to frame
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnKembali = new JButton("Kembali");
        btnHapus = new JButton("Hapus");
        btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnKembali);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnRefresh);
        
        // Layout setup
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void setTableData(Object[][] data) {
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public void addKembaliListener(ActionListener listener) {
        btnKembali.addActionListener(listener);
    }
    
    public void addHapusListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }
    
    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public int getSelectedId() {
        int selectedRow = table.getSelectedRow();
        return selectedRow != -1 ? (int) table.getValueAt(selectedRow, 0) : -1;
    }

    public JTable getTable() {
        return table;
    }
}