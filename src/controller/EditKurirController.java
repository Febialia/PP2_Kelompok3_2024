package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import model.Kurir;
import model.KurirMapper;
import model.Penjemputan;

import org.apache.ibatis.session.SqlSession;

import controller.SidebarController.btnHome;
import controller.TambahPenjemputanController.addPenjemputan;
import model.Permintaan;
import model.PermintaanMapper;

import view.EditKurirView;
public class EditKurirController {
    public EditKurirView view;
    public KurirMapper mapper;
    public SqlSession session; 
    private List<Kurir> kurirList;

    public EditKurirController(EditKurirView view, KurirMapper mapper, SqlSession session, int id, KurirController kurirController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.view.editKurir(new editKurir(kurirController));
        this.view.deleteKurir(new deleteKurir(kurirController));
        this.kurirList = this.mapper.getKurirById(id);
        loadData();
        
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
    

    private void loadData() {
        Kurir kurir = kurirList.get(0);  

        // Set status ke ComboBox
        view.setNama(kurir.getNamaKurir());
    }

    class editKurir implements ActionListener {
        private KurirController kurirController;

        public editKurir(KurirController kurirController) {
            this.kurirController = kurirController;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengakses objek Kurir yang dipilih dari ComboBox
            String namaKurir = EditKurirController.this.view.namaKurir.getText();
            Kurir kurir = kurirList.get(0);  
            if (!namaKurir.isEmpty()) {
                try {
                    EditKurirController.this.mapper.editKurir(kurir.getId(),namaKurir);
                    EditKurirController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil di-update!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    EditKurirController.this.view.setVisible(false);
                    this.kurirController.loadKurirData();
                    this.kurirController.view.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();

                    // Menampilkan popup untuk error
                    JOptionPane.showMessageDialog(
                        null,
                        "Error saat meng-update data: " + ex.getMessage(),
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

    class deleteKurir implements ActionListener {
        private KurirController kurirController;

        public deleteKurir(KurirController kurirController) {
            this.kurirController = kurirController;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengakses objek Kurir yang dipilih dari ComboBox
            Kurir kurir = kurirList.get(0);  
                try {
                    EditKurirController.this.mapper.deleteKurir(kurir.getId());
                    EditKurirController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil di-delete!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    EditKurirController.this.view.setVisible(false);
                    this.kurirController.loadKurirData();
                    this.kurirController.view.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();

                    // Menampilkan popup untuk error
                    JOptionPane.showMessageDialog(
                        null,
                        "Error saat meng-delete data: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
        }
    }


}
