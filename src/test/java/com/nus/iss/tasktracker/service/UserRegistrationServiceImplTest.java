package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.impl.UserRegistrationServiceImpl;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceImplTest {
/*
    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    @BeforeAll
    public static void init() {
        mockStatic(TaskTrackerInterceptor.class);
    }

    @AfterAll
    public static void cleanup() {
        Mockito.clearAllCaches();
    }

    @Test
    void testSignUp_Success(){

        UserInfo userEntity=new UserInfo();
        userEntity.setUserId(1);
        userEntity.setName("Tester Name");
        userEntity.setPassword("password1");


        UserDTO returnDTO=new UserDTO();
        returnDTO.setUserId(1);
        returnDTO.setUsername("user1");
        returnDTO.setEmail("user1@test.test");



        UserDTO requestDTO=new UserDTO();
        requestDTO.setName("Tester Name");
        requestDTO.setEmail("user1@test.test");
        requestDTO.setUsername("user1");
        requestDTO.setPassword("password1");
        requestDTO.setCreatedBy("admin");



        when(userInfoRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.userEntityToUserDTO(userEntity)).thenReturn(returnDTO);


        UserDTO output = userMapper.userEntityToUserDTO(userInfoRepository.save(userEntity));

        verify(userInfoRepository).save(userEntity);
        verify(userMapper).userEntityToUserDTO(userEntity);


        assertEquals("user1",output.getUsername());
        assertEquals("user1@test.test",output.getEmail());


    }


    @Test
    void testChangePassword_Success(){
        UserDTO requestDTO = new UserDTO();
        requestDTO.setUsername("testuser");
        requestDTO.setOldPassword("oldPassword");
        requestDTO.setNewPassword("newPassword123");

        UserInfo userEntity = new UserInfo();
        userEntity.setUsername("testuser");
        userEntity.setPassword("oldPassword");

        when(userInfoRepository.findByUsername("testuser")).thenReturn(userEntity);


        // Act
        userRegistrationService.changePassword(requestDTO);

        // Assert
        assertEquals(requestDTO.getNewPassword(), userEntity.getPassword());
        verify(userInfoRepository, times(1)).save(userEntity);


    }


    @Test
    void testGetAllUsersInAGroup_Success(){

        Mockito.when(TaskTrackerInterceptor.getLoggedInUserName()).thenReturn("admin");
        Mockito.when(TaskTrackerInterceptor.getLoggedInUserRole()).thenReturn(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN);

        UserInfo currentUserInfo = new UserInfo();
        currentUserInfo.setUserId(1);
        currentUserInfo.setGroupId(1);
        when(userInfoRepository.findByUsername("admin")).thenReturn(currentUserInfo);

        List<UserInfo> userInfoList=new ArrayList<>();
        UserInfo user1=new UserInfo();
        user1.setUserId(1);
        user1.setPassword("test");
        userInfoList.add(user1);


        UserInfo user2=new UserInfo();
        user2.setUserId(2);
        user2.setPassword("test");
        userInfoList.add(user2);

        when(userInfoRepository.findAllByGroupIdAndDeleteFlag(1,TaskTrackerConstant.DELETE_FLAG_FALSE)  ).thenReturn(userInfoList);

        List<UserDTO> userDTOList1 = new ArrayList<UserDTO>();
        UserDTO userdto1=new UserDTO();
        userdto1.setUserId(2);
        userdto1.setPassword("test");
        userDTOList1.add(userdto1);
        UserDTO userdto2=new UserDTO();
        userdto2.setUserId(2);
        userdto2.setPassword("test");
        userDTOList1.add(userdto2);

        when(userMapper.userEntityToUserDTO(any())).thenReturn(userdto1);

        List<UserDTO> userDTOList = userRegistrationService.getAllUsersInAGroup();
        assertEquals(2, userDTOList.size());

    }


    @Test
    public void testUpdateUserInfo_Admin_Success() {

        Mockito.when(TaskTrackerInterceptor.getLoggedInUserName()).thenReturn("admin");
        Mockito.when(TaskTrackerInterceptor.getLoggedInUserRole()).thenReturn(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN);

        // Mocking the request DTO
        UserDTO requestDTO = new UserDTO();
        requestDTO.setUserId(1);
        requestDTO.setName("New Name");
        requestDTO.setEmail("newemail@example.com");
        requestDTO.setPasswordChangeMandatory(TaskTrackerConstant.TASK_PWD_CHANGE_MANDATORY_TRUE);
        requestDTO.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_TRUE);

        // Mocking UserRepository
        UserInfo currentUserInfo = new UserInfo();
        currentUserInfo.setUserId(1);
        when(userInfoRepository.findById(1)).thenReturn(Optional.of(currentUserInfo));

        // Mocking UserMapper
        when(userMapper.userEntityToUserDTO(any())).thenAnswer(invocation -> {
            UserInfo userInfo = invocation.getArgument(0);
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userInfo.getUserId());
            userDTO.setName(userInfo.getName());
            userDTO.setEmail(userInfo.getEmail());
            userDTO.setPassword(userInfo.getPassword());
            userDTO.setDeleteFlag(userInfo.getDeleteFlag());
            return userDTO;
        });
        when(userMapper.userDTOToUserInfo(any())).thenAnswer(invocation -> {
            UserDTO userDTO = invocation.getArgument(0);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userDTO.getUserId());
            userInfo.setName(userDTO.getName());
            userInfo.setEmail(userDTO.getEmail());
            userInfo.setPassword(userDTO.getPassword());
            userInfo.setDeleteFlag(userDTO.getDeleteFlag());
            return userInfo;
        });

        // Mocking userInfoRepository.save()
        when(userInfoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Testing
        UserDTO output = userRegistrationService.updateUserInfo(requestDTO);
        assertNotNull(output);
        assertEquals("New Name", output.getName());
        assertEquals("newemail@example.com", output.getEmail());
        assertEquals(TaskTrackerConstant.DELETE_FLAG_TRUE, output.getDeleteFlag());


    }



    @Test
    public void testGetUserById() {
        int userId = 1;

        // Mocking
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUsername("testuser");

        when(userMapper.userEntityToUserDTO(any())).thenReturn(userDTO);


        UserDTO expectedDTO = new UserDTO();
        expectedDTO.setUserId(userId);
        expectedDTO.setUsername("testuser");

        when(userMapper.userEntityToUserDTO(any())).thenReturn(expectedDTO);

        // Call the method to test
        UserDTO result = userRegistrationService.getUserById(userId);

        // Verify
        assertEquals(expectedDTO, result);

        verify(userMapper, times(1)).userEntityToUserDTO(any());
    }*/

}


