package controller;


import view.PointView;
import model.PenjemputanMapper;
import model.TotalPointInfo;
import view.MainView;
import view.HistoryView;
import view.LatestStatusView;
import org.apache.ibatis.session.SqlSession;

import javax.swing.SwingUtilities; // Tambahkan import untuk SwingUtilities
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainView view;
    private SqlSession session; // Simpan sesi yang diterima dari Main

    public MainController(MainView view, SqlSession session) {
        this.view = view;
        this.session = session;

        // Tambahkan listener ke tombol pada MainView
        this.view.addLihatPenjemputanListener(new ShowLatestStatus());
        this.view.addLihatHistoryListener(new ShowHistory());
        this.view.addLihatPointListener(new ShowPointInfo());
    }

    // Listener untuk tombol "Lihat Penjemputan"
    class ShowLatestStatus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                LatestStatusView penjemputanView = new LatestStatusView();
                PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);

                // Buat controller untuk LatestStatusView
                new LatestStatusController(penjemputanView, penjemputanMapper);

                // Pastikan tampilan ditampilkan di EDT
                SwingUtilities.invokeLater(() -> penjemputanView.setVisible(true));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Listener untuk tombol "Lihat History"
    class ShowHistory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                HistoryView historyView = new HistoryView();
                PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);

                // Buat controller untuk HistoryView
                new HistoryController(historyView, penjemputanMapper);

                // Pastikan tampilan ditampilkan di EDT
                SwingUtilities.invokeLater(() -> historyView.setVisible(true));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    // Listener untuk tombol "Lihat Point"
    class ShowPointInfo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                PointView pointView = new PointView();
                PenjemputanMapper mapper = session.getMapper(PenjemputanMapper.class);

                // Ambil data total berat dan poin
                TotalPointInfo totalInfo = mapper.getTotalBeratDanPointElektronik();
                if (totalInfo != null) {
                    pointView.setTotalBerat(totalInfo.getTotalBerat());
                    pointView.setTotalPoint(totalInfo.getTotalPoint());
                } else {
                    pointView.setTotalBerat(0);
                    pointView.setTotalPoint(0);
                }

                // Pastikan tampilan ditampilkan di EDT
                SwingUtilities.invokeLater(() -> pointView.setVisible(true));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
