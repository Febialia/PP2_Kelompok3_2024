package controller;
import java.awt.event.*;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import model.Penjemputan;
import model.PenjemputanMapper;
import model.Kurir;
import model.KurirMapper;
import view.EditKurirView;
import view.KurirPdf;
import view.StatusPenjemputanPdf;
import view.KurirView;
import view.TambahKurirView;
import view.TambahPenjemputanView;

public class KurirController {

    public KurirView view;
    public KurirMapper mapper;
    public SqlSession session; 

    public KurirController(KurirView view, KurirMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        loadKurirData();
        this.view.addKurir(new addKurir());
        this.view.editKurir(new editKurir());
        this.view.exportKurir(new ExportListener());
        
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

    public void loadKurirData() {
        List<Kurir> kurirList = mapper.getAllKurirs();
        view.setTableData(kurirList);
    }

    class addKurir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TambahKurirView addKurirView = new TambahKurirView();
            KurirMapper kurirMapper = session.getMapper(KurirMapper.class);
            new TambahKurirController(addKurirView, kurirMapper, session, KurirController.this);
            // Pastikan tampilan ditampilkan
            addKurirView.setVisible(true); 
            KurirController.this.view.setVisible(false);
        }
    }

    class editKurir implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            EditKurirView editKurirView = new EditKurirView();
            KurirMapper kurirMapper = session.getMapper(KurirMapper.class);
            
            // Get the selected row
            int selectedRow = KurirController.this.view.getTable().getSelectedRow();

            if (selectedRow != -1) {
                int id = (int) KurirController.this.view.getTableModel().getValueAt(selectedRow, 0);
                System.out.println(id);

                // Pass the LatestStatusController instance to editKurirController
                new EditKurirController(editKurirView, kurirMapper, session, id, KurirController.this);
                editKurirView.setVisible(true);
                KurirController.this.view.setVisible(false);
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
    
    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Kurir> kurirs = mapper.getAllKurirs();
            KurirPdf pdf = new KurirPdf();
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            pdf.exportPdf(kurirs, "kurir_"+ timeStamp);
            JOptionPane.showMessageDialog(view, "Data exported to PDF.");
        }
    }
}
