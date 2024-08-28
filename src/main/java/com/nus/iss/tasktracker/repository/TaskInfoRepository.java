package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.model.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository

public interface TaskInfoRepository extends  JpaRepository<TaskInfo, Integer> {


    @Query(value =
            "select a.task_assignee, sum(a.task_reward_point) as task_reward_point " +
            "from task_info a " +
            "where a.delete_flag = 'FALSE' and a.task_status='Complete' and a.task_assignee= :userId "+
            "group by task_assignee ", nativeQuery = true)
    List<Object[]> findTaskRewardPointsByGroupId(@Param("userId") Integer userId);

    List<Object[]> findAllByDeleteFlag(String deleteFlag);
    List<Object[]> findAllByDeleteFlagAndTaskAssignee(String deleteFlag, int userId);

    List<Object[]> findAllByDeleteFlagAndTaskAssigneeAndTaskDueDateLessThanEqualAndTaskStatusNot(String deleteFlag,int taskAssignee,Timestamp taskDueDate, String taskStatus);

    List<Object[]> findAllByDeleteFlagAndTaskDueDateLessThanEqualAndTaskStatusNot(String deleteFlag,Timestamp taskDueDate, String taskStatus);

    Optional<TaskInfo> findByDeleteFlagAndTaskId(String deleteFlag, int taskId );

}







