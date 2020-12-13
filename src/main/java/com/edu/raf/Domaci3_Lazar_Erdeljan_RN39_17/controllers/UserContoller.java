package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.controllers;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.User;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.UserDto;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserContoller{

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User nUser, @PathVariable Long id){
        User user = this.userService.updateUser(nUser, id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userService.saveUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(this.userService.deleteUserById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam("userId") Long id){
        UserDto user = this.userService.getUserById(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<User> findUsersByFirstnameOrLastname(@RequestParam(value = "name",required = false) String name,@RequestParam(value = "surname",required = false)String surname,@RequestParam(value = "groupId",required = false)Long groupId,@RequestParam(value = "userTypeId",required = false)Long userTypeId ){
        return this.userService.searchUsers(name,surname,groupId,userTypeId);
    }



}
