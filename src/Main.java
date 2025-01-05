import model.MyBatisUtil;
import model.KurirMapper;
import model.PenjemputanMapper;
import org.apache.ibatis.session.SqlSession;
import view.MainView;
import controller.MainController;
import javax.swing.table.DefaultTableModel;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();

        MainView view = new MainView();
        new MainController(view, session);

        view.setVisible(true);
    }
}
