package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.controllers;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.Group;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.GroupDto;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    public List<GroupDto> getAllGroups(){
        return this.groupService.getAllGroups();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group newGroup, @PathVariable Long id){
        Group group = this.groupService.updateGroup(newGroup, id);
        if(group != null){
            return ResponseEntity.ok(group);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Group createGroup(@RequestBody Group group){
        return this.groupService.saveGroup(group);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id){
        if(this.groupService.deleteGroupById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<GroupDto> getGroupById(@RequestParam("groupId") Long id){
        GroupDto group = this.groupService.getGroupById(id);
        if(group != null){
            return ResponseEntity.ok(group);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestParam("groupId") Long groupId,@RequestParam("userId") Long userId){
        if(this.groupService.addUser(groupId,userId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/removeUser")
    public ResponseEntity<?> removeUser(@RequestParam("groupId") Long groupId,@RequestParam("userId") Long userId){
        if(this.groupService.removeUser(groupId,userId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
