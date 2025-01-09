package controller;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import model.Kurir;

import org.apache.ibatis.session.SqlSession;

import model.Penjemputan;
import model.PenjemputanMapper;
import model.Permintaan;
import model.PermintaanMapper;
import view.EditPermintaanView;
import view.KurirPdf;
import view.PermintaanView;
import view.KurirView;
import view.PermintaanPdf;
import view.TambahKurirView;
import view.TambahPenjemputanView;
import view.TambahPermintaanView;

public class PermintaanController {

    public PermintaanView view;
    public PermintaanMapper mapper;
    public SqlSession session; 

    public PermintaanController(PermintaanView view, PermintaanMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        loadKurirData();
        this.view.addPermintaan(new addPermintaan());
        this.view.editPermintaan(new editPermintaan());
        this.view.exportPdf(new ExportListener());

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
        List<Permintaan> permintaanList = mapper.getAllpermintaan();
        view.setTableData(permintaanList);
    }

    class addPermintaan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TambahPermintaanView addPermintaanView = new TambahPermintaanView();
            PermintaanMapper permintaanMapper = session.getMapper(PermintaanMapper.class);
            new TambahPermintaanController(addPermintaanView, permintaanMapper, session, PermintaanController.this);
            // Pastikan tampilan ditampilkan
            addPermintaanView.setVisible(true); 
            PermintaanController.this.view.setVisible(false);
        }
    }

    class editPermintaan implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            EditPermintaanView editPermintaanView = new EditPermintaanView();
            PermintaanMapper permintaanMapper = session.getMapper(PermintaanMapper.class);
            
            // Get the selected row
            int selectedRow = PermintaanController.this.view.getTable().getSelectedRow();

            if (selectedRow != -1) {
                int id = (int) PermintaanController.this.view.getTableModel().getValueAt(selectedRow, 0);
                System.out.println(id);

                // Pass the LatestStatusController instance to editPermintaanController
                new EditPermintaanController(editPermintaanView, permintaanMapper, session, id, PermintaanController.this);
                editPermintaanView.setVisible(true);
                PermintaanController.this.view.setVisible(false);
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
            List<Permintaan> permintaanList = mapper.getAllpermintaan();
            PermintaanPdf pdf = new PermintaanPdf();
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            pdf.exportPdf(permintaanList, "permintaan_"+ timeStamp);
            JOptionPane.showMessageDialog(view, "Data exported to PDF.");
        }
    }
}
