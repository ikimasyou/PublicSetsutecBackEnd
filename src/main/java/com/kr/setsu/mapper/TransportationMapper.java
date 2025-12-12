package com.kr.setsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.TransportationInfo;

@Mapper
@Repository
public interface TransportationMapper {

	@Select("select * from transportation_info")
	public List<TransportationInfo> getAllTransportationInfo();
	
	@Select("select * from transportation_info where date=#{date}")
	public List<TransportationInfo> getTransportationInfoByDate(String date);
	
	@Insert("insert into transportation_info(date,category,trip_type,start_station,end_station,fare,note)"
			+ "values(#{date},#{category},#{tripType},#{startStation},#{endStation},#{fare},#{note})")
	public Integer insertTransportationInfo(TransportationInfo tansportationInfo);
	
	@Update("update transportation_info set category=#{category},trip_type=#{tripType},"
			+ "start_station=#{startStation}, end_station=#{endStation},fare=#{fare},note=#{note} where date=#{date}")
	public Integer updateTransportationInfoByDate(TransportationInfo tansportationInfo);
	
	@Delete("delete from transportation_info where date=#{date}")
	public Integer deleteTransportationInfoByDate(TransportationInfo tansportationInfo);
}
