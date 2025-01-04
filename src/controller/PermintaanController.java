package controller;

import view.PermintaanView;
import model.PermintaanMapper;
import model.Permintaan;
import java.util.List;

import javax.swing.JOptionPane;

public class PermintaanController {
    private PermintaanView view;
    private PermintaanMapper mapper;
    
    public PermintaanController(PermintaanView view, PermintaanMapper mapper) {
        this.view = view;
        this.mapper = mapper;
        loadPermintaanData();

        view.addKembaliListener(e -> {
            view.dispose();
        });

        view.addHapusListener(e -> {
        int id = view.getSelectedId();
        if (id != -1) {
            int confirm = JOptionPane.showConfirmDialog(view, 
                "Hapus data ini?", "Konfirmasi", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                mapper.deletePermintaan(id);
                loadPermintaanData();
            }
        } else {
            JOptionPane.showMessageDialog(view, 
                "Pilih data yang akan dihapus");
        }
        
    });
    
    loadPermintaanData();

    }
    
    private void loadPermintaanData() {
        List<Permintaan> permintaanList = mapper.getAllPermintaan();
        Object[][] data = new Object[permintaanList.size()][6];
        
        for (int i = 0; i < permintaanList.size(); i++) {
            Permintaan p = permintaanList.get(i);
            data[i] = new Object[]{
                p.getId(),
                p.getNamaPelanggan(),
                p.getAlamat(),
                p.getJenisSampah(),
                p.getBeratSampah(),
                p.getTanggalPenjemputan()
            };
        }
        
        view.setTableData(data);
    }

    
}