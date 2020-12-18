package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.impl;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.Group;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.User;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.GroupDto;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories.GroupRepository;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories.UserRepository;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.GroupService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<GroupDto> getAllGroups() {
        List<Group> groups = this.groupRepository.findAll();
        List<GroupDto> groupDtoList = new ArrayList<>();
        for (Group g : groups) {
            groupDtoList.add(Group.groupToDto(g));
        }
        return groupDtoList;
    }

    @Override
    public GroupDto getGroupById(Long id) {
        Optional<Group> optionalGroup = this.groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            return Group.groupToDto(group);
        }
        return null;
    }

    @Override
    public Group saveGroup(Group group) {
        return this.groupRepository.save(group);
    }

    @Override
    public boolean deleteGroupById(Long id) {
        Optional<Group> optionalGroup = this.groupRepository.findById(id);

        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            for (User u : group.getUsers()) u.setGroup(null);
            this.groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Group updateGroup(Group newGroup, Long id) {
        Optional<Group> optionalGroup = this.groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            this.groupRepository.setGroupInfoByID(newGroup.getName(), id);
            return newGroup;
        }
        return null;
    }

    @Override
    public boolean addUser(Long groupId, Long userId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
      if( optionalGroup.isPresent()){
          Group group = optionalGroup.get();
          Optional<User> optionalUser = userRepository.findById(userId);
          if( optionalUser.isPresent()){
              User user = optionalUser.get();
              group.getUsers().add(user);
              user.setGroup(group);
              groupRepository.flush();
              userRepository.flush();
              return true;
          }

          }
      return false;
    }

    @Override
    public boolean removeUser(Long groupId, Long userId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if( optionalGroup.isPresent()){
            Group group = optionalGroup.get();
            Optional<User> optionalUser = userRepository.findById(userId);
            if( optionalUser.isPresent()){
                User user = optionalUser.get();
                group.getUsers().remove(user);
                user.setGroup(null);
                groupRepository.flush();
                userRepository.flush();
                return true;
            }

        }
        return false;
    }

}
