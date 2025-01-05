package controller;


import view.PointView;
import model.PenjemputanMapper;
import model.TotalPointInfo;
import view.MainView;
import view.PermintaanFormView;
import view.PermintaanView;
import view.HistoryView;
import view.LatestStatusView;
import model.PenjemputanMapper;
import model.Permintaan;
import model.PermintaanMapper;

import org.apache.ibatis.session.SqlSession;

import controller.MainController.EditPermintaanListener;
import controller.MainController.showHistory;
import controller.MainController.showLatestStatus;
import controller.MainController.showPermintaan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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

        // Add listener for "Lihat Penjemputan"
        this.view.addLihatPenjemputanListener(new showLatestStatus());
        this.view.addLihatHistoryListener(new showHistory());

        // Listener Permintaan 
        this.view.addLihatPermintaanListener(new showPermintaan());

        // Tambahkan listener untuk tombol Tambah dan Edit Permintaan
        this.view.addTambahPermintaanListener(new TambahPermintaanListener());
        this.view.addEditPermintaanListener(new EditPermintaanListener());
        this.session.commit();
    }

    class TambahPermintaanListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PermintaanFormView formView = new PermintaanFormView();
        formView.addSimpanListener(event -> {
            PermintaanMapper mapper = session.getMapper(PermintaanMapper.class);
            Permintaan permintaan = formView.getPermintaanFromForm();

            if (permintaan != null) {
                // Insert the new "Permintaan"
                mapper.insertPermintaan(permintaan);
                session.commit(); // Commit changes to the database
                JOptionPane.showMessageDialog(view, "Permintaan berhasil ditambahkan!");
                formView.dispose();
            }
        });
        formView.setVisible(true);
    }
}

     class EditPermintaanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputId = JOptionPane.showInputDialog(view, "Masukkan ID Permintaan untuk diubah:");
            if (inputId != null && !inputId.isEmpty()) {
                try {
                    int id = Integer.parseInt(inputId);
                    PermintaanMapper mapper = session.getMapper(PermintaanMapper.class);
                    Permintaan permintaan = mapper.getPermintaanById(id);

                    if (permintaan != null) {
                        PermintaanFormView formView = new PermintaanFormView(permintaan);
                        formView.addSimpanListener(event -> {
                            Permintaan updatedPermintaan = formView.getPermintaanFromForm();
                            updatedPermintaan.setId(id);
                            mapper.updatePermintaan(updatedPermintaan);
                            session.commit();
                            JOptionPane.showMessageDialog(view, "Permintaan berhasil diupdate!");
                            formView.dispose();
                        });
                        formView.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(view, "Data permintaan tidak ditemukan.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "ID tidak valid.");
                }
            }
        }
    }


    class showLatestStatus implements ActionListener {
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


    class showPermintaan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PermintaanView permintaanView = new PermintaanView();
            PermintaanMapper permintaanMapper = session.getMapper(PermintaanMapper.class);
            new PermintaanController(permintaanView, permintaanMapper);
            permintaanView.setVisible(true);
        }
    }
    
}
