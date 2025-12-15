package com.kr.setsu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.BPPersonnel;

@Repository
@Mapper
public interface BPMapper {
	BPPersonnel selectByUserId(String userId);

    int insert(BPPersonnel bpPersonnel);

    int updateByUserId(BPPersonnel bpPersonnel);
}
