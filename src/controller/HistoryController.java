package controller;

import model.Penjemputan;
import model.PenjemputanMapper;
import view.HistoryView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class HistoryController {
    private final HistoryView view;
    private final PenjemputanMapper mapper;

    public HistoryController(HistoryView view, PenjemputanMapper mapper) {
        this.view = view;
        this.mapper = mapper;
        initializeListeners();
        loadPenjemputanData();
    }

    private void initializeListeners() {
        // Listener untuk search field
        view.getSearchField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterData();
            }
        });

        // Listener untuk combo box filter
        view.getFilterCombo().addActionListener(e -> filterData());
    }

    private void loadPenjemputanData() {
        List<Penjemputan> penjemputanList = mapper.getCompleteHistory(); // Menggunakan getCompleteHistory untuk mendapatkan semua data
        if (penjemputanList.isEmpty()) {
            view.showMessage("Tidak ada data penjemputan tersedia.");
        }
        view.setTableData(penjemputanList);
    }

    private void filterData() {
    String searchText = view.getSearchField().getText().toLowerCase().trim();
    String selectedFilter = view.getSelectedFilter();
    List<Penjemputan> penjemputanList = mapper.getCompleteHistory();

    System.out.println("Search Text: " + searchText);
    System.out.println("Selected Filter: " + selectedFilter);

    List<Penjemputan> filteredList = penjemputanList.stream()
            .filter(p -> {
                // Filter berdasarkan text pencarian
                boolean searchMatch = searchText.isEmpty() || 
                    (p.getNamaKurir() != null && p.getNamaKurir().toLowerCase().contains(searchText)) ||
                    (p.getLokasi() != null && p.getLokasi().toLowerCase().contains(searchText)) ||
                    (p.getJenisSampah() != null && p.getJenisSampah().toLowerCase().contains(searchText)) ||
                    (p.getStatus() != null && p.getStatus().toLowerCase().contains(searchText));

                // Filter berdasarkan status
                boolean statusMatch = selectedFilter.equals("Semua Status") || 
                    (p.getStatus() != null && p.getStatus().equalsIgnoreCase(selectedFilter));

                System.out.println("Item: " + p + ", Search Match: " + searchMatch + ", Status Match: " + statusMatch);

                return searchMatch && statusMatch;
            })
            .toList();

    if (filteredList.isEmpty() && (!searchText.isEmpty() || !selectedFilter.equals("Semua Status"))) {
        view.showMessage("Tidak ada data yang sesuai dengan kriteria pencarian.");
    }

    view.setTableData(filteredList);
}

}
