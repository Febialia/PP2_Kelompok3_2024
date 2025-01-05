package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface PenjemputanMapper {
    
    // Mendapatkan semua data dari tabel penjemputan
    @Select("SELECT * FROM penjemputan")
    List<Penjemputan> getAllPenjemputan();

    // Mendapatkan status terbaru dengan filter status tertentu
    @Select("SELECT p.id AS id, p.status AS status, k.nama_kurir AS namaKurir, " +
            "pe.tanggal_penjemputan AS waktuPenjemputan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE p.status IN ('Dalam Perjalanan', 'Selesai') " +
            "ORDER BY pe.tanggal_penjemputan DESC")
    List<Penjemputan> getLatestStatus();

    // Mendapatkan riwayat penjemputan lengkap
    @Select("SELECT p.id AS id, p.status AS status, k.nama_kurir AS namaKurir, " +
            "pe.tanggal_penjemputan AS waktuPenjemputan, pe.lokasi AS lokasi, " +
            "pe.jenis_sampah AS jenisSampah, p.point AS poinDidapatkan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "ORDER BY pe.tanggal_penjemputan DESC")
    List<Penjemputan> getHistory();

    // Mendapatkan riwayat lengkap dengan data terstruktur
    @Select("SELECT p.id AS id, p.status AS status, k.nama_kurir AS namaKurir, " +
            "pe.tanggal_penjemputan AS waktuPenjemputan, pe.lokasi AS lokasi, " +
            "pe.jenis_sampah AS jenisSampah, p.point AS poinDidapatkan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "ORDER BY pe.tanggal_penjemputan DESC")
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


    // Mendapatkan data berdasarkan status tertentu
    @Select("SELECT p.id AS id, p.status AS status, k.nama_kurir AS namaKurir, " +
            "pe.tanggal_penjemputan AS waktuPenjemputan, pe.lokasi AS lokasi, " +
            "pe.jenis_sampah AS jenisSampah, p.point AS poinDidapatkan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE p.status = #{status} " +
            "ORDER BY pe.tanggal_penjemputan DESC")
    List<Penjemputan> getPenjemputanByStatus(@Param("status") String status);
}
