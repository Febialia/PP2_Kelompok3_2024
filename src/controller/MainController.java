package controller;


import model.PenjemputanMapper;
import view.MainView;
import view.HistoryView;
import view.LatestStatusView;
import model.PenjemputanMapper;
import org.apache.ibatis.session.SqlSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainView view;
    private SqlSession session; // Tambahkan field untuk session

    public MainController(MainView view, SqlSession session) {
        this.view = view;
        this.session = session; // Simpan session untuk digunakan nanti

        // Add listener for "Lihat Penjemputan"
        this.view.addLihatPenjemputanListener(new showLatestStatus());
        this.view.addLihatHistoryListener(new showHistory());
       
    }

    class showLatestStatus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LatestStatusView penjemputanView = new LatestStatusView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new LatestStatusController(penjemputanView, penjemputanMapper);
            
            // Pastikan tampilan ditampilkan
            penjemputanView.setVisible(true); 
        }
    }

    class showHistory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HistoryView penjemputanView = new HistoryView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new HistoryController(penjemputanView, penjemputanMapper);
            
            // Pastikan tampilan ditampilkan
            penjemputanView.setVisible(true); 
        }
    }

}
