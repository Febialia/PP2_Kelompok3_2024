package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import model.Kurir;
import model.KurirMapper;
import model.Penjemputan;

import org.apache.ibatis.session.SqlSession;

import controller.SidebarController.btnHome;
import model.PenjemputanMapper;
import model.Permintaan;
import model.PermintaanMapper;
import model.TrackingMapper;
import view.TambahPenjemputanView;
public class TambahPenjemputanController {
    public TambahPenjemputanView view;
    public PenjemputanMapper mapper;
    public SqlSession session; 

    public TambahPenjemputanController(TambahPenjemputanView view, PenjemputanMapper mapper, SqlSession session, LatestStatusController latestStatusController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        loadKurirData();
        loadPermintaan();
        this.view.addPenjemputan(new addPenjemputan(latestStatusController));

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
        view.setKurir(kurirList);
    }
    private void loadPermintaan() {
        PermintaanMapper PermintaanMapper = session.getMapper(PermintaanMapper.class);
        List<Permintaan> permintaanList = PermintaanMapper.getPermintaanPickup();
        view.setPermintaan(permintaanList);
    }
    
    class addPenjemputan implements ActionListener {
        private LatestStatusController latestStatusController;

        public addPenjemputan(LatestStatusController latestStatusController) {
            this.latestStatusController = latestStatusController;
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mengakses objek Kurir yang dipilih dari ComboBox
        Kurir selectedKurir = (Kurir) TambahPenjemputanController.this.view.kurir.getSelectedItem();
        Permintaan selectedPermintaan = (Permintaan) TambahPenjemputanController.this.view.permintaan.getSelectedItem();
        String selectedStatus = (String) TambahPenjemputanController.this.view.status.getSelectedItem();
        String selectedPoint = TambahPenjemputanController.this.view.point.getText();
        
        if (selectedKurir != null && selectedPermintaan != null && selectedStatus != null && !selectedPoint.isEmpty()) {
            int idKurir = selectedKurir.getId();  // Mendapatkan ID dari Kurir
            int idPermintaan = selectedPermintaan.getId();  
            String status = selectedStatus;
            int point = Integer.parseInt(selectedPoint);
            try {
                
                TambahPenjemputanController.this.mapper.tambahPenjemputan(
                    idKurir,
                    idPermintaan,
                    status,
                    point
                );
                TambahPenjemputanController.this.session.commit();



                // Jika status "Dalam Perjalanan", insert ke tabel tracking
                if (status.equals("Dalam Perjalanan")) {
                    List<Penjemputan> penjemputanLast = TambahPenjemputanController.this.mapper.getLastRow(idKurir, idPermintaan);
                    Penjemputan penjemputan = penjemputanLast.get(0); 
                    TrackingMapper trackingMapper = session.getMapper(TrackingMapper.class);
                    trackingMapper.addTracking(penjemputan.getId(), "Belum Diambil");
                    TambahPenjemputanController.this.session.commit();
                }

                // Menampilkan popup untuk sukses
                JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil di-insert!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
                );
                TambahPenjemputanController.this.view.setVisible(false);
                this.latestStatusController.loadPenjemputanData();
                this.latestStatusController.view.setVisible(true);
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
