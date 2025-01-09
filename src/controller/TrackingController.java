package controller;
import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import model.Penjemputan;
import model.PenjemputanMapper;
import model.Permintaan;
import model.PenjemputanMapper;
import model.TrackingMapper;
import view.DetailTrackingView;
import view.TrackingView;
import view.KurirView;
import view.TambahKurirView;
import view.TambahPenjemputanView;

public class TrackingController {

    public TrackingView view;
    public PenjemputanMapper mapper;
    public SqlSession session; 

    public TrackingController(TrackingView view, PenjemputanMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        loadData();
        this.view.detailTracking(new detailTracking());

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
        List<Penjemputan> penjemputanList = mapper.getTrackingList();
        view.setTableData(penjemputanList);
    }

    class detailTracking implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            DetailTrackingView detailTrackingView = new DetailTrackingView();
            TrackingMapper trackingMapper = session.getMapper(TrackingMapper.class);
            
            // Get the selected row
            int selectedRow = TrackingController.this.view.getTable().getSelectedRow();

            if (selectedRow != -1) {
                int id = (int) TrackingController.this.view.getTableModel().getValueAt(selectedRow, 0);
                System.out.println(id);

                // Pass the LatestStatusController instance to editPermintaanController
                new DetailTrackingController(detailTrackingView, trackingMapper, session, id, TrackingController.this);
                detailTrackingView.setVisible(true);
                TrackingController.this.view.setVisible(false);
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
