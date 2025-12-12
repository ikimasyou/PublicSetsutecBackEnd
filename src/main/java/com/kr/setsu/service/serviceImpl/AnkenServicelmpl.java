package com.kr.setsu.service.serviceImpl;

import com.kr.setsu.entity.AnkenInfo;
import com.kr.setsu.mapper.AnkenMapper;
import com.kr.setsu.service.AnkenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnkenServicelmpl implements AnkenService {
    @Autowired
    private AnkenMapper ankenMapper;
    public String getAnkenName(String userId) {
        System.out.println(userId);

       return ankenMapper.getAnkenName(userId) ;

    }




}
