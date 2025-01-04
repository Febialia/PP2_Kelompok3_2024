package model;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface PenjemputanMapper {
    @Select("SELECT * FROM penjemputan")
    List<Penjemputan> getAllPenjemputan();
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, pe.tanggal_penjemputan AS waktuPenjemputan " +
            "FROM penjemputan p JOIN kurir k ON p.id_kurir = k.id JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE p.status = 'Dalam Perjalanan' OR p.status = 'Selesai' ORDER BY p.tanggal_penjemputan DESC")
    List<Penjemputan> getLatestStatus();
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, " +
            "pe.tanggal_penjemputan as waktuPenjemputan, pe.lokasi as lokasi, " +
            "pe.jenis_sampah as jenisSampah, p.point as poinDidapatkan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "ORDER BY p.tanggal_penjemputan DESC")
    List<Penjemputan> getHistory();
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, " +
            "pe.tanggal_penjemputan as waktuPenjemputan, pe.lokasi as lokasi, " +
            "pe.jenis_sampah as jenisSampah, p.point as poinDidapatkan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "ORDER BY p.tanggal_penjemputan DESC")
    List<Penjemputan> getCompleteHistory();
    
   @Select("SELECT SUM(p.berat) AS totalBerat, SUM(p.point) AS totalPoint " +
        "FROM penjemputan p " +
        "JOIN permintaan pe ON p.id_permintaan = pe.id " +
        "WHERE pe.jenis_sampah = 'Laptop'")
    @Results({
        @Result(property = "totalBerat", column = "totalBerat"),
        @Result(property = "totalPoint", column = "totalPoint")
    })
    TotalPointInfo getTotalBeratDanPointElektronik();

    
}