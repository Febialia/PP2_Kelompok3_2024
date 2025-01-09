package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import model.Kurir;
import model.KurirMapper;
import model.Penjemputan;
import model.PenjemputanMapper;
import model.Permintaan;

import org.apache.ibatis.session.SqlSession;

import controller.EditPenjemputanController.deletePenjemputan;
import controller.SidebarController.btnHome;
import controller.TambahPenjemputanController.addPenjemputan;
import model.Tracking;
import model.TrackingMapper;
import model.PermintaanMapper;
import view.DetailTrackingView;

public class DetailTrackingController {
    public DetailTrackingView view;
    public TrackingMapper mapper;
    private List<Tracking> trackingList;
    public SqlSession session; 
    public int id;

    public DetailTrackingController(DetailTrackingView view, TrackingMapper mapper, SqlSession session, int id, TrackingController trackingController) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.id = id;
        
        this.trackingList = this.mapper.getTracking(id);

        loadData();

        this.view.addLokasi(new addLokasi(trackingController));
        this.view.deleteLokasi(new deleteLokasi(trackingController));
        
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
    
    

    public void loadData() {
        // Kosongkan tabel terlebih dahulu
        view.clearTable();
    
        // Ambil data pertama untuk ditampilkan pada detail
        Tracking tracking = trackingList.get(0);  
    
        // Update tabel dengan data baru
        view.setTableData(trackingList);
    
        // Update detail
        view.setDetail(tracking.getIdPenjemputan(), tracking.getNamaPelanggan(), tracking.getNamaKurir());
    }

    class addLokasi implements ActionListener {
  
        private TrackingController trackingController;
        
        public addLokasi(TrackingController trackingController) {
            this.trackingController = trackingController;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengakses objek Kurir yang dipilih dari ComboBox  
            String lokasiTracking = DetailTrackingController.this.view.lokasiTerakhir.getText();
        
            if (!lokasiTracking.isEmpty()) {
                try {
                    DetailTrackingController.this.mapper.addTracking(DetailTrackingController.this.id, lokasiTracking);
                    DetailTrackingController.this.session.commit();

                    // Menampilkan popup untuk sukses
                    JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil di-insert!",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    DetailTrackingController.this.view.setVisible(false);
                    this.trackingController.loadData();
                    this.trackingController.view.setVisible(true);
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


    class deleteLokasi implements MouseListener {
        private TrackingController trackingController;
    
        public deleteLokasi(TrackingController trackingController) {
            this.trackingController = trackingController;
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
            DetailTrackingView detailTrackingView = new DetailTrackingView();
            TrackingMapper trackingMapper = session.getMapper(TrackingMapper.class);
    
            // Get the selected row
            int selectedRow = DetailTrackingController.this.view.getTable().getSelectedRow();
    
            if (selectedRow != -1) {
                int id = (int) DetailTrackingController.this.view.getTableModel().getValueAt(selectedRow, 0);
                System.out.println(id);
    
                // Menampilkan dialog konfirmasi
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda yakin ingin menghapus data ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
    
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        DetailTrackingController.this.mapper.deleteTracking(id);
                        DetailTrackingController.this.session.commit();
    
                        // Menampilkan popup untuk sukses
                        JOptionPane.showMessageDialog(
                            null,
                            "Data berhasil di-delete!",
                            "Berhasil",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                        DetailTrackingController.this.view.setVisible(false);
                        this.trackingController.loadData();
                        this.trackingController.view.setVisible(true);
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
    
        @Override
        public void mousePressed(MouseEvent e) {}
    
        @Override
        public void mouseReleased(MouseEvent e) {}
    
        @Override
        public void mouseEntered(MouseEvent e) {}
    
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    

}
