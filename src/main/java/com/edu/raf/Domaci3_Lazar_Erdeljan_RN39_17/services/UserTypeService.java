package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.UserType;

import java.util.List;

public interface UserTypeService {

    public List<UserType> getAllUserTypes();

    public UserType getUserTypeById(Long id);

    public UserType saveUserType(UserType userType);

    public boolean deleteUserTypeById(Long id);

    public UserType updateUserType(UserType newUserType, Long id);
}
