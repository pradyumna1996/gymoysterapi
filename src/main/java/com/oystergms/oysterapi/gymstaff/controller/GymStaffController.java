package com.oystergms.oysterapi.gymstaff.controller;

import com.oystergms.oysterapi.gymhandler.GymResponseHandler;
import com.oystergms.oysterapi.gymmember.model.GymMember;
import com.oystergms.oysterapi.gymstaff.model.GymStaff;
import com.oystergms.oysterapi.gymstaff.service.GymStaffRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class GymStaffController {

    private final GymStaffRepositoryService gymStaffRepositoryService;

    public GymStaffController(GymStaffRepositoryService gymStaffRepositoryService) {
        this.gymStaffRepositoryService = gymStaffRepositoryService;
    }

    @GetMapping("/gymStaffs")
    @CrossOrigin
    public ResponseEntity<Object> getAllStaffs(){

        List<GymStaff> gymStaffs = gymStaffRepositoryService.getAllGymStaffs();
        if (gymStaffs.size()<=0){
            return GymResponseHandler.generateResponse("No Staffs in the List", HttpStatus.OK,null);
        }
        else{
            return  GymResponseHandler.generateResponse("Staffs Fetched Successfully !",HttpStatus.OK,gymStaffs);
        }
    }

    @GetMapping("/gymStaffs/{gymStaffId}")
    @CrossOrigin
    public ResponseEntity<Object> getStaffById(@PathVariable("gymStaffId") Integer gymStaffId){

        GymStaff gymStaff = gymStaffRepositoryService.getGymStaffById(gymStaffId);
        if (gymStaff==null){
            return GymResponseHandler.generateResponse("No Staff with such id", HttpStatus.OK,null);
        }
        else{
            return  GymResponseHandler.generateResponse("Staffs Fetched Successfully !",HttpStatus.OK,gymStaff);
        }
    }



    @PostMapping("/gymStaffs/addStaff")
    @CrossOrigin
    public ResponseEntity<Object>  addGymStaff( @RequestBody GymStaff gymStaff) {

        if (gymStaff.toString().equals("{}")) {
            return GymResponseHandler.generateResponse("Form is Wrong Filled", HttpStatus.OK, null);
        }
        else if (gymStaff == null) {
            return GymResponseHandler.generateResponse("Something is wrong", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
        gymStaffRepositoryService.addGymStaff(gymStaff);
        return GymResponseHandler.generateResponse("Staff Added Successfully !", HttpStatus.OK, gymStaff);
    }

    @PutMapping("/gymStaffs/updateStaff")
    @CrossOrigin
    public ResponseEntity<Object>  updateGymStaff( @RequestBody GymStaff gymStaff) {

        if (gymStaff.toString().equals("{}")) {
            return GymResponseHandler.generateResponse("Form is Wrong Filled", HttpStatus.OK, null);
        }
        else if (gymStaff == null) {
            return GymResponseHandler.generateResponse("Something is wrong", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
        String result = gymStaffRepositoryService.updateGymStaff(gymStaff);
        return GymResponseHandler.generateResponse("Staff Updated Successfully !", HttpStatus.OK, result
        );
    }


    @DeleteMapping("/gymStaffs/deleteGymStaff/{gymStaffId}")
    @CrossOrigin
    public ResponseEntity<Object> deleteGymStaffById(@PathVariable("gymStaffId") Integer gymStaffId){

        try {
            String result = gymStaffRepositoryService.deleteGymStaffById(gymStaffId);
            return GymResponseHandler.generateResponse("Selected Staff is Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return GymResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
