package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface KurirMapper {
    
    @Select("SELECT * FROM kurir")
    List<Kurir> getAllKurirs();

    @Insert("INSERT INTO kurir (nama_kurir) VALUES (#{nama_kurir})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertKurir(Kurir kurir);

    @Insert("INSERT INTO kurir (nama_kurir) VALUES (#{nama_kurir})")
    @Options(useGeneratedKeys = true)
    int tambahKurir(@Param("nama_kurir") String namaKurir);

    @Update("UPDATE kurir SET nama_kurir = #{nama_kurir} WHERE id = #{id}")
    void editKurir(@Param("id") int idKurir, @Param("nama_kurir") String namaKurir);

    @Delete("DELETE FROM kurir WHERE id = #{id}")
    void deleteKurir(@Param("id") int idKurir);

    @Select("SELECT * FROM kurir WHERE id = #{id}")
    List<Kurir> getKurirById(@Param("id") int idKurir);

    @Select("SELECT COUNT(id) FROM kurir")        
    int total();
}