package com.nus.iss.tasktracker.repository;
import com.nus.iss.tasktracker.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>
        {
            // Implement findByUsername method to interact with the database or any data store
            UserInfo findByUsername(String username);
            UserInfo findByUsernameAndPasswordAndDeleteFlag (String username,String password,String deleteFlag);
            boolean existsByUsername(String username);
            List<UserInfo> findAllByGroupIdAndDeleteFlag(int groupId,String deleteFlag);


            @Query(value = "SELECT\n" +
                    "            SUM(CASE WHEN role = 'ROLE_ADMIN' THEN 1 ELSE 0 END) AS ROLE_ADMIN,\n" +
                    "            SUM(CASE WHEN role = 'ROLE_USER' THEN 1 ELSE 0 END) AS ROLE_USER,\n" +
                    "            COUNT(*) AS TOTAL_USERS\n" +
                    "            FROM\n" +
                    "                    user_info\n" +
                    "            WHERE\n" +
                    "                    group_id = :groupId\n" +
                    "            AND delete_flag = 'FALSE';", nativeQuery = true)
            List<Object[]> findGroupMembersListByGroupId(@Param("groupId") Integer groupId);

            UserInfo findByUserId(Integer userId);


}
