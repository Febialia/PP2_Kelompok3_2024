package model;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Permintaan {
    private int id;
    private String nama_pelanggan;
    private String alamat;
    private String jenis_sampah;
    private String berat_sampah;
    private Date tanggal_penjemputan;
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNamaPelanggan() { return nama_pelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.nama_pelanggan = namaPelanggan; }
    
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    
    public String getJenisSampah() { return jenis_sampah; }
    public void setJenisSampah(String jenisSampah) { this.jenis_sampah = jenisSampah; }
    
    public String getBeratSampah() { return berat_sampah; }
    public void setBeratSampah(String beratSampah) { this.berat_sampah = beratSampah; }

    public Date getTanggalPenjemputan() { return tanggal_penjemputan; }
    public void setTanggalPenjemputan(Date tanggalPenjemputan) { this.tanggal_penjemputan = tanggalPenjemputan; }
    
    // Method tambahan untuk memformat tanggal jika diperlukan
    public String getFormattedTanggalPenjemputan() {
        if (tanggal_penjemputan != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(tanggal_penjemputan);
        }
        return null;
    }
}
