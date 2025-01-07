package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Penjemputan;
import model.PenjemputanMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

public class LatestStatusView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public LatestStatusView() {
        setTitle("Penjemputan View");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define table columns
        String[] columnNames = {"Status Penjemputan", "Nama Kurir", "Waktu Penjemputan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Menambahkan mouse listener untuk table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                
                // Cek jika klik pada kolom Nama Kurir (index 1)
                if (col == 1 && row >= 0) {
                    String courierName = (String) table.getValueAt(row, col);
                    showTrackingPopup(courierName, row);
                }
            }
        });

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Tombol Tambah 
        JButton btnTambah = new JButton("Tambah");
        btnTambah.addActionListener(e -> tambahTracking());

        // Tombol Edit
        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(e -> editTracking());
        
        // Tombol Delete
        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(e -> deleteTracking());
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        setLocationRelativeTo(null);

        add(scrollPane, BorderLayout.CENTER);

        // Memanggil data dan menampilkan ke tabel
        loadLatestStatus();
    }

    public void loadLatestStatus() {
        try {
            // Membaca konfigurasi MyBatis
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Mendapatkan mapper
                PenjemputanMapper mapper = session.getMapper(PenjemputanMapper.class);

                // Mendapatkan data penjemputan terbaru
                List<Penjemputan> penjemputanList = mapper.getLatestStatus();
                setTableData(penjemputanList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Terjadi kesalahan saat mengambil data.");
        }

    }

    public void setTableData(List<Penjemputan> penjemputanList) {
        if (penjemputanList == null || penjemputanList.isEmpty()) {
            showMessage("Data tidak ditemukan.");
            return;
        }

        tableModel.setRowCount(0); // Clear existing data
        for (Penjemputan penjemputan : penjemputanList) {
            tableModel.addRow(new Object[]{
                penjemputan.getStatus(),
                penjemputan.getNamaKurir(),
                penjemputan.getWaktuPenjemputan()
            });
        }
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

        JDialog editDialog = new JDialog(this, "Edit Data Tracking", true);
        editDialog.setLayout(new GridLayout(4, 2, 10, 10));
        editDialog.setSize(300, 200);

        JTextField txtStatus = new JTextField((String) table.getValueAt(selectedRow, 0));
        JTextField txtNama = new JTextField((String) table.getValueAt(selectedRow, 1));
        JTextField txtWaktu = new JTextField((String) table.getValueAt(selectedRow, 2));

        editDialog.add(new JLabel("Status:"));
        editDialog.add(txtStatus);
        editDialog.add(new JLabel("Nama Kurir:"));
        editDialog.add(txtNama);
        editDialog.add(new JLabel("Waktu:"));
        editDialog.add(txtWaktu);

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

        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(e -> editDialog.dispose());

        editDialog.add(btnUpdate);
        editDialog.add(btnBatal);

        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    private void tambahTracking() {
        JDialog addDialog = new JDialog(this, "Tambah Data Tracking", true);
        addDialog.setLayout(new GridLayout(4, 2, 10, 10));
        addDialog.setSize(300, 200);
        
        JTextField txtStatus = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtWaktu = new JTextField();
        
        addDialog.add(new JLabel("Status:"));
        addDialog.add(txtStatus);
        addDialog.add(new JLabel("Nama Kurir:"));
        addDialog.add(txtNama);
        addDialog.add(new JLabel("Waktu:"));
        addDialog.add(txtWaktu);
        
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> {
            if(txtStatus.getText().isEmpty() || txtNama.getText().isEmpty() || txtWaktu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(addDialog,
                    "Semua field harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            tableModel.addRow(new Object[]{
                txtStatus.getText(),
                txtNama.getText(),
                txtWaktu.getText()
            });
            
            addDialog.dispose();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        });
        
        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(e -> addDialog.dispose());
        
        addDialog.add(btnSimpan);
        addDialog.add(btnBatal);
        
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }

    private void showTrackingPopup(String courierName, int row) {
        JDialog trackingDialog = new JDialog(this, "Detail Tracking Kurir", true);
        trackingDialog.setLayout(new BorderLayout(10, 10));
        trackingDialog.setSize(350, 200);
    
        JPanel detailPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        detailPanel.add(new JLabel("Nama Kurir:"));
        detailPanel.add(new JLabel(courierName));
        
        detailPanel.add(new JLabel("Status:"));
        detailPanel.add(new JLabel((String) table.getValueAt(row, 0)));
        
        detailPanel.add(new JLabel("Waktu:"));
        detailPanel.add(new JLabel((String) table.getValueAt(row, 2)));
    
        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> trackingDialog.dispose());
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
    
        trackingDialog.add(detailPanel, BorderLayout.CENTER);
        trackingDialog.add(buttonPanel, BorderLayout.SOUTH);
    
        trackingDialog.setLocationRelativeTo(this);
        trackingDialog.setVisible(true);
    }
}


    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

