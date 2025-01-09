package controller;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserListener;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import model.Penjemputan;
import model.PenjemputanMapper;
import model.Kurir;
import model.KurirMapper;
import view.EditKurirView;
import view.KurirView;
import view.RiwayatView;
import view.TambahKurirView;
import view.TambahPenjemputanView;

public class RiwayatController {

    public RiwayatView view;
    public PenjemputanMapper mapper;
    public SqlSession session; 

    public RiwayatController(RiwayatView view, PenjemputanMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        loadPenjemputanData();
        
            
        this.view.setTanggal(new SetTanggal());
        this.view.setStatus(new SetStatus());
        this.view.setSearch(new SetSearch());
        
        
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
        List<Penjemputan> penjemputanList = mapper.getHistory();
        view.setTableData(penjemputanList);
    }

    class SetTanggal implements DateChooserListener{

        @Override
        public void dateChanged(Date date, DateChooserAction action) {
        }      

        @Override
        public void dateBetweenChanged(DateBetween db, DateChooserAction action) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = df.format(db.getFromDate());
                String dateTo = df.format(db.getToDate());
                List<Penjemputan> penjemputanList = RiwayatController.this.mapper.getHistoryByDate(dateFrom , dateTo);
                view.setTableData(penjemputanList);
        }
    }
    
    class SetStatus implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            String status =  view.getStatus();
            List<Penjemputan> penjemputanList = RiwayatController.this.mapper.getHistoryByStatus(status);
            view.setTableData(penjemputanList);
        }
        
    }
    
    
    class SetSearch implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            String search =  view.getSearch();
            List<Penjemputan> penjemputanList = RiwayatController.this.mapper.getHistoryBySearch(search);
            view.setTableData(penjemputanList);
        }
        
    }
}
