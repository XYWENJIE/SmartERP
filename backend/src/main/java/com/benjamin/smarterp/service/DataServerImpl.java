package com.benjamin.smarterp.service;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.UserLogin;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.repository.jpa.UserLoginRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DataServerImpl {

    private final PersonnelRepository personnelRepository;
    private final UserLoginRepository userLoginRepository;

    public DataServerImpl(PersonnelRepository personnelRepository, UserLoginRepository userLoginRepository) {
        this.personnelRepository = personnelRepository;
        this.userLoginRepository = userLoginRepository;
    }

//    @PostConstruct
//    public void initData(){
//        log.info("初始化数据");
//        List<Personnel> userInfoList = new ArrayList<>();
////        if(!this.personnelRepository.existsByRealName("SmartAssist")){
////            log.info("创建SmartAssist助手");
////            Personnel userInfo = new Personnel();
////            userInfo.setRealName("SmartAssist");
////            userInfo.setRobot(true);
////            userInfo.setAvatarUrl("http://localhost:8080/a821c0ab-f8a0-5284-a714-679e578992f5_0.png");
////            userInfoList.add(userInfo);
////        }
////        if(!this.personnelRepository.existsByRealName("黄文杰")){
////            log.info("创建管理员信息");
////            Personnel userInfo = new Personnel();
////            Optional<UserLogin> optional = this.userLoginRepository.findByUsername("admin");
////            userInfo.setRealName("黄文杰");
////            userInfo.setEmail("xywenjie@outlook.com");
////            userInfo.setPhone("13530995506");
////            userInfo.setAvatarUrl("https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp");
////            userInfo.setUserLogin(optional.get());
////            userInfoList.add(userInfo);
////        }
//        log.info("初始化保存信息{}条",userInfoList.size());
//        this.personnelRepository.saveAll(userInfoList);
//    }
}
