package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface TrackingMapper {

    @Select("SELECT * FROM tracking WHERE id_penjemputan = #{idPenjemputan}")
    List<Tracking> getTrackingByPenjemputan(int idPenjemputan);

    @Insert("INSERT INTO tracking (id_penjemputan, lokasi) VALUES (#{idPenjemputan}, #{lokasi})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertTracking(Tracking tracking);

    @Update("UPDATE tracking SET lokasi = #{lokasi} WHERE id = #{id}")
    void updateTracking(Tracking tracking);

    @Delete("DELETE FROM tracking WHERE id = #{id}")
    void deleteTrackingById(int id);
}
