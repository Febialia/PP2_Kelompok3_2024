package view;

import javax.swing.*;
import java.awt.*;
import model.PenjemputanMapper;
import model.TotalPointInfo;
import org.apache.ibatis.session.SqlSession;
import model.MyBatisUtil;

public class PointView extends JFrame {
    private JLabel totalBeratLabel = new JLabel("Total Berat Elektronik: ");
    private JLabel totalPointLabel = new JLabel("Total Poin Elektronik: ");

    public PointView() {
        setTitle("Lihat Total Berat dan Poin");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(totalBeratLabel);
        panel.add(totalPointLabel);

        add(panel);

        // Panggil metode untuk menampilkan data
        tampilkanTotalBeratDanPoin();
    }

    private void tampilkanTotalBeratDanPoin() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            PenjemputanMapper mapper = session.getMapper(PenjemputanMapper.class);
            TotalPointInfo info = mapper.getTotalBeratDanPointElektronik();

            if (info != null) {
                setTotalBerat(info.getTotalBerat());
                setTotalPoint(info.getTotalPoint());
            } else {
                totalBeratLabel.setText("Total Berat Elektronik: 0 kg");
                totalPointLabel.setText("Total Poin Elektronik: 0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mengambil data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setTotalBerat(double berat) {
        totalBeratLabel.setText("Total Berat Elektronik: " + berat + " kg");
    }

    public void setTotalPoint(int point) {
        totalPointLabel.setText("Total Poin Elektronik: " + point);
    }
}
