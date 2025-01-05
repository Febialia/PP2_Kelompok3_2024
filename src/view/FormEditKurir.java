/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author aldip
 */

import model.KurirMapper;
import model.Kurir;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.MyBatisUtil;

public class FormEditKurir extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private KurirMapper mapper;
    private SqlSession session;

    public FormEditKurir(KurirMapper mapper, SqlSession session) {
        this.mapper = mapper;
        this.session = session;
        setTitle("Edit Kurir");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama Kurir"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadKurirData();

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");

        editButton.addActionListener(e -> editKurir());
        deleteButton.addActionListener(e -> deleteKurir());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadKurirData() {
    try (SqlSession session = MyBatisUtil.openSession()) {
        KurirMapper mapper = session.getMapper(KurirMapper.class);
        List<Kurir> kurirList = mapper.getAllKurirs();
        System.out.println("Data Kurir: " + kurirList); // Debugging
        tableModel.setRowCount(0);
        for (Kurir kurir : kurirList) {
            tableModel.addRow(new Object[]{kurir.getId(), kurir.getNamaKurir()});
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal memuat data kurir!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

 
    private void editKurir() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String namaKurirBaru = JOptionPane.showInputDialog(this, "Masukkan nama kurir baru:", tableModel.getValueAt(selectedRow, 1));
        if (namaKurirBaru != null && !namaKurirBaru.trim().isEmpty()) {
            try (SqlSession session = MyBatisUtil.openSession()) {
                KurirMapper mapper = session.getMapper(KurirMapper.class);
                Kurir kurir = new Kurir();
                kurir.setId(id);
                kurir.setNamaKurir(namaKurirBaru);
                mapper.updateKurir(kurir);
                session.commit();
                JOptionPane.showMessageDialog(this, "Kurir berhasil diperbarui!");
                loadKurirData(); // Refresh tabel
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal memperbarui kurir!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih kurir yang ingin diedit!");
    }
}
    


private void deleteKurir() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus kurir ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try (SqlSession session = MyBatisUtil.openSession()) {
                KurirMapper mapper = session.getMapper(KurirMapper.class);
                mapper.deleteKurirById(id);
                session.commit();
                JOptionPane.showMessageDialog(this, "Kurir berhasil dihapus!");
                loadKurirData();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menghapus kurir!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih kurir yang ingin dihapus!");
    }
}

}