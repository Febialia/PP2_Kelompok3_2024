package model;

public class Permintaan {
    private int id;
    private String namaPelanggan;
    private String alamat;
    private String jenisSampah;
    private String beratSampah;
    private String tanggalPenjemputan;
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }
    
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    
    public String getJenisSampah() { return jenisSampah; }
    public void setJenisSampah(String jenisSampah) { this.jenisSampah = jenisSampah; }
    
    public String getBeratSampah() { return beratSampah; }
    public void setBeratSampah(String beratSampah) { this.beratSampah = beratSampah; }

    public String getTanggalPenjemputan() { return tanggalPenjemputan; }
    public void setTanggalPenjemputan(String tanggalPenjemputan) { this.tanggalPenjemputan = tanggalPenjemputan; }
    
}

