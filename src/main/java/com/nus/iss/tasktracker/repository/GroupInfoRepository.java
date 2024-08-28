package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Integer> {
    GroupInfo findByGroupId(int groupId);

}
