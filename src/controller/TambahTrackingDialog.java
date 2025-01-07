package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TambahTrackingDialog extends JDialog {
    private JTextField txtNoResi;
    private JTextField txtStatus;
    private JTextArea txtKeterangan;
    private JButton btnSimpan;
    private JButton btnBatal;
    private Connection conn;
    
    public TambahTrackingDialog(JFrame parent, Connection conn) {
        super(parent, "Tambah Tracking", true);
        this.conn = conn;
        initComponents();
    }
    
    private void initComponents() {
        // Layout
        setLayout(new BorderLayout());
        
        // Panel untuk form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Komponen-komponen form
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("No. Resi:"), gbc);
        
        gbc.gridx = 1;
        txtNoResi = new JTextField(20);
        formPanel.add(txtNoResi, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        txtStatus = new JTextField(20);
        formPanel.add(txtStatus, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Keterangan:"), gbc);
        
        gbc.gridx = 1;
        txtKeterangan = new JTextArea(4, 20);
        JScrollPane scrollPane = new JScrollPane(txtKeterangan);
        formPanel.add(scrollPane, gbc);
        
        // Panel untuk button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");
        
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnBatal);
        
        // Action Listeners
        btnSimpan.addActionListener(e -> simpanTracking());
        btnBatal.addActionListener(e -> dispose());
        
        // Tambahkan panel ke dialog
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(getParent());
    }
    
    private void simpanTracking() {
        try {
            String sql = "INSERT INTO tracking (no_resi, status, keterangan, tanggal) VALUES (?, ?, ?, NOW())";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtNoResi.getText());
            pstmt.setString(2, txtStatus.getText());
            pstmt.setString(3, txtKeterangan.getText());
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data tracking berhasil disimpan");
            dispose();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
