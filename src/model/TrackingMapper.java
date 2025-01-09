package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface TrackingMapper {
    @Select("SELECT t.id as id, t.lokasi as lokasi, p.id as idPenjemputan, pe.nama_pelanggan as namaPelanggan, k.nama_kurir as namaKurir FROM tracking t JOIN penjemputan p ON t.id_penjemputan = p.id JOIN permintaan pe ON p.id_permintaan = pe.id JOIN kurir k ON p.id_kurir = k.id WHERE p.id = #{id}")
    List<Tracking> getTracking(@Param("id") int id);

    @Insert("INSERT INTO tracking (id_penjemputan, lokasi) VALUES (#{id}, #{lokasi})")
    @Options(useGeneratedKeys = true)
    int addTracking(@Param("id") int idPenjemputan, @Param("lokasi") String lokasi);

    @Delete("DELETE FROM tracking WHERE id = #{id}")
    int deleteTracking(@Param("id") int id);
}
