package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.User;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getAllUsers();

    public UserDto getUserById(Long id);

    public User saveUser(User user);

    public boolean deleteUserById(Long id);

    public User updateUser(User nUser, Long id);

    List<User> searchUsers(String name,String surname,Long groupId, Long userTypeId);


}
