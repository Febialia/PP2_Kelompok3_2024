package model;

public class Kurir {
    private int id;
    private String namaKurir;  // Mengganti nama_kurir dengan namaKurir sesuai konvensi Java

    // Konstruktor default
    public Kurir() {}

    // Konstruktor dengan parameter
    public Kurir(int id, String namaKurir) {
        this.id = id;
        this.namaKurir = namaKurir;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    // toString untuk representasi string dari objek Kurir
    @Override
    public String toString() {
        return "Kurir [id=" + id + ", namaKurir=" + namaKurir + "]";  // Sesuaikan dengan variabel namaKurir
    }
}
