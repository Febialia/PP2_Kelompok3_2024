package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import controller.KurirController;

public class MainView extends JFrame {
    private JButton btnTambahKurir = new JButton("Tambah Kurir");
    private JButton btnTambahPermintaan = new JButton("Tambah Permintaan");
    private JButton btnTambahPenjemputan = new JButton("Tambah Penjemputan");
    private JButton btnTambahTracking = new JButton("Tambah Tracking");

    private JButton btnEditKurir = new JButton("Edit Kurir");
    private JButton btnEditPermintaan = new JButton("Edit Permintaan");
    private JButton btnEditPenjemputan = new JButton("Edit Penjemputan");
    private JButton btnEditTracking = new JButton("Edit Tracking");

    private JButton btnLihatPenjemputan = new JButton("Lihat Penjemputan");
    private JButton btnPermintaan = new JButton("Lihat Permintaan");
    private JButton btnLihatHistory = new JButton("Lihat History");
    private JButton btnLihatPoint = new JButton("Lihat Point");

    public MainView() {
        setTitle("Menu Utama");
        setSize(300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


         // Tombol "Tambah"
        panel.add(createButtonGroup("Tambah", btnTambahKurir, btnTambahPermintaan, btnTambahPenjemputan, btnTambahTracking));
        btnTambahKurir.addActionListener(e -> {
            KurirController controller = new KurirController();
            controller.handleTambahKurir();
        });

        // Tombol "Edit"
        panel.add(createButtonGroup("Edit", btnEditKurir, btnEditPermintaan, btnEditPenjemputan, btnEditTracking));

        panel.add(createButtonGroup("Lihat", btnLihatPenjemputan, btnPermintaan, btnLihatHistory, btnLihatPoint));

        btnEditKurir.addActionListener(e -> {
            KurirController controller = new KurirController();
            controller.handleEditKurir();
        });

        panel.add(createButtonGroup("Lihat", btnLihatPenjemputan, btnLihatHistory, btnLihatPoint));
        btnLihatPenjemputan.addActionListener(e -> {
            LatestStatusView latestStatusView = new LatestStatusView();
            latestStatusView.setVisible(true);
        });

        btnLihatHistory.addActionListener(e -> {
            HistoryView historyView = new HistoryView();
            historyView.setVisible(true);
        });



        // Menambahkan panel ke frame
        add(panel);
    }

     private JPanel createButtonGroup(String groupTitle, JButton... buttons) {
        JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
        groupPanel.setBorder(BorderFactory.createTitledBorder(groupTitle));
        
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
            groupPanel.add(button);
            groupPanel.add(Box.createVerticalStrut(5));
        }

        return groupPanel;
    }


    public void addTambahKurirListener(ActionListener listener) {
        btnTambahKurir.addActionListener(listener);
    }

    public void addTambahPermintaanListener(ActionListener listener) {
        btnTambahPermintaan.addActionListener(listener);
    }

    public void addTambahPenjemputanListener(ActionListener listener) {
        btnTambahPenjemputan.addActionListener(listener);
    }

    public void addTambahTrackingListenser(ActionListener listener) {
        btnTambahTracking.addActionListener(listener);
    }

    public void addEditKurirListener(ActionListener listener) {
        btnEditKurir.addActionListener(listener);
    }

    public void addEditPermintaanListener(ActionListener listener) {
        btnEditPermintaan.addActionListener(listener);
    }

    public void addEditPenjemputanListener(ActionListener listener) {
        btnEditPenjemputan.addActionListener(listener);
    }

    public void addEditTrackingListener(ActionListener listener) {
        btnEditTracking.addActionListener(listener);
    }

    public void addLihatPenjemputanListener(ActionListener listener) {
        btnLihatPenjemputan.addActionListener(listener);
    }

    public void addLihatPermintaanListener(ActionListener listener) {
        btnPermintaan.addActionListener(listener);
    }

    public void addLihatHistoryListener(ActionListener listener) {
        btnLihatHistory.addActionListener(listener);
    }

    public void addLihatPointListener(ActionListener listener) {
        btnLihatPoint.addActionListener(listener);
    }
}
