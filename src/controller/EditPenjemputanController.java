package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import model.Kurir;
import model.KurirMapper;
import model.Penjemputan;

import org.apache.ibatis.session.SqlSession;

import model.PenjemputanMapper;
import model.Permintaan;
import model.PermintaanMapper;
import view.EditPenjemputanView;
public class EditPenjemputanController {
    public EditPenjemputanView view;
    public PenjemputanMapper mapper;
    private List<Penjemputan> penjemputanList;
    public SqlSession session; 

    public EditPenjemputanController(EditPenjemputanView view, PenjemputanMapper mapper, SqlSession session, int id, LatestStatusController latestStatusController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.penjemputanList = EditPenjemputanController.this.mapper.getPenjemputan(id);
        loadKurirData();
        loadPermintaan();
        loadStatusPoint();
        this.view.editPenjemputan(new editPenjemputan(latestStatusController)); // Pass the controller instance
        this.view.deletePenjemputan(new deletePenjemputan(latestStatusController)); // Pass the controller instance

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

    private void loadKurirData() {
        KurirMapper kurirMapper = session.getMapper(KurirMapper.class);
        List<Kurir> kurirList = kurirMapper.getAllKurirs();
        Penjemputan penjemputan = penjemputanList.get(0);  
        view.setKurir(kurirList, penjemputan.getIdKurir());
    }
    private void loadPermintaan() {
        PermintaanMapper PermintaanMapper = session.getMapper(PermintaanMapper.class);
        Penjemputan penjemputan = penjemputanList.get(0);  
        List<Permintaan> permintaanList = PermintaanMapper.getPermintaan(penjemputan.getIdPermintaan());
        Permintaan permintaan = permintaanList.get(0);  
        this.view.setPermintaan(permintaan.getNamaPelanggan() +" - "+ permintaan.getAlamat());
    }
    
    private void loadStatusPoint() {
        Penjemputan penjemputan = penjemputanList.get(0);  
        System.out.println(penjemputan.getStatus());
        // Set status ke ComboBox
        view.setStatus(penjemputan.getStatus());
        
        // Set point ke field text, misalnya jika Point diambil dari objek Penjemputan
        view.setPoint(penjemputan.getPoint());
    }
    
    class editPenjemputan implements ActionListener {
            private LatestStatusController latestStatusController;

            public editPenjemputan(LatestStatusController latestStatusController) {
                this.latestStatusController = latestStatusController;
            }
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengakses objek Kurir yang dipilih dari ComboBox
            Kurir selectedKurir = (Kurir) EditPenjemputanController.this.view.kurir.getSelectedItem();
            String selectedStatus = (String) EditPenjemputanController.this.view.status.getSelectedItem();
            String selectedPoint = EditPenjemputanController.this.view.point.getText();
            Penjemputan penjemputan = penjemputanList.get(0);  
            if (selectedKurir != null  && selectedStatus != null && !selectedPoint.isEmpty()) {
                int idKurir = selectedKurir.getId();  // Mendapatkan ID dari Kurir
                int idPermintaan = selectedKurir.getId();  
                String status = selectedStatus;
                int point = Integer.parseInt(selectedPoint);
                
                try {
                    EditPenjemputanController.this.mapper.editPenjemputan(
                        penjemputan.getId(),
                        idKurir,
                        status,
                        point
                    );
                    EditPenjemputanController.this.session.commit();

                    // Success message
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil diupdate!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    EditPenjemputanController.this.view.setVisible(false); 
                    latestStatusController.loadPenjemputanData();
                            this.latestStatusController.view.setVisible(true); // Now it works

                } catch (Exception ex) {
                    ex.printStackTrace();

                    // Menampilkan popup untuk error
                    JOptionPane.showMessageDialog(
                        null,
                        "Error saat mengupdate data: " + ex.getMessage(),
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
   
   
    class deletePenjemputan implements ActionListener {
        private LatestStatusController latestStatusController;

        public deletePenjemputan(LatestStatusController latestStatusController) {
            this.latestStatusController = latestStatusController;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            Penjemputan penjemputan = penjemputanList.get(0);  

                try {
                    EditPenjemputanController.this.mapper.deletePenjemputan(penjemputan.getId());
                    EditPenjemputanController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil dihapus!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    EditPenjemputanController.this.view.setVisible(false); 
                    latestStatusController.loadPenjemputanData();
                              this.latestStatusController.view.setVisible(true); 
                } catch (Exception ex) {
                    ex.printStackTrace();

                    // Menampilkan popup untuk error
                    JOptionPane.showMessageDialog(
                        null,
                        "Error saat mengupdate data: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
        }
    }
    
}
