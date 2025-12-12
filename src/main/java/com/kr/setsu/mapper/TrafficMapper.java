package com.kr.setsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.AttendanceInfo;
import com.kr.setsu.entity.TrafficInfo;

@Repository
@Mapper
public interface TrafficMapper {
	
	@Select("select * from kanri_traffic order by id")
	@ResultMap("trafficInfoResult")
	List<TrafficInfo> getTrafficList();

	@Delete("delete from kanri_traffic where id = #{trafficId}")
	Integer deleteTraffic(@Param("trafficId") String trafficId);

	Integer insertTraffic(TrafficInfo trafficInfo);
	
	Integer updateTraffic(TrafficInfo trafficInfo);
	
	@Select ("select * from kanri_traffic where user_id = #{userId} and date like concat('%',#{date},'%') order by date")
	@ResultMap("trafficInfoResult")
	List<TrafficInfo> getTrafficUserMonthList(TrafficInfo trafficInfo);
}
