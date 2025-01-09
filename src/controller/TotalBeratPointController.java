package controller;
import java.awt.event.*;
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
import view.TotalBeratPointView;

public class TotalBeratPointController {

    public TotalBeratPointView view;
    public PenjemputanMapper mapper;
    public SqlSession session; 

    public TotalBeratPointController(TotalBeratPointView view, PenjemputanMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        loadPenjemputanData();

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

}
