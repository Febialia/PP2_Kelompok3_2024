package controller;

import java.util.List;
import model.Penjemputan;
import model.PenjemputanMapper;
import view.HistoryView;

public class HistoryController {
    private HistoryView view;
    private PenjemputanMapper mapper;

    public HistoryController(HistoryView view, PenjemputanMapper mapper) {
        this.view = view;
        this.mapper = mapper;
        loadPenjemputanData();
    }

    private void loadPenjemputanData() {
        List<Penjemputan> penjemputanList = mapper.getHistory();
        view.setTableData(penjemputanList);
    }
}
