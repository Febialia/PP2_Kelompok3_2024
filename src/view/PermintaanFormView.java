package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;


import model.Permintaan;

public class PermintaanFormView extends JFrame {
    private JTextField txtNamaPelanggan;
    private JTextField txtAlamat;
    private JTextField txtJenisSampah;
    private JTextField txtBeratSampah;
    private JSpinner spnTanggalPenjemputan;
    private JButton btnSimpan;
    private JButton btnBatal;

    public PermintaanFormView() {
        this(null);
    }

    public PermintaanFormView(Permintaan permintaan) {
        setTitle(permintaan == null ? "Tambah Permintaan" : "Edit Permintaan");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("Nama Pelanggan:"));
        txtNamaPelanggan = new JTextField();
        formPanel.add(txtNamaPelanggan);

        formPanel.add(new JLabel("Alamat:"));
        txtAlamat = new JTextField();
        formPanel.add(txtAlamat);

        formPanel.add(new JLabel("Jenis Sampah:"));
        txtJenisSampah = new JTextField();
        formPanel.add(txtJenisSampah);

        formPanel.add(new JLabel("Berat Sampah:"));
        txtBeratSampah = new JTextField();
        formPanel.add(txtBeratSampah);

        formPanel.add(new JLabel("Tanggal Penjemputan:"));
        spnTanggalPenjemputan = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnTanggalPenjemputan, "yyyy-MM-dd");
        spnTanggalPenjemputan.setEditor(dateEditor);
        formPanel.add(spnTanggalPenjemputan);

        JPanel buttonPanel = new JPanel();
        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnBatal);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnBatal.addActionListener(e -> dispose());

        if (permintaan != null) {
            setForm(permintaan);
        }
    }

    public Permintaan getPermintaanFromForm() {
        String namaPelanggan = txtNamaPelanggan.getText();
        String alamat = txtAlamat.getText();
        String jenisSampah = txtJenisSampah.getText();
        String beratSampah = txtBeratSampah.getText();
        java.util.Date utilDate = (java.util.Date) spnTanggalPenjemputan.getValue();

        if (namaPelanggan.isEmpty() || alamat.isEmpty() || jenisSampah.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.");
            return null;
        }

        Permintaan permintaan = new Permintaan();
        permintaan.setNamaPelanggan(namaPelanggan);
        permintaan.setAlamat(alamat);
        permintaan.setJenisSampah(jenisSampah);
        permintaan.setBeratSampah(beratSampah);
        
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        permintaan.setTanggalPenjemputan(sqlDate);

        return permintaan;
    }

    private void setForm(Permintaan permintaan) {
        txtNamaPelanggan.setText(permintaan.getNamaPelanggan());
        txtAlamat.setText(permintaan.getAlamat());
        txtJenisSampah.setText(permintaan.getJenisSampah());
        txtBeratSampah.setText(permintaan.getBeratSampah());
        
        java.util.Date utilDate = new java.util.Date(permintaan.getTanggalPenjemputan().getTime());
        spnTanggalPenjemputan.setValue(utilDate);
    }

    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }
}