package controller;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import model.Kurir;

import org.apache.ibatis.session.SqlSession;

import model.Penjemputan;
import model.PenjemputanMapper;
import view.EditPenjemputanView;
import view.KurirPdf;
import view.LatestStatusView;
import view.StatusPenjemputanPdf;
import view.TambahPenjemputanView;
public class LatestStatusController {
    public LatestStatusView view;
    public PenjemputanMapper mapper;
    public SqlSession session; 

    public LatestStatusController(LatestStatusView view, PenjemputanMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.view.addTambahJemputan(new addJemputan());
        this.view.editPenjemputan(new editPenjemputan());
        this.view.exportPdf(new ExportListener());
        this.loadPenjemputanData();

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

    public void loadPenjemputanData() {
        List<Penjemputan> penjemputanList = mapper.getLatestStatus();
        view.setTableData(penjemputanList);
    }
    
    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Penjemputan> penjemputanList = mapper.getLatestStatus();
            StatusPenjemputanPdf pdf = new StatusPenjemputanPdf();
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            pdf.exportPdf(penjemputanList, "penjemputan_"+ timeStamp);
            JOptionPane.showMessageDialog(view, "Data exported to PDF.");
        }
    }

    class addJemputan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TambahPenjemputanView addPenjemputanView = new TambahPenjemputanView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            new TambahPenjemputanController(addPenjemputanView, penjemputanMapper, session, LatestStatusController.this);
            // Pastikan tampilan ditampilkan
            addPenjemputanView.setVisible(true); 
            LatestStatusController.this.view.setVisible(false);
        }
    }

    class editPenjemputan implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            EditPenjemputanView editPenjemputanView = new EditPenjemputanView();
            PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
            
            // Get the selected row
            int selectedRow = LatestStatusController.this.view.getTable().getSelectedRow();

            if (selectedRow != -1) {
                int id = (int) LatestStatusController.this.view.getTableModel().getValueAt(selectedRow, 0);
                System.out.println(id);

                // Pass the LatestStatusController instance to EditPenjemputanController
                new EditPenjemputanController(editPenjemputanView, penjemputanMapper, session, id, LatestStatusController.this);
                editPenjemputanView.setVisible(true);
                LatestStatusController.this.view.setVisible(false);
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
