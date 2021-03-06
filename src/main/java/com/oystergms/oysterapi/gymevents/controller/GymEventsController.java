package com.oystergms.oysterapi.gymevents.controller;


import com.oystergms.oysterapi.gymevents.model.GymEvents;
import com.oystergms.oysterapi.gymevents.service.GymEventsService;
import com.oystergms.oysterapi.gymhandler.GymResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class GymEventsController {
    private final GymEventsService gymEventsService;

    public GymEventsController(GymEventsService gymEventsService) {
        this.gymEventsService = gymEventsService;
    }

    @GetMapping("/gymEvents")
    @CrossOrigin
    public ResponseEntity<Object> getAllGymEvents(){

        try {
            List<GymEvents> gymEvents = gymEventsService.getAllGymEvents();
            if(gymEvents.size()<=0){
                return GymResponseHandler.generateResponse("No Events! Please Add Events First !", HttpStatus.OK, null);

            }
            return GymResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, gymEvents);
        } catch (Exception e) {
            return GymResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/gymEvents/{gymEventId}")
    @CrossOrigin
    public ResponseEntity<Object> getGymEventById(@PathVariable("gymEventId") Integer gymEventId){

        try {
            GymEvents gymEvents = gymEventsService.getGymEventById(gymEventId);
            return GymResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, gymEvents);
        } catch (Exception e) {
            return GymResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    @PostMapping("/gymEvents/addEvent")
    @CrossOrigin
    public ResponseEntity<Object> addGymEvent( @RequestBody GymEvents gymEvents) {
        try {
            if (gymEvents.toString().equals("{}")) {
                return GymResponseHandler.generateResponse("Error in Request", HttpStatus.MULTI_STATUS, null);
            }else {
                gymEventsService.addGymEvent(gymEvents);
                return GymResponseHandler.generateResponse("Events Entered Successful.", HttpStatus.OK, gymEvents);
            }
            }catch (Exception e){
            return GymResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @PutMapping("/gymEvents/updateEvent")
    @CrossOrigin
    public ResponseEntity<Object> updateGymEvent( @RequestBody GymEvents gymEvents) {
        try {
            if (gymEvents.toString().equals("{}")) {
                return GymResponseHandler.generateResponse("Error in Request", HttpStatus.MULTI_STATUS, null);
            }else {
                String result = gymEventsService.updateGymEvent(gymEvents);
                return GymResponseHandler.generateResponse("Events Modified Successful.", HttpStatus.OK, result);
            }
        }catch (Exception e){
            return GymResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @DeleteMapping("/gymEvents/deleteEvent/{gymEventId}")
    @CrossOrigin
    public ResponseEntity<Object> deleteGymEvent(@PathVariable("gymEventId") Integer gymEventId){

        try {
            String result = gymEventsService.deleteGymEvent(gymEventId);
            return GymResponseHandler.generateResponse("Event Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return GymResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }



}
