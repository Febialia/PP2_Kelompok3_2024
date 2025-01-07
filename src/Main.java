import view.MainView;
import controller.MainController;
import javax.swing.table.DefaultTableModel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView view = new MainView();
            view.setVisible(true);
        });
    }
}