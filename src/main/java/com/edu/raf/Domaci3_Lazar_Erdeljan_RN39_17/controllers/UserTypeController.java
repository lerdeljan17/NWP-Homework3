package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.controllers;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.UserType;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userType")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping("/all")
    public List<UserType> getAllUserTypes(){
        return this.userTypeService.getAllUserTypes();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserType> updateUserType(@RequestBody UserType newUserType, @PathVariable Long id){
        UserType userType = this.userTypeService.updateUserType(newUserType, id);
        if(userType != null){
            return ResponseEntity.ok(userType);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public UserType createUserType(@RequestBody UserType userType){
        return this.userTypeService.saveUserType(userType);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUserType(@PathVariable Long id){
        if(this.userTypeService.deleteUserTypeById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<UserType> getUserTypeById(@RequestParam("userTypeId") Long id){
        UserType userType = this.userTypeService.getUserTypeById(id);
        if(userType != null){
            return ResponseEntity.ok(userType);
        }
        return ResponseEntity.notFound().build();
    }

}
