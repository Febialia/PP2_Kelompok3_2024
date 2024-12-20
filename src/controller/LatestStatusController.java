package controller;

import java.util.List;
import model.Penjemputan;
import model.PenjemputanMapper;
import view.LatestStatusView;

public class LatestStatusController {
    private LatestStatusView view;
    private PenjemputanMapper mapper;

    public LatestStatusController(LatestStatusView view, PenjemputanMapper mapper) {
        this.view = view;
        this.mapper = mapper;
        loadPenjemputanData();
    }

    private void loadPenjemputanData() {
        List<Penjemputan> penjemputanList = mapper.getLatestStatus();
        view.setTableData(penjemputanList);
    }
}
