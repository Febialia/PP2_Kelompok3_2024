package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Penjemputan;

public class LatestStatusView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public LatestStatusView() {
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
        btnTambah.addActionListener(e->tambahTracking());

        // Tombol Edit
        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(e ->editTracking());
        
        // Tombol Delete
        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(e->deleteTracking());
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);

        // Menempatkan window di tengah
        setLocationRelativeTo(null);
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

    @SuppressWarnings("unused")
    private void showTrackingPopup(String courierName) {
        JOptionPane.showMessageDialog(this,
            "Detail Tracking untuk kurir: " + courierName,
            "Detail Tracking",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteTracking() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Pilih baris yang akan dihapus!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Menggunakan tableModel bukan model
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this,
                    "Data berhasil dihapus!",
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            }
    }

    private void editTracking() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Pilih baris yang akan diedit!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Membuat dialog untuk edit data
        JDialog editDialog = new JDialog(this, "Edit Data Tracking", true);
        editDialog.setLayout(new GridLayout(4, 2, 10, 10));
        editDialog.setSize(300, 200);

        // Komponen untuk input
        JTextField txtStatus = new JTextField((String) table.getValueAt(selectedRow, 0));
        JTextField txtNama = new JTextField((String) table.getValueAt(selectedRow, 1));
        JTextField txtWaktu = new JTextField((String) table.getValueAt(selectedRow, 2));

        editDialog.add(new JLabel("Status:"));
        editDialog.add(txtStatus);
        editDialog.add(new JLabel("Nama Kurir:"));
        editDialog.add(txtNama);
        editDialog.add(new JLabel("Waktu:"));
        editDialog.add(txtWaktu);

        // Tombol Update
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            if(txtStatus.getText().isEmpty() || txtNama.getText().isEmpty() || txtWaktu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(editDialog,
                    "Semua field harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            table.setValueAt(txtStatus.getText(), selectedRow, 0);
            table.setValueAt(txtNama.getText(), selectedRow, 1);
            table.setValueAt(txtWaktu.getText(), selectedRow, 2);

            editDialog.dispose();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
        });

        // Tombol Batal
        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(e -> editDialog.dispose());

        editDialog.add(btnUpdate);
        editDialog.add(btnBatal);

        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    private void tambahTracking() {
        // Membuat dialog untuk tambah data
        JDialog addDialog = new JDialog(this, "Tambah Data Tracking", true);
        addDialog.setLayout(new GridLayout(4, 2, 10, 10));
        addDialog.setSize(300, 200);
        
        // Komponen untuk input
        JTextField txtStatus = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtWaktu = new JTextField();
        
        addDialog.add(new JLabel("Status:"));
        addDialog.add(txtStatus);
        addDialog.add(new JLabel("Nama Kurir:"));
        addDialog.add(txtNama);
        addDialog.add(new JLabel("Waktu:"));
        addDialog.add(txtWaktu);
        
        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> {
            if(txtStatus.getText().isEmpty() || txtNama.getText().isEmpty() || txtWaktu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(addDialog,
                    "Semua field harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            model.addRow(new Object[]{
                txtStatus.getText(),
                txtNama.getText(),
                txtWaktu.getText()
            });
            
            addDialog.dispose();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        });
        
        // Tombol Batal
        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(e -> addDialog.dispose());
        
        addDialog.add(btnSimpan);
        addDialog.add(btnBatal);
        
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }
    


    }

