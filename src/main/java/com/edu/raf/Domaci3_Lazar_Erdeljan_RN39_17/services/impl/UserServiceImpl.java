package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.impl;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.User;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.UserDto;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories.UserRepository;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(user ->{
                    UserDto userDto = UserDto.builder().
                            id(user.getId())
                            .ime(user.getFirstname())
                            .prezime(user.getLastname())
                            .tipKorisnika(user.getUserType().getName())
                            .build();
                    if(user.getGroup() != null)
                        userDto.setImeGrupe(user.getGroup().getName());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDto userDto = UserDto.builder().id(user.getId())
                    .ime(user.getFirstname())
                    .prezime(user.getLastname())
                    .tipKorisnika(user.getUserType().getName())
                    .build();
            if(user.getGroup() != null)
                userDto.setImeGrupe(user.getGroup().getName());
            return userDto;
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()){
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(User user, Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()){
            this.userRepository.setUserInfoById(user.getFirstname(), user.getLastname(), user.getUserType(), user.getGroup(), id);
            return user;
        }
        return null;
    }

    @Override
    public List<User> searchUsers(String name, String surname, Long groupId, Long userTypeId) {
        return userRepository.searchUsers(name,surname,groupId,userTypeId);
    }


}
