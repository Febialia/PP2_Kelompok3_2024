package view;

import javax.swing.table.DefaultTableModel;

public class mainPanelodel {
    private DefaultTableModel tableModel;

    public void model() {
        // Inisialisasi DefaultTableModel dengan kolom yang diinginkan
        tableModel = new DefaultTableModel(new Object[]{"Column 1", "Column 2"}, 0);
    }

    public void addRow(Object[] objects) {
        // Menambahkan baris baru ke model
        tableModel.addRow(objects);
    }

    public void removeRow(int selectedRow) {
        // Memastikan indeks yang diberikan valid
        if (selectedRow >= 0 && selectedRow < tableModel.getRowCount()) {
            tableModel.removeRow(selectedRow);
        } else {
            throw new IndexOutOfBoundsException("Invalid row index: " + selectedRow);
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}