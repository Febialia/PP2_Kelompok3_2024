
package view;

import model.KurirMapper;
import model.Kurir;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;

public class FormTambahKurir extends JFrame {
    private JTextField namaField;
    private KurirMapper mapper;
    private SqlSession session;

    public FormTambahKurir(KurirMapper mapper, SqlSession session) {
        this.mapper = mapper;
        this.session = session;
        setTitle("Tambah Kurir");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel namaLabel = new JLabel("Nama Kurir:");
        namaField = new JTextField();

        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> saveKurir());

        add(namaLabel);
        add(namaField);
        add(saveButton);
    }

    private void saveKurir() {
        String namaKurir = namaField.getText().trim();
        if (namaKurir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama kurir tidak boleh kosong!");
        } else {
            try {
                Kurir kurir = new Kurir();
                kurir.setNamaKurir(namaKurir);
                mapper.insertKurir(kurir);
                session.commit();
                JOptionPane.showMessageDialog(this, "Kurir berhasil ditambahkan!");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan kurir!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

