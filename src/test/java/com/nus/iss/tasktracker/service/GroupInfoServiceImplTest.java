package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.GroupMapper;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.GroupInfoRepository;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.impl.GroupInfoServiceImpl;
import com.nus.iss.tasktracker.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupInfoServiceImplTest {

    @Mock
    private GroupInfoRepository groupInfoRepository;

    @Mock
    private  UserInfoRepository userInfoRepository;
    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupInfoServiceImpl groupInfoService;



/*
    @Test
    void testGetGroupById_Success(){


        GroupInfo groupInfo=new GroupInfo();
        groupInfo.setGroupId(1);

        GroupDTO expectedGroupDTO=new GroupDTO();
        expectedGroupDTO.setGroupId(1);


        when(groupInfoRepository.findByGroupId(1)).thenReturn(groupInfo);
        when(groupMapper.groupInfoToGroupDTO(groupInfo)).thenReturn(expectedGroupDTO);


        //Test
        GroupDTO actualGroupDTO=groupInfoService.getGroupById(1);

        assertEquals(expectedGroupDTO,actualGroupDTO);

        //verify if method get called
        verify(groupInfoRepository).findByGroupId(1);
        verify(groupMapper).groupInfoToGroupDTO(groupInfo);

    }




    @Test
    void testGetGroupById_GroupNotFound(){

        //Given
        GroupInfo groupInfo=null;
        when(groupInfoRepository.findByGroupId(1)).thenReturn(groupInfo);

        //When
        GroupDTO actualGroupDTO=groupInfoService.getGroupById(1);

        //Then
        assertNull(actualGroupDTO);

        //verify if method get called
        verify(groupInfoRepository).findByGroupId(1);
        verify(groupMapper).groupInfoToGroupDTO(groupInfo);

    }




    @Test
    void testCreateGroup_Success(){

        //Given
        GroupDTO groupDTORequest=new GroupDTO();
        groupDTORequest.setGroupName("Test Group Name");

        GroupInfo groupInfoRequest =new GroupInfo();
        groupInfoRequest.setGroupName("Test Group Name");

        GroupInfo saveGroupInfo=new GroupInfo();
        saveGroupInfo.setGroupName("Test Group Name");
        saveGroupInfo.setGroupId(1);

        GroupDTO expectedGroupDTOResponse=new GroupDTO();
        expectedGroupDTOResponse.setGroupId(1);
        expectedGroupDTOResponse.setGroupName("Test Group Name");

        when(groupMapper.groupDTOToGroupInfo(groupDTORequest)).thenReturn(groupInfoRequest);
        when(groupInfoRepository.save(groupInfoRequest)).thenReturn(saveGroupInfo);
        when(groupMapper.groupInfoToGroupDTO(saveGroupInfo)).thenReturn(expectedGroupDTOResponse);


        //When
       GroupDTO actualGroupDTOResponse= groupInfoService.createGroup(groupDTORequest);

        //Then
        assertEquals(expectedGroupDTOResponse,actualGroupDTOResponse);

        //verify if method get called
        verify(groupInfoRepository).save(groupInfoRequest);
        verify(groupMapper).groupDTOToGroupInfo(groupDTORequest);
        verify(groupMapper).groupInfoToGroupDTO(saveGroupInfo);

    }



    @Test
    void testGetGroupByUserName_Success(){

        //Given
        String userName="testusername";
        UserInfo userEntity=new UserInfo();
        userEntity.setUsername("testusername");
        userEntity.setGroupId(1);

        GroupInfo groupInfo =new GroupInfo();
        groupInfo.setGroupId(1);
        groupInfo.setGroupName("Test Group Name");

        GroupDTO expectedGroupDTO=new GroupDTO();
        expectedGroupDTO.setGroupId(1);
        expectedGroupDTO.setGroupName("Test Group Name");

        when(userInfoRepository.findByUsername(userName)).thenReturn(userEntity);
        when(groupInfoRepository.findByGroupId(userEntity.getGroupId())).thenReturn(groupInfo);
        when(groupMapper.groupInfoToGroupDTO(groupInfo)).thenReturn(expectedGroupDTO);


        //When
        GroupDTO actualGroupDTO= groupInfoService.getGroupByUserName(userName);

        //Then
        assertEquals(expectedGroupDTO,actualGroupDTO);

        //verify if method get called
        verify(userInfoRepository).findByUsername(userName);
        verify(groupInfoRepository).findByGroupId(userEntity.getGroupId());
        verify(groupMapper).groupInfoToGroupDTO(groupInfo);

    }*/

}






