package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserInfoServiceImplTest {
    @Mock
    private UserInfoRepository userInfoRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserInfoServiceImpl userInfoService;

    @Test
    void testUserLogin_ValidUser(){

        UserInfo userEntity=new UserInfo();
        userEntity.setUserId(1);
        userEntity.setUsername("User1");
        userEntity.setPassword("password1");
        userEntity.setRole("ROLE_USER");

        UserDTO returnDTO=new UserDTO();
        returnDTO.setUserId(1);
        returnDTO.setUsername("User1");
        returnDTO.setUserRole("ROLE_USER");
        returnDTO.setOldPassword(null);
        returnDTO.setNewPassword(null);
        returnDTO.setPassword("");

        when(userInfoRepository.findByUsernameAndPasswordAndDeleteFlag("User1","password1","FALSE")).thenReturn(userEntity);
        when(userMapper.userEntityToUserDTO(userEntity)).thenReturn(returnDTO);

        UserDTO requestDTO=new UserDTO();
        requestDTO.setUsername("User1");
        requestDTO.setPassword("password1");


        UserDTO resultDTO=userInfoService.UserLogin(requestDTO);


        verify(userInfoRepository).findByUsernameAndPasswordAndDeleteFlag("User1","password1","FALSE");
        verify(userMapper).userEntityToUserDTO(userEntity);

        assertEquals("User1",returnDTO.getUsername());
        assertEquals("",returnDTO.getPassword());
        assertEquals("ROLE_USER", returnDTO.getUserRole());

    }



    @Test
    void testUserLogin_InValidUser(){

        UserInfo userEntity=null;

        UserDTO returnDTO=null;

        when(userInfoRepository.findByUsernameAndPasswordAndDeleteFlag("User1","password1","FALSE")).thenReturn(userEntity);
        when(userMapper.userEntityToUserDTO(userEntity)).thenReturn(returnDTO);

        UserDTO requestDTO=new UserDTO();
        requestDTO.setUsername("User1");
        requestDTO.setPassword("password1");


        UserDTO resultDTO=userInfoService.UserLogin(requestDTO);


        verify(userInfoRepository).findByUsernameAndPasswordAndDeleteFlag("User1","password1","FALSE");
        verify(userMapper).userEntityToUserDTO(userEntity);

        assertNull(returnDTO);
    }
/*


    @Test
    public void testGetUserDetail() {
        // Mocking
        int userId = 1;
        UserInfo userEntity = new UserInfo();
        userEntity.setUserId(userId);
        userEntity.setUsername("testuser");
        userEntity.setPassword("password");

        // Mock behavior of userInfoRepository
        when(userInfoRepository.findByUserId(userId)).thenReturn(userEntity);

        // Mock behavior of userMapper
        UserDTO expectedDTO = new UserDTO();
        expectedDTO.setUserId(userId);
        expectedDTO.setUsername("testuser");
        expectedDTO.setPassword(""); // password should be empty
        when(userMapper.userEntityToUserDTO(userEntity)).thenReturn(expectedDTO);

        // Call the method to test
        UserDTO result = userInfoService.getUserDetail(userId);

        // Verify
        assertEquals(expectedDTO, result);
        verify(userInfoRepository, times(1)).findByUserId(userId);
        verify(userMapper, times(1)).userEntityToUserDTO(userEntity);
    }



    @Test
    public void testGetUserDetailByUserName() {
        // Mocking
        String userName = "testuser";
        UserInfo userEntity = new UserInfo();
        userEntity.setUserId(1);
        userEntity.setUsername(userName);
        userEntity.setPassword("password");

        // Mock behavior of userInfoRepository
        when(userInfoRepository.findByUsername(userName)).thenReturn(userEntity);

        // Mock behavior of userMapper
        UserDTO expectedDTO = new UserDTO();
        expectedDTO.setUserId(1);
        expectedDTO.setUsername(userName);
        expectedDTO.setPassword(""); // password should be empty
        when(userMapper.userEntityToUserDTO(userEntity)).thenReturn(expectedDTO);

        // Call the method to test
        UserDTO result = userInfoService.getUserDetailByUserName(userName);

        // Verify
        assertEquals(expectedDTO, result);
        verify(userInfoRepository, times(1)).findByUsername(userName);
        verify(userMapper, times(1)).userEntityToUserDTO(userEntity);
    }
*/


}


