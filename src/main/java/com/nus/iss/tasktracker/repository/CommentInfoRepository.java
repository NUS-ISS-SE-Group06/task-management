package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.TaskComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentInfoRepository extends JpaRepository<TaskComments, Integer> {

    List<TaskComments> findByTaskId(int taskId);

}
