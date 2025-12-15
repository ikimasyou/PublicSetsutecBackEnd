package com.kr.setsu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kr.setsu.entity.BPPersonnel;
import com.kr.setsu.mapper.BPMapper;
import com.kr.setsu.service.BPService;

@Service
public class BPServiceImpl implements BPService{

	@Autowired
	BPMapper bpMapper;
	
	@Override
	public Integer updateBP(BPPersonnel bpPersonnel) {
		BPPersonnel existing =
		        bpMapper.selectByUserId(bpPersonnel.getUserId());

		    if (existing == null) {
		        return bpMapper.insert(bpPersonnel);
		    } else {
		        return bpMapper.updateByUserId(bpPersonnel);
		    }
	}

}
