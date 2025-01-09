package model;

public class Tracking {
    private int id;
    private int idPenjemputan;
    private String lokasi;
    private String namaPelanggan;
    private String namaKurir;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setIdPenjemputan(int idPenjemputan) {
        this.idPenjemputan = idPenjemputan;
    }

    public int getIdPenjemputan() {
        return idPenjemputan;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    public String getNamaKurir() {
        return namaKurir;
    }
}