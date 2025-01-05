package controller;

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
    private SqlSession session; // Tambahkan field untuk session

    public MainController(MainView view, SqlSession session) {
        this.view = view;
        this.session = session; // Simpan session untuk digunakan nanti

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
