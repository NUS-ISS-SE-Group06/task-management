package com.nus.iss.tasktracker.controller;


// import com.nus.iss.tasktracker.dto.GroupDTO;
// import com.nus.iss.tasktracker.dto.Response;
// import com.nus.iss.tasktracker.service.GroupInfoService;
// import com.nus.iss.tasktracker.util.CustomResponseHandler;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-info")
@Slf4j
public class GroupInfoController {

/*
    private final GroupInfoService groupInfoService;

    @Autowired
    public GroupInfoController(GroupInfoService groupInfoService) {
        this.groupInfoService = groupInfoService;
    }
*/


  /*  @GetMapping("/{id}")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public ResponseEntity<Response> getGroupById(@PathVariable int id) throws RuntimeException{
        log.info("Group endpoint called with id {}", id);
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        GroupDTO groupDTO = groupInfoService.getGroupById(id);

        if (groupDTO !=  null){
            responseBody = groupDTO;
            successOrFailMessage = "Group Info Retrieved successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Group Info Retrieval Failed.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }*/

  /*  @PostMapping("/create")
    @CrossOrigin(origins = {"http://165.22.100.234:3000", "http://localhost:3000"})
    public ResponseEntity<Response> createGroup(@RequestBody GroupDTO requestDTO) throws RuntimeException {
        log.info("requestDTO: "+requestDTO);
        log.info("requestDTO updated: "+requestDTO);
        GroupDTO groupDTO= groupInfoService.createGroup(requestDTO);
        log.info("groupDTO : {}", groupDTO);
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        if (groupDTO !=  null){
            responseBody = groupDTO;
            successOrFailMessage = "Group Created successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Group Creation Failed.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }*/

}
