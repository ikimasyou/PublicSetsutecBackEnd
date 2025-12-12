package com.kr.setsu.controller;

import com.kr.setsu.entity.AnkenInfo;
import com.kr.setsu.service.AnkenService;
import com.kr.setsu.service.serviceImpl.AnkenServicelmpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/anken")
public class ankenController {
    @Autowired
    private AnkenService ankenService;
    @GetMapping("/getAnkenName")
    @ResponseBody
    public String getAnkenName(String userId) {

        return ankenService.getAnkenName(userId);
    }



}
