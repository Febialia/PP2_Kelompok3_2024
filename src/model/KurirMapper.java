package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface KurirMapper {

    // Mendapatkan semua data kurir
    @Select("SELECT * FROM kurir")
    List<Kurir> getAllKurirs();

    // Menambahkan kurir baru, otomatis mendapatkan nilai ID yang di-generate
    @Insert("INSERT INTO kurir (nama_kurir) VALUES (#{namaKurir})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // Mengambil ID yang dihasilkan secara otomatis
    int insertKurir(Kurir kurir);

    @Update("UPDATE kurir SET nama_kurir = #{namaKurir} WHERE id = #{id}")
    void updateKurir(Kurir kurir);

    @Delete("DELETE FROM kurir WHERE id = #{id}")
    void deleteKurirById(int id);

    // Mendapatkan kurir berdasarkan ID
    @Select("SELECT * FROM kurir WHERE id = #{id}")
    Kurir getKurirById(int id);
}
