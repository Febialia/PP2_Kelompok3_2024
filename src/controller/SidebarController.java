package controller;

import java.awt.event.*;
import javax.swing.JFrame;
import model.KurirMapper;
import model.MyBatisUtil;
import model.PenjemputanMapper;
import model.PermintaanMapper;
import view.LatestStatusView;
import view.MainView;

import org.apache.ibatis.session.SqlSession;
import view.KurirView;
import view.PermintaanView;
import view.RiwayatView;
import view.TotalBeratPointView;
import view.TrackingView;

public class SidebarController {

    public JFrame view; // Perbaiki tipe properti
    public SqlSession session;
    public SidebarController(JFrame view, SqlSession session) {
        this.view = view;
        this.session = session;
    }

    public class btnHome implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            SidebarController.this.view.setVisible(false);
            SqlSession session = MyBatisUtil.getSqlSession();

            MainView view = new MainView();
            new MainController(view, session);
            view.setVisible(true);
        }
    }
    
    public class btnStatusPenjemputan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LatestStatusView penjemputanView = new LatestStatusView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new LatestStatusController(penjemputanView, penjemputanMapper, session);
            // Pastikan tampilan ditampilkan
            penjemputanView.setVisible(true); 
            SidebarController.this.view.setVisible(false);
        }
    }

    public class btnKurir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            KurirView kurirView = new KurirView();
            KurirMapper kurirMapper = session.getMapper(KurirMapper.class);
            new KurirController(kurirView, kurirMapper, session);
            // Pastikan tampilan ditampilkan
            kurirView.setVisible(true); 
            SidebarController.this.view.setVisible(false);
        }
    }
    public class btnRiwayat implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RiwayatView riwayatView = new RiwayatView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new RiwayatController(riwayatView, penjemputanMapper, session);
            riwayatView.setVisible(true); 
            SidebarController.this.view.setVisible(false);
        }
    }
    
    public class btnBeratPoint implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TotalBeratPointView totalBeratPointView = new TotalBeratPointView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new TotalBeratPointController(totalBeratPointView, penjemputanMapper, session);
            totalBeratPointView.setVisible(true); 
            SidebarController.this.view.setVisible(false);
        }
    }
    public class btnPermintaan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PermintaanView permintaanView = new PermintaanView();
            PermintaanMapper permintaanMapper = session.getMapper(PermintaanMapper.class);
            new PermintaanController(permintaanView, permintaanMapper, session);
            permintaanView.setVisible(true); 
            SidebarController.this.view.setVisible(false);
        }
    }
    public class btnTracking implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrackingView trackingView = new TrackingView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new TrackingController(trackingView, penjemputanMapper, session);
            trackingView.setVisible(true); 
            SidebarController.this.view.setVisible(false);
        }
    }
}
