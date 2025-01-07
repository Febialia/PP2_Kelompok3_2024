package view;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Penjemputan;
import model.PenjemputanMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

public class HistoryView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField = new JTextField(20);
    private JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Semua Status", "Dalam Perjalanan", "Selesai"});
    private List<Penjemputan> originalData;

    public HistoryView() {
        setTitle("History Penjemputan");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Status Penjemputan", "Nama Kurir", "Waktu Penjemputan", "Lokasi", "Jenis Sampah", "Poin"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

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

        add(scrollPane, BorderLayout.CENTER);
        add(createSearchFilterPanel(), BorderLayout.NORTH);

        // Menambahkan listener untuk pencarian dan filter
        searchField.addCaretListener(e -> filterData());
        filterCombo.addActionListener(e -> filterData());

        // Memuat data penjemputan
        loadHistoryData();
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

    // Getter methods for controller
    public JTextField getSearchField() {
        return searchField;
    }

    public JComboBox<String> getFilterCombo() {
        return filterCombo;
    }

    public String getSelectedFilter() {
        return (String) filterCombo.getSelectedItem();
    }

    public void setTableData(List<Penjemputan> penjemputanList) {
        if (penjemputanList == null || penjemputanList.isEmpty()) {
            showMessage("Data tidak ditemukan.");
            return;
        }
        
        originalData = penjemputanList;
        updateTable(penjemputanList);
    }

    private void updateTable(List<Penjemputan> penjemputanList) {
        tableModel.setRowCount(0); // Clear previous data
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

    // Method to filter data based on search and status filter
    private void filterData() {
        String searchText = searchField.getText().toLowerCase();
        String selectedStatus = (String) filterCombo.getSelectedItem();
        
        // Filter original data based on search text and status
        List<Penjemputan> filteredData = originalData.stream()
            .filter(penjemputan -> penjemputan.getStatus().toLowerCase().contains(searchText) &&
                (selectedStatus.equals("Semua Status") || penjemputan.getStatus().equalsIgnoreCase(selectedStatus)))
            .collect(Collectors.toList());
        
        updateTable(filteredData);
    }

    private void loadHistoryData() {
        try {
            // Membaca konfigurasi MyBatis
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Mendapatkan mapper
                PenjemputanMapper mapper = session.getMapper(PenjemputanMapper.class);

                // Mendapatkan data riwayat penjemputan
                List<Penjemputan> penjemputanList = mapper.getHistory();
                setTableData(penjemputanList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Terjadi kesalahan saat mengambil data.");
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
