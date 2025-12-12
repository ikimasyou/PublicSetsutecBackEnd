package com.kr.setsu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kr.setsu.entity.TransportationInfo;
import com.kr.setsu.mapper.TransportationMapper;
import com.kr.setsu.service.TransportationService;

@Service
public class TransportationServiceImpl implements TransportationService {

	@Autowired
	private TransportationMapper transportationMapper;
	@Override
	public List<TransportationInfo> getAllTransportationInfo() {
		// TODO Auto-generated method stub
		return transportationMapper.getAllTransportationInfo();
	}

	@Override
	public Integer updateTransportationInfoByDate(TransportationInfo tansportationInfo) {
		// TODO Auto-generated method stub
		return transportationMapper.updateTransportationInfoByDate(tansportationInfo);
	}

	@Override
	public List<TransportationInfo> getTransportationInfoByDate(String date) {
		// TODO Auto-generated method stub
		return transportationMapper.getTransportationInfoByDate(date);
	}

	@Override
	public Integer insertTransportationInfo(TransportationInfo tansportationInfo) {
		// TODO Auto-generated method stub
		return transportationMapper.insertTransportationInfo(tansportationInfo);
	}

	@Override
	public Integer deleteTransportationInfoByDate(TransportationInfo tansportationInfo) {
		// TODO Auto-generated method stub
		return transportationMapper.deleteTransportationInfoByDate(tansportationInfo);
	}
	

}
