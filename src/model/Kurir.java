package model;

public class Kurir {
    private int id;
    private String nama_kurir;

    // Konstruktor
    public Kurir(int id, String nama_kurir) {
        this.id = id;
        this.nama_kurir = nama_kurir;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKurir() {
        return nama_kurir;
    }

    public void setNamaKurir(String nama_kurir) {
        this.nama_kurir = nama_kurir;
    }

    // toString untuk representasi string dari objek Kurir
    @Override
    public String toString() {
        return "Kurir [id=" + id + ", nama_kurir=" + nama_kurir + "]";
    }
}