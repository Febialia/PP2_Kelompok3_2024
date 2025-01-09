package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import model.Kurir;
import model.KurirMapper;
import model.Penjemputan;

import org.apache.ibatis.session.SqlSession;

import controller.EditPenjemputanController.deletePenjemputan;
import controller.SidebarController.btnHome;
import controller.TambahPenjemputanController.addPenjemputan;
import model.Permintaan;
import model.PermintaanMapper;
import view.EditPermintaanView;

public class EditPermintaanController {
    public EditPermintaanView view;
    public PermintaanMapper mapper;
    private List<Permintaan> permintaanList;
    public SqlSession session; 

    public EditPermintaanController(EditPermintaanView view, PermintaanMapper mapper, SqlSession session, int id, PermintaanController permintaanController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        this.permintaanList = this.mapper.getPermintaan(id);

        loadData();

        this.view.editPermintaan(new editPermintaan(permintaanController)); 
        this.view.deletePermintaan(new deletePermintaan(permintaanController));

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
        Permintaan permintaan = permintaanList.get(0);  
        // Set status ke ComboBox
        view.setNama(permintaan.getNamaPelanggan());
        view.setAlamat(permintaan.getAlamat());
        view.setBeratSampah(Integer.toString(permintaan.getBeratSampah()));
        view.setJenis(permintaan.getJenisSampah());
    }

    class editPermintaan implements ActionListener {
        private PermintaanController permintaanController;

        public editPermintaan(PermintaanController permintaanController) {
            this.permintaanController = permintaanController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengakses objek Kurir yang dipilih dari ComboBox
            Permintaan permintaan = permintaanList.get(0);  
            String namaPelangganInput = EditPermintaanController.this.view.namaPelanggan.getText();
            String alamatInput = EditPermintaanController.this.view.alamat.getText();
            String beratSampahInput = EditPermintaanController.this.view.beratSampah.getText();
            String selectedJenisSampah = (String) EditPermintaanController.this.view.jenisSampah.getSelectedItem();
            
            if (!namaPelangganInput.isEmpty() && !alamatInput.isEmpty() && !beratSampahInput.isEmpty() && selectedJenisSampah != null) {

                try {
                    EditPermintaanController.this.mapper.editPermintaan(permintaan.getId(),namaPelangganInput, alamatInput,selectedJenisSampah,Integer.parseInt(beratSampahInput));
                    EditPermintaanController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil di-update!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    EditPermintaanController.this.view.setVisible(false);
                    this.permintaanController.loadKurirData();
                    this.permintaanController.view.setVisible(true);
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

    class deletePermintaan implements ActionListener {
        private PermintaanController permintaanController;

        public deletePermintaan(PermintaanController permintaanController) {
            this.permintaanController = permintaanController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Permintaan permintaan = permintaanList.get(0);  
                try {
                    EditPermintaanController.this.mapper.delelePermintaan(permintaan.getId());
                    EditPermintaanController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil di-hapus!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    EditPermintaanController.this.view.setVisible(false);
                    this.permintaanController.loadKurirData();
                    this.permintaanController.view.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();

                    // Menampilkan popup untuk error
                    JOptionPane.showMessageDialog(
                        null,
                        "Error saat meng-hapus data: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
        }
    }

}
