package model;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface PermintaanMapper {
    @Select("SELECT * FROM permintaan")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "namaPelanggan", column = "nama_pelanggan"),
        @Result(property = "alamat", column = "alamat"),
        @Result(property = "jenisSampah", column = "jenis_sampah"),
        @Result(property = "beratSampah", column = "berat_sampah"),
        @Result(property = "tanggalPenjemputan", column = "tanggal_penjemputan")
    })
    List<Permintaan> getAllPermintaan();

    @Select("SELECT * FROM permintaan WHERE id = #{id}")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "namaPelanggan", column = "nama_pelanggan"),
        @Result(property = "alamat", column = "alamat"),
        @Result(property = "jenisSampah", column = "jenis_sampah"),
        @Result(property = "beratSampah", column = "berat_sampah"),
        @Result(property = "tanggalPenjemputan", column = "tanggal_penjemputan")
    })
    Permintaan getPermintaanById(int id);

    @Insert("INSERT INTO permintaan (nama_pelanggan, alamat, jenis_sampah, tanggal_penjemputan, berat_sampah) " +
            "VALUES (#{namaPelanggan}, #{alamat}, #{jenisSampah}, #{berat_sampah}), #{tanggalPenjemputan}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPermintaan(Permintaan permintaan);

    @Update("UPDATE permintaan SET nama_pelanggan = #{namaPelanggan}, " +
            "alamat = #{alamat}, " +
            "jenis_sampah = #{jenisSampah}, " +
            "berat_sampah = #{berat_sampah}, " +
            "tanggal_penjemputan = #{tanggalPenjemputan} " +
            "WHERE id = #{id}")
    void updatePermintaan(Permintaan permintaan);

    @Delete("DELETE FROM permintaan WHERE id = #{id}")
    void deletePermintaan(int id);

    @Update("UPDATE permintaan SET isDeleted = TRUE WHERE id = #{id}")
    void softDeletePermintaan(int id);

}