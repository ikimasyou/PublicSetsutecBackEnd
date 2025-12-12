package com.kr.setsu.service;

import java.util.List;

import com.kr.setsu.entity.TransportationInfo;

public interface TransportationService {

	public List<TransportationInfo> getAllTransportationInfo();
	
	public List<TransportationInfo> getTransportationInfoByDate(String date);
	
	public Integer insertTransportationInfo(TransportationInfo tansportationInfo);
	
	public Integer updateTransportationInfoByDate(TransportationInfo tansportationInfo);
	
	public Integer deleteTransportationInfoByDate(TransportationInfo tansportationInfo);
}
