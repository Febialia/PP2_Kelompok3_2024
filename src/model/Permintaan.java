package model;

public class Permintaan {
    private int id;
    private String nama_pelanggan;  // Gunakan java.sql.Timestamp jika perlu
    private String alamat;
    private String jenis_sampah;
    private int berat_sampah;
    private String tanggal_permintaan;
    private String status;
    private int total_sampah;
    private int total;

    // Konstruktor
    public Permintaan() {}

    public Permintaan(int id, String nama_pelanggan) {
        this.id = id;
        this.nama_pelanggan = nama_pelanggan;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public int getTotal(){
        return this.total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public void setJenisSampah(String jenis_sampah) {
        this.jenis_sampah = jenis_sampah;
    }

    public String getJenisSampah() {
        return jenis_sampah;
    }

    public void setBeratSampah(int berat_sampah) {
        this.berat_sampah = berat_sampah;
    }

    public int getBeratSampah() {
        return berat_sampah;
    }

    public void setTanggalPermintaan(String tanggal_permintaan) {
        this.tanggal_permintaan = tanggal_permintaan;
    }

    public String getTanggalPermintaan() {
        return tanggal_permintaan;
    }


    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNamaPelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNamaPelanggan(){
        return this.nama_pelanggan;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat(){
        return this.alamat;
    }

    public void setTotalSampah(int total_sampah) {
        this.total_sampah = total_sampah;
    }

    public int getTotalSampah(){
        return this.total_sampah;
    }

    public String toString()
    {
        return nama_pelanggan + " - " + alamat;
    }
}
