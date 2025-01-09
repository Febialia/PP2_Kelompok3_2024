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

import view.TambahPermintaanView;
public class TambahPermintaanController {
    public TambahPermintaanView view;
    public PermintaanMapper mapper;
    public SqlSession session; 

    public TambahPermintaanController(TambahPermintaanView view, PermintaanMapper mapper, SqlSession session, PermintaanController permintaanController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.view.addPermintaan(new addPermintaan(permintaanController));

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
    
    class addPermintaan implements ActionListener {
        private PermintaanController permintaanController;

        public addPermintaan(PermintaanController permintaanController) {
            this.permintaanController = permintaanController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengakses objek Kurir yang dipilih dari ComboBox
            String namaPelangganInput = TambahPermintaanController.this.view.namaPelanggan.getText();
            String alamatInput = TambahPermintaanController.this.view.alamat.getText();
            String beratSampahInput = TambahPermintaanController.this.view.beratSampah.getText();
            String selectedJenisSampah = (String) TambahPermintaanController.this.view.jenisSampah.getSelectedItem();
            
            if (!namaPelangganInput.isEmpty() && !alamatInput.isEmpty() && !beratSampahInput.isEmpty() && selectedJenisSampah != null) {

                try {
                    TambahPermintaanController.this.mapper.tambahPermintaan(namaPelangganInput, alamatInput,selectedJenisSampah,Integer.parseInt(beratSampahInput));
                    TambahPermintaanController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil di-insert!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    TambahPermintaanController.this.view.setVisible(false);
                    this.permintaanController.loadKurirData();
                    this.permintaanController.view.setVisible(true);
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
