package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface KurirMapper {
    
    @Select("SELECT * FROM kurir")
    List<Kurir> getAllKurirs();

    @Insert("INSERT INTO kurir (nama_kurir) VALUES (#{nama_kurir})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertKurir(Kurir kurir);

    @Update("UPDATE kurir SET nama_kurir = #{nama_kurir} WHERE id = #{id}")
    void updateKurir(Kurir kurir);

    @Delete("DELETE FROM kurir WHERE id = #{id}")
    void deleteKurirById(int id);

    @Select("SELECT * FROM kurir WHERE id = #{id}")
    Kurir getKurirById(int id);
}