package model;

public class Penjemputan {
    private int id;
    private int id_kurir;
    private int id_permintaan;
    private String status;
    private int point;
    private String tanggal_penjemputan;
    private String statusPenjemputan;
    private String namaKurir;
    private String waktuPenjemputan;

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setIdKurir(int id_kurir) {
        this.id_kurir = id_kurir;
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

    public void setTanggalPenjemputan(String tanggal_penjemputan) {
        this.tanggal_penjemputan = tanggal_penjemputan;
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


    // Getter dan Setter
    public String getStatusPenjemputan() { return statusPenjemputan; }
    public void setStatusPenjemputan(String statusPenjemputan) { this.statusPenjemputan = statusPenjemputan; }

    public String getNamaKurir() { return namaKurir; }
    public void setNamaKurir(String namaKurir) { this.namaKurir = namaKurir; }

    public String getWaktuPenjemputan() { return waktuPenjemputan; }
    public void setWaktuPenjemputan(String waktuPenjemputan) { this.waktuPenjemputan = waktuPenjemputan; }

}