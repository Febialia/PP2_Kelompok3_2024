package controller;


import model.PenjemputanMapper;
import model.PermintaanMapper;
import view.MainView;
import view.HistoryView;
import view.LatestStatusView23;
import org.apache.ibatis.session.SqlSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.KurirMapper;

public class MainController {
    private MainView view;
    public SqlSession session; // Tambahkan field untuk session

    public MainController(MainView view, SqlSession session) {
        this.view = view;
        this.session = session; // Simpan session untuk digunakan nanti

        loadData();

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

   public void loadData(){
       KurirMapper kurirMapper = session.getMapper(KurirMapper.class);
       PermintaanMapper permintaanMapper = session.getMapper(PermintaanMapper.class);
       PenjemputanMapper penjemputanMapper = session.getMapper(PenjemputanMapper.class);
       this.view.setData(kurirMapper.total(), permintaanMapper.total(), penjemputanMapper.total());
   }

}
