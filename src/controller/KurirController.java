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
                kurir.setNamaKurir(namaKurir);
                mapper.insertKurir(kurir);
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
            formEditKurir.setVisible(true);  // Menampilkan form untuk mengedit kurir
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengedit kurir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}