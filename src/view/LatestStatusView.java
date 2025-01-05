package view;

import java.awt.*;
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
        setLocationRelativeTo(null); // Posisi di tengah layar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define table columns
        String[] columnNames = {"Status Penjemputan", "Nama Kurir", "Waktu Penjemputan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
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

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
