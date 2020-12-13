package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.impl;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.User;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.UserType;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories.UserRepository;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.repositories.UserTypeRepository;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services.UserTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;
    @Autowired
    private  UserRepository userRepository;

    @Override
    public List<UserType> getAllUserTypes() {
        return this.userTypeRepository.findAll();
    }

    @Override
    public UserType getUserTypeById(Long id) {
        Optional<UserType> optionalUserType = this.userTypeRepository.findById(id);
        if(optionalUserType.isPresent()){
            UserType userType = optionalUserType.get();
            return  userType;
        }
        return  null;
    }

    @Override
    public UserType saveUserType(UserType userType) {
        return this.userTypeRepository.save(userType);
    }

    @Override
    public boolean deleteUserTypeById(Long id) {
        Optional<UserType> optionalUserType = this.userTypeRepository.findById(id);
        if(optionalUserType.isPresent()){
            List<User> users = this.userRepository.findAll();
            for(User u : users) if(u.getUserType().getId().equals(id)) this.userRepository.deleteById(u.getId());
            this.userTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserType updateUserType(UserType newUserType, Long id) {
        
        Optional<UserType> optionalUserType = this.userTypeRepository.findById(id);
        if(optionalUserType.isPresent()){
            UserType userType = optionalUserType.get();
            userType.setName(newUserType.getName());
            this.userTypeRepository.save(userType);

            return userType;
        }
        return null;
    }

}
