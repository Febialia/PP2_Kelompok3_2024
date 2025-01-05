
package controller;

import model.Kurir;
import model.KurirMapper;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import view.FormEditKurir;

import javax.swing.*;

public class KurirController {
    public void handleTambahKurir() {
        String namaKurir = JOptionPane.showInputDialog("Masukkan nama kurir:");
        if (namaKurir != null && !namaKurir.isEmpty()) {
            try (SqlSession session = MyBatisUtil.openSession()) {
                KurirMapper mapper = session.getMapper(KurirMapper.class);
                Kurir kurir = new Kurir();
                kurir.setNamaKurir(namaKurir); // Setter tetap menggunakan namaKurir dari model
                mapper.insertKurir(kurir); // Mapper akan menangani pemetaan ke kolom nama_kurir
                session.commit();
                JOptionPane.showMessageDialog(null, "Kurir berhasil ditambahkan!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Gagal menambahkan kurir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleEditKurir() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            KurirMapper mapper = session.getMapper(KurirMapper.class);
            // Menampilkan FormEditKurir setelah memuat mapper dan session
            FormEditKurir formEditKurir = new FormEditKurir(mapper, session);
            formEditKurir.setVisible(true); // Menampilkan form untuk mengedit kurir
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengedit kurir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleHapusKurir() {
        String idKurirStr = JOptionPane.showInputDialog("Masukkan ID kurir yang ingin dihapus:");
        if (idKurirStr != null && !idKurirStr.isEmpty()) {
            try {
                int idKurir = Integer.parseInt(idKurirStr);
                try (SqlSession session = MyBatisUtil.openSession()) {
                    KurirMapper mapper = session.getMapper(KurirMapper.class);
                    mapper.deleteKurirById(idKurir);
                    session.commit();
                    JOptionPane.showMessageDialog(null, "Kurir berhasil dihapus!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID Kurir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Gagal menghapus kurir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleLihatSemuaKurir() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            KurirMapper mapper = session.getMapper(KurirMapper.class);
            java.util.List<Kurir> kurirList = mapper.getAllKurirs();
            StringBuilder sb = new StringBuilder("Daftar Kurir:\n");
            for (Kurir kurir : kurirList) {
                sb.append("ID: ").append(kurir.getId())
                  .append(", Nama: ").append(kurir.getNamaKurir())
                  .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal memuat daftar kurir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
