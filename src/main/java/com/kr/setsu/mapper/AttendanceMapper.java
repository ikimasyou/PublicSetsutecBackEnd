package com.kr.setsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.AttendanceInfo;

@Repository
@Mapper
public interface AttendanceMapper {

	
	//changed @Select to @Insert and changed syukkin_jikan to shukkin_jikan
	@Insert ("insert into  M_KINMU(user_id,hiduke,kinmu_kiroku_id,shukkin_jikan) values(#{userId},#{date},#{attendanceId},#{begin})")
	@ResultMap("attendanceInfoResult")
	Integer shukkin(AttendanceInfo attendanceInfo);
//	@Select ("insert into M_KINMU(user_id,hiduke,kinmu_kiroku_id,taikin_jikan) value(#{userId},#{date},#{attendanceId},#{end})")
//	@ResultMap("attendanceInfoResult")
    @Update("update M_KINMU set taikin_jikan=#{end} where kinmu_kiroku_id=#{kinmuid}  ")
	@ResultMap("attendanceInfoResult")
	Integer taikin(AttendanceInfo attendanceInfo);
	@Select ("select * from M_KINMU where user_id = #{userId} and  hiduke like concat('%',#{date},'%') order by hiduke")
//    @Select ("select * from M_KINMU where user_id = #{userId} and (hiduke is null or hiduke like concat('%',#{date},'%')) order by hiduke ")
	@ResultMap("attendanceInfoResult")
	List<AttendanceInfo> getAttendance(AttendanceInfo attendanceInfo);
	
	@Select("select count(*) from M_KINMU;")
	Integer getExistAttendanceCount();
	//changed hiruma_kyukeyi_jikan to hiruma_kyukei_jikan and yuugata_kyukeyi_jikan to yuugata_kyukei_jikan
	//changed syukkin_jikan to shukkin_jikan
	@Update("update M_KINMU set kinmu_kubun=#{division},hiruma_kyukei_jikan=#{breakday},yuugata_kyukei_jikan=#{breaknight},biko=#{note},kinmu_nayiyo=#{workcontent},jitudou_jikan=#{workhour},shukkin_jikan=#{begin},taikin_jikan=#{end} where kinmu_kiroku_id=#{kinmuid}")
	Integer updateWorkRecord(AttendanceInfo attendanceInfo);
	Integer updateAttendance(AttendanceInfo attendanceInfo);
	
	Integer insertAttendance(AttendanceInfo attendanceInfo);

	//List<AttendanceInfo>

}
