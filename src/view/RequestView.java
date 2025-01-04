package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Permintaan;
import java.util.List;

public class RequestView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    
    public RequestView() {
        setTitle("Daftar Permintaan Penjemputan");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        String[] columns = {
            "ID", "Nama Pelanggan", "Alamat", 
            "Jenis Sampah", "Tanggal Penjemputan", "Status"
        };
        
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void updateTable(List<Permintaan> requests) {
        tableModel.setRowCount(0);
        for (Permintaan request : requests) {
            Object[] row = {
                request.getId(),
                request.getNamaPelanggan(),
                request.getAlamat(),
                request.getJenisSampah(),
                request.getTanggalPenjemputan(),
                request.getBeratSampah()
            };
            tableModel.addRow(row);
        }
    }
}