package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
//import com.nus.iss.tasktracker.service.KafkaProducerService;
import com.nus.iss.tasktracker.service.UserInfoService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j

public class UserInfoServiceImpl implements UserInfoService {
    private  final UserInfoRepository userInfoRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO UserLogin(UserDTO requestDTO){

        UserInfo userEntity = userInfoRepository.findByUsernameAndPasswordAndDeleteFlag (requestDTO.getUsername(),requestDTO.getPassword(), TaskTrackerConstant.DELETE_FLAG_FALSE);
        log.info("UserInfo {}", userEntity);
        if (userEntity != null) {
            userEntity.setPassword("");
        }
        return userMapper.userEntityToUserDTO(userEntity);
    }

/*
    @Override
    public UserDTO getUserDetail(int userId) {
        UserInfo userEntity = userInfoRepository.findByUserId(userId);
        log.info("UserInfo {}", userEntity);
        if (userEntity != null) {
            userEntity.setPassword("");
        }
        return userMapper.userEntityToUserDTO(userEntity);
    }*/
/*
    @Override
    public UserDTO getUserDetailByUserName(String userName) {
        UserInfo userEntity = userInfoRepository.findByUsername(userName);
        log.info("UserInfo {}", userEntity);
        if (userEntity != null) {
            userEntity.setPassword("");
        }
        return userMapper.userEntityToUserDTO(userEntity);
    }*/


}
