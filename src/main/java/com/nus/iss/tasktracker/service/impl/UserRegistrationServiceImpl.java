package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {
/*
    private  final UserInfoRepository userInfoRepository;
    private final GroupInfoService groupInfoService;
    private final UserMapper userMapper;

@Autowired

    public UserRegistrationServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper, GroupInfoService groupInfoService) {
        this.userInfoRepository = userInfoRepository;
        this.groupInfoService = groupInfoService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(int userid) {
        log.info("getUserById called in UserRegistrationServiceImpl with id {}", userid);

        // FIXME - UNCOMMENT THE BELOW LINE ONCE userInfoRepository CODE IS WRITTEN
        // UserEntity userEntity = userInfoRepository.getUserInfoById(id);

        // FIXME - REMOVE THE BELOW DUMMY LINES OF CODE ONCE userInfoRepository CODE IS WRITTEN
        // DUMMY CODE START
        UserInfo userEntity = new UserInfo();
        userEntity.setUserId(userid);
       userEntity.setUserId(1);
       // userEntity.setUserRole("ADMIN");
        // DUMMY CODE END

        log.debug("User Entity {}",userEntity);
        UserDTO userDTO = userMapper.userEntityToUserDTO(userEntity);
        log.debug("User DTO {}",userDTO.toString());
        return userDTO;
    }
    @Override
    public void changePassword(UserDTO requestDTO){

        UserInfo userEntity = userInfoRepository.findByUsername(requestDTO.getUsername());
        if(userEntity == null){
            throw new RuntimeException("Make sure the username is correct!");
        }
        if (!userEntity.getPassword().equals(requestDTO.getOldPassword())) {
            throw new RuntimeException("Old password does not match!");
        }

        String newPassword = requestDTO.getNewPassword();

        if (newPassword == null || newPassword.length() < 8) {
            throw new RuntimeException("Password must meet the min. length of 8!");
        }
        log.info("New Password Regex check: {}", newPassword.matches(".*[a-zA-Z].*\\d.*"));
        if (!newPassword.matches(".*[a-zA-Z].*\\d.*")) {
            throw new RuntimeException("Password must contain alphanumeric characters!");
        }
        // Update userEntity with new password
        userEntity.setPassword(newPassword);
        userInfoRepository.save(userEntity);

    }

    // THIS SIGNUP METHOD IS USED FOR BOTH ADMIN REGISTRATION DONE FROM PRE LOGIN AND FOR USER REGISTRATION DONE FROM POST LOGIN BY ADMIN
    @Override
    public UserDTO signUp(UserDTO requestDTO){

        String loggedInUserName = TaskTrackerInterceptor.getLoggedInUserName();
        log.info("loggedInUserName: {}",loggedInUserName);

        if(!StringUtils.hasText(requestDTO.getName())){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Name"));
        }

        if(!StringUtils.hasText(requestDTO.getEmail())){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Email"));
        }

        if(!StringUtils.hasText(requestDTO.getUserRole())){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "User Role"));
        }

        if(!StringUtils.hasText(requestDTO.getGroupName())){
            // THROW THE ERROR IF THE GROUP NAME IS EMPTY AND SIGNUP IS HAPPENING FROM PRE-LOGIN
            if (!StringUtils.hasText(loggedInUserName)){
                throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Group Name"));
            }
        }else {
            if ( requestDTO.getGroupName().length() < 6 || !requestDTO.getGroupName().matches("^(?! )[0-9A-Za-z](?!.* $)[0-9A-Za-z\\s]{0,18}(?<! )$")) {
                throw new RuntimeException(TaskTrackerConstant.SIGNUP_INVALID_GROUP_NAME);
            }
        }

        if(!StringUtils.hasText(requestDTO.getUsername())){
                throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Username"));
        }

        if(!StringUtils.hasText(requestDTO.getPassword())){
            // THROW THE ERROR IF THE PASSWORD IS EMPTY AND SIGNUP IS HAPPENING FROM PRE-LOGIN
            if (!StringUtils.hasText(loggedInUserName)) {
                throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Password"));
            }
        } else {
            if (requestDTO.getPassword().length() < 8 || !requestDTO.getPassword().matches(".*[a-zA-Z].*\\d.*")) {
                throw new RuntimeException(TaskTrackerConstant.SIGNUP_INVALID_INPUT_PASSWORD);
            }
        }

        boolean isExists = userInfoRepository.existsByUsername(requestDTO.getUsername());
        if(isExists){
            throw new RuntimeException(TaskTrackerConstant.SIGNUP_INVALID_INPUT_USERNAME_UNAVAILABLE);
        }

        log.info("requestDTO: {}",requestDTO);
        GroupDTO groupDTOResponse = null;

        if (!StringUtils.hasText(loggedInUserName)){
            // STORING GROUP FIRST TO GET GROUP ID
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setGroupName(requestDTO.getGroupName());
            groupDTO.setGroupDescription(requestDTO.getGroupName());
            groupDTO.setCreatedBy(TaskTrackerConstant.TASK_ADMIN);
            groupDTO.setModifiedBy(TaskTrackerConstant.TASK_ADMIN);
            groupDTO.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
            log.info("groupDTO: {}",groupDTO);
            groupDTOResponse = groupInfoService.createGroup(groupDTO);
        } else{
            // GET GROUP DETAILS FROM DB FOR THE ADMIN AND USE IT FOR THE NEWLY CREATED USER
            groupDTOResponse = groupInfoService.getGroupByUserName(loggedInUserName);

            // SETTING PASSWORD FOR THE SIGN-UP HAPPENING FROM POST LOGIN
            requestDTO.setPassword(TaskTrackerConstant.TASK_USER_ADMIN_DEFAULT_CRED);
        }
        log.info("groupDTOResponse: {}",groupDTOResponse);


        // DO THE CHECK ONLY FOR POST-LOGIN SIGNUPs BECAUSE PRE-LOGIN SIGNUPs CREATES THE GROUP FRESHLY
        if (StringUtils.hasText(loggedInUserName)) {
            // CHECKING WHETHER THE GROUP HAVE REACHED MAX TEAM MEMBERS OR MAX ADMIN MEMBERS
            int nonAdminsInGroup = 0;
            int adminsInGroup = 0;
            int totalUsersInGroup = 0;
            List<Object[]> queryResponseObject = userInfoRepository.findGroupMembersListByGroupId(groupDTOResponse.getGroupId());
            for (Object[] record : queryResponseObject) {
                // column index : 0 ROLE_ADMIN, 1 ROLE_USER, 2 TOTAL_USERS
                adminsInGroup = ((Integer) record[0]);
                nonAdminsInGroup = ((Integer) record[1]);
                totalUsersInGroup = ((Integer) record[2]);
            }

            log.info("In Group {}, we have {} non-admins, {} admins and in total {} users.", groupDTOResponse.getGroupId(), nonAdminsInGroup, adminsInGroup, totalUsersInGroup);
            boolean canAddUserToGroup = true;
            if (requestDTO.getUserRole().equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                if (adminsInGroup >= 2) {
                    canAddUserToGroup = false;
                }
            }
            if (totalUsersInGroup >= TaskTrackerConstant.TASK_GROUP_MAX_NO) {
                canAddUserToGroup = false;
            }
            if (!canAddUserToGroup) {
                throw new RuntimeException(TaskTrackerConstant.SIGNUP_GROUP_MEMBER_ADDITION_VALIDATION_FAILED);
            }
        }

        UserInfo userEntity=userMapper.userDTOToUserInfo(requestDTO);
        log.info("userEntity: {}",userEntity);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        userEntity.setGroupId(groupDTOResponse.getGroupId());
        userEntity.setCreatedBy(TaskTrackerConstant.TASK_ADMIN);
        userEntity.setCreatedDate(timestamp);
        userEntity.setModifiedBy(TaskTrackerConstant.TASK_ADMIN);
        userEntity.setModifiedDate(timestamp);
        userEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);

        // FOR THE USERS CREATED UNDER POST-LOGIN, PASSWORD CHANGE IS MANDATORY SINCE, DEFAULT PASSWORD IS ASSIGNED TO THOSE USERS
        if (StringUtils.hasText(loggedInUserName)) {
            userEntity.setPasswordChangeMandatory(TaskTrackerConstant.TASK_PWD_CHANGE_MANDATORY_TRUE);
        } else{
            // FOR THE NORMAL USER SIGN-UPs, PASSWORD CHANGE IS NON-MANDATORY
            userEntity.setPasswordChangeMandatory(TaskTrackerConstant.TASK_PWD_CHANGE_MANDATORY_FALSE);
        }

        UserDTO output= userMapper.userEntityToUserDTO(userInfoRepository.save(userEntity));
        output.setPassword(null);
        output.setOldPassword(null);
        output.setNewPassword(null);
        output.setAuthToken(null);

        return output;
    }

    @Override
    public List<UserDTO> getAllUsersInAGroup() {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();
        log.info("userName: {}; userRole: {}", userName, userRole);

        if(!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)){
            throw new RuntimeException("Service Accessed Without Token");
        }

        if(userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)){
            UserInfo currentUserInfo = userInfoRepository.findByUsername(userName);
            if(currentUserInfo!=null){
                List<UserInfo> userInfoList = userInfoRepository.findAllByGroupIdAndDeleteFlag(currentUserInfo.getGroupId(), TaskTrackerConstant.DELETE_FLAG_FALSE);
                log.info("userInfoList {}", userInfoList);

                for(UserInfo userInfo: userInfoList){
                    UserDTO output= userMapper.userEntityToUserDTO(userInfo);
                    output.setPassword(null);
                    userDTOList.add(output);
                }
            } else{
                throw new RuntimeException("User Info unavailable in DB");
            }
        } else{
            throw new RuntimeException("Service Accessed by Non-Admin");
        }

        return  userDTOList;
    }

    // BELOW METHOD IS FOR UPDATE USER INFO
    // THE FIELDS -> USERNAME, GROUP ID & USER ROLE CANNOT BE CHANGED
    @Override
    public UserDTO updateUserInfo(UserDTO requestDTO) {
        String loggedInUserName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();
        log.info("userName: {}; userRole: {}", loggedInUserName, userRole);
        log.info("requestDTO: {}",requestDTO);

        if(userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            UserDTO currentUserDTO = null;
            if (requestDTO.getUserId() != null) {
                Optional<UserInfo> currentUserInfo = userInfoRepository.findById(requestDTO.getUserId());
                if (currentUserInfo.isPresent()) {
                    currentUserDTO = userMapper.userEntityToUserDTO(currentUserInfo.get());
                }
                if (currentUserDTO == null) {
                    throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "User ID"));
                }
            } else {
                throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "User ID"));
            }

            if (StringUtils.hasText(requestDTO.getName())) {
                currentUserDTO.setName(requestDTO.getName());
            }

            if (StringUtils.hasText(requestDTO.getEmail())) {
                currentUserDTO.setEmail(requestDTO.getEmail());
            }

            // SETTING DEFAULT PASSWORD IF THE PASSWORD CHANGE MANDATORY FLAG IS TRUE
            if (requestDTO.getPasswordChangeMandatory().equals(TaskTrackerConstant.TASK_PWD_CHANGE_MANDATORY_TRUE)) {
                currentUserDTO.setPassword(TaskTrackerConstant.TASK_USER_ADMIN_DEFAULT_CRED);
                currentUserDTO.setPasswordChangeMandatory(TaskTrackerConstant.TASK_PWD_CHANGE_MANDATORY_TRUE);
            }

            if (requestDTO.getDeleteFlag().equals(TaskTrackerConstant.DELETE_FLAG_TRUE)) {
                currentUserDTO.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_TRUE);
            }

            UserInfo userEntity = userMapper.userDTOToUserInfo(currentUserDTO);
            log.info("userEntity : {}", userEntity);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            userEntity.setModifiedBy(TaskTrackerConstant.TASK_ADMIN);
            userEntity.setModifiedDate(timestamp);
            if (requestDTO.getDeleteFlag().equals(TaskTrackerConstant.DELETE_FLAG_TRUE)) {
                if(loggedInUserName.equals(currentUserDTO.getUsername())){
                    System.out.println("loggedInUserName: "+loggedInUserName);
                    System.out.println("currentUserDTO.getUsername(): "+currentUserDTO.getUsername());
                    log.info("Overriding Delete flag for the ADMIN User - if the user accidentally tries to delete own account");
                } else{
                    userEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_TRUE);
                }

            }

            UserDTO output = userMapper.userEntityToUserDTO(userInfoRepository.save(userEntity));
            output.setPassword(null);
            output.setOldPassword(null);
            output.setNewPassword(null);
            output.setAuthToken(null);

            //FIXME - CHECK THE CREATED DATE & MODIFIED DATE VALUE MISSING IN DTO

            return output;
        } else{
            throw new RuntimeException("Service Accessed by Non-Admin");
        }
    }*/

}
