package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.services;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.Group;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.GroupDto;

import java.util.List;

public interface GroupService {

    public List<GroupDto> getAllGroups();

    public GroupDto getGroupById(Long id);

    public Group saveGroup(Group group);

    public boolean deleteGroupById(Long id);

    public Group updateGroup(Group newGroup, Long id);

}
