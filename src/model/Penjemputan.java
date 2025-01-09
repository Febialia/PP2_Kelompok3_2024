package model;

public class Penjemputan {
    private int id;
    private int id_kurir;
    private int id_permintaan;
    private String status;
    private int point;
    private int beratsampah;
    private String tanggal_penjemputan;  // Gunakan java.sql.Timestamp jika perlu
    private String namaKurir;
    private String namaPelanggan;
    private String waktuPenjemputan;
    private String lokasi;
    private String jenisSampah;
    private String alamat;
    private int poinDidapatkan;
    private int total;
    private Kurir kurir;
    private Permintaan permintaan;

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setIdKurir(int id_kurir) {
        this.id_kurir = id_kurir;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public int getTotal(){
        return this.total;
    }


    public void setIdPermintaan(int id_permintaan) {
        this.id_permintaan = id_permintaan;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setnamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getnamaPelanggan() {
        return namaPelanggan;
    }
    
    public void setBeratSampah(int beratSampah) {
        this.beratsampah = beratSampah;
    }

    public int getBeratSampah() {
        return beratsampah;
    }

    public void setTanggalPenjemputan(String tanggal_penjemputan) {
        this.tanggal_penjemputan = tanggal_penjemputan;
    }
    
    public void setLokasi(String lokasi) { 
        this.lokasi = lokasi; 
    }
    
    public void setJenisSampah(String jenisSampah) { 
        this.jenisSampah = jenisSampah; 
    }
    
    public void setPoinDidapatkan(int poinDidapatkan) { 
        this.poinDidapatkan = poinDidapatkan; 
    }
    
    // Getter methods
    public int getId() {
        return id;
    }

    public int getIdKurir() {
        return id_kurir;
    }

    public int getIdPermintaan() {
        return id_permintaan;
    }

    public String getStatus() {
        return status;
    }

    public int getPoint() {
        return point;
    }

    public String getTanggalPenjemputan() {
        return tanggal_penjemputan;
    }
    
    public String getLokasi() { 
        return lokasi; 
    }
    
    public String getJenisSampah() { 
        return jenisSampah; 
    }
    
    public int getPoinDidapatkan() { 
        return poinDidapatkan; 
    }

    // Getter dan Setter tambahan jika diperlukan
    public String getNamaKurir() { return namaKurir; }
    public void setNamaKurir(String namaKurir) { this.namaKurir = namaKurir; }

    public String getWaktuPenjemputan() { return waktuPenjemputan; }
    public void setWaktuPenjemputan(String waktuPenjemputan) { this.waktuPenjemputan = waktuPenjemputan; }
    // Getter dan Setter untuk Kurir
      public Kurir getKurir() {
        return kurir;
    }

    public void setKurir(Kurir kurir) {
        this.kurir = kurir;
    }

    // Getter dan Setter untuk Permintaan
    public Permintaan getPermintaan() {
        return permintaan;
    }

    public void setPermintaan(Permintaan permintaan) {
        this.permintaan = permintaan;
    }
}
