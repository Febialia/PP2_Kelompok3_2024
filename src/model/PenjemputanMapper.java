package model;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface PenjemputanMapper {
    @Select("SELECT * FROM penjemputan")
    List<Penjemputan> getAllPenjemputan();

    @Select("SELECT * FROM penjemputan WHERE id=(SELECT max(id) FROM penjemputan) AND id_kurir = #{idKurir} AND id_permintaan = #{idPermintaan}; ")
    List<Penjemputan> getLastRow(@Param("idKurir") int idKurir, @Param("idPermintaan") int idPermintaan);

    @Select("""
SELECT
    penj.id AS id,
    p.nama_pelanggan as namaPelanggan,
    p.alamat,
    t_latest.lokasi AS lokasi,
    k.nama_kurir as namaKurir
FROM
    penjemputan penj
JOIN
    permintaan p ON penj.id_permintaan = p.id
JOIN
    kurir k ON penj.id_kurir = k.id
JOIN
    (SELECT id_penjemputan, MAX(id) AS max_tracking_id FROM tracking GROUP BY id_penjemputan) AS sub ON penj.id = sub.id_penjemputan
JOIN
    tracking t_latest ON sub.max_tracking_id = t_latest.id
WHERE
    penj.status <> 'selesai';
    """)
    List<Penjemputan> getTrackingList();
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, p.tanggal_penjemputan AS waktuPenjemputan " +
            "FROM penjemputan p JOIN kurir k ON p.id_kurir = k.id JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE p.status = 'Dalam Perjalanan' OR p.status = 'Selesai' ORDER BY p.id DESC")
    List<Penjemputan> getLatestStatus();
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, p.tanggal_penjemputan AS waktuPenjemputan, pe.alamat as alamat, p.point as point, pe.berat_sampah as beratsampah, pe.nama_pelanggan as namaPelanggan " +
            "FROM penjemputan p JOIN kurir k ON p.id_kurir = k.id JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE p.status = 'Selesai' ORDER BY p.id DESC")
    List<Penjemputan> getHistory();
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, p.tanggal_penjemputan AS waktuPenjemputan, pe.alamat as alamat, p.point as point, pe.berat_sampah as beratsampah, pe.nama_pelanggan as namaPelanggan " +
            "FROM penjemputan p JOIN kurir k ON p.id_kurir = k.id JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE p.tanggal_penjemputan BETWEEN #{tanggal} AND #{tanggal2} ORDER BY p.id DESC")
    List<Penjemputan> getHistoryByDate(@Param("tanggal") String tanggal, @Param("tanggal2") String tanggal2);
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, p.tanggal_penjemputan AS waktuPenjemputan, pe.alamat as alamat, p.point as point, pe.berat_sampah as beratsampah, pe.nama_pelanggan as namaPelanggan " +
            "FROM penjemputan p JOIN kurir k ON p.id_kurir = k.id JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "WHERE status = #{status} ORDER BY p.id DESC")
    List<Penjemputan> getHistoryByStatus(@Param("status") String status);
    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, p.tanggal_penjemputan AS waktuPenjemputan, pe.alamat as alamat, p.point as point, pe.berat_sampah as beratsampah, pe.nama_pelanggan as namaPelanggan " +
    "FROM penjemputan p " +
    "JOIN kurir k ON p.id_kurir = k.id " +
    "JOIN permintaan pe ON p.id_permintaan = pe.id " +
    "WHERE k.nama_kurir LIKE CONCAT('%', #{search}, '%') " +
    "OR pe.alamat LIKE CONCAT('%', #{search}, '%') " +
    "OR pe.nama_pelanggan LIKE CONCAT('%', #{search}, '%') " +
    "ORDER BY p.id DESC")
    List<Penjemputan> getHistoryBySearch(@Param("search") String search);

    
    @Select("SELECT p.id as id, p.status AS status, k.nama_kurir as namaKurir, " +
            "pe.tanggal_penjemputan as waktuPenjemputan, pe.lokasi as lokasi, " +
            "pe.jenis_sampah as jenisSampah, p.point as poinDidapatkan " +
            "FROM penjemputan p " +
            "JOIN kurir k ON p.id_kurir = k.id " +
            "JOIN permintaan pe ON p.id_permintaan = pe.id " +
            "ORDER BY p.tanggal_penjemputan DESC")
    List<Penjemputan> getCompleteHistory();
    
    @Select("SELECT * FROM penjemputan WHERE status_penjemputan = #{status}")
    List<Penjemputan> getPenjemputanByStatus(@Param("status") String status);

    @Select("SELECT * FROM penjemputan WHERE id = #{id}")
    List<Penjemputan> getPenjemputan(@Param("id") int id);

    @Insert("INSERT INTO penjemputan (id_kurir, id_permintaan, status, point, tanggal_penjemputan) " +
    "VALUES (#{idKurir}, #{idPermintaan}, #{status}, #{point}, NOW())")
    @Options(useGeneratedKeys = true)
                int tambahPenjemputan(@Param("idKurir") int idKurir,
                                @Param("idPermintaan") int idPermintaan,
                                @Param("status") String status,
                                @Param("point") int point);

    @Update("UPDATE penjemputan " +
    "SET id_kurir = #{idKurir}, status = #{status}, point = #{point} WHERE id = #{id}")
                int editPenjemputan(@Param("id") int id, @Param("idKurir") int idKurir,
                                @Param("status") String status,
                                @Param("point") int point);

    @Delete("DELETE FROM penjemputan WHERE id = #{id}")
                int deletePenjemputan(@Param("id") int id);


    @Select("SELECT COUNT(id) FROM penjemputan")              
    int total();

}