package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Penjemputan;

public class HistoryView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public HistoryView() {
        setTitle("Penjemputan View");
        setSize(400, 300);
        setLocationRelativeTo(null); // Posisi di tengah layar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define table columns
        String[] columnNames = {"Status Penjemputan", "Nama Kurir", "Waktu Penjemputan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Tombol Tambah 
        JButton btnTambah = new JButton("Tambah");
        // btnTambah.addActionListener();

        // Tombol Edit
        JButton btnEdit = new JButton("Edit");
        // btnEdit.addActionListener();
        
        // Tombol Delete
        JButton btnDelete = new JButton("Hapus");
        // btnDelete.addActionListener();
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    public void setTableData(List<Penjemputan> penjemputanList) {
        tableModel.setRowCount(0); // Clear existing data
        for (Penjemputan penjemputan : penjemputanList) {
            tableModel.addRow(new Object[]{
                penjemputan.getStatus(),
                penjemputan.getNamaKurir(),
                penjemputan.getWaktuPenjemputan()
            });
        }
    }
}
