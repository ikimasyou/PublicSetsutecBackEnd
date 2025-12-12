package com.kr.setsu.mapper;

import com.kr.setsu.entity.AnkenInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnkenMapper {
	//changed ankenmeyi to ankenmei
    @Select("select ankenmei from t_anken where torokusyaID=#{userId} ")
    String getAnkenName(String userId);

}
