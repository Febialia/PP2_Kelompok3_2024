package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import model.Kurir;
import model.KurirMapper;

import org.apache.ibatis.session.SqlSession;

import controller.SidebarController.btnHome;
import controller.TambahPenjemputanController.addPenjemputan;
import model.Permintaan;
import model.PermintaanMapper;

import view.TambahKurirView;
public class TambahKurirController {
    public TambahKurirView view;
    public KurirMapper mapper;
    public SqlSession session; 

    public TambahKurirController(TambahKurirView view, KurirMapper mapper, SqlSession session, KurirController kurirController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.view.addKurir(new addKurir(kurirController));

        // NAVBAR
        SidebarController sidebarController = new SidebarController(this.view, this.session);
        this.view.redirectHome(sidebarController.new btnHome());
        this.view.redirectStatusPenjemputan(sidebarController.new btnStatusPenjemputan());
        this.view.redirectKurir(sidebarController.new btnKurir());
        this.view.redirectRiwayat(sidebarController.new btnRiwayat());
        this.view.redirectTotalBeratPoint(sidebarController.new btnBeratPoint());
        this.view.redirectPermintaan(sidebarController.new btnPermintaan());
        this.view.redirectTracking(sidebarController.new btnTracking());
    }
    
    class addKurir implements ActionListener {
        private KurirController kurirController;

        public addKurir(KurirController kurirController) {
            this.kurirController = kurirController;
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mengakses objek Kurir yang dipilih dari ComboBox
        String namaKurir = TambahKurirController.this.view.namaKurir.getText();
        
        if (!namaKurir.isEmpty()) {
            try {
                TambahKurirController.this.mapper.tambahKurir(namaKurir);
                TambahKurirController.this.session.commit();

                // Menampilkan popup untuk sukses
                JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil di-insert!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
                );
                TambahKurirController.this.view.setVisible(false);
                this.kurirController.loadKurirData();
                this.kurirController.view.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();

                // Menampilkan popup untuk error
                JOptionPane.showMessageDialog(
                    null,
                    "Error saat meng-insert data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            
        } else {
            // Menampilkan popup untuk validasi input
            JOptionPane.showMessageDialog(
                null,
                "Harap mengisi semua field terlebih dahulu!",
                "Validasi Input",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }
}


}
