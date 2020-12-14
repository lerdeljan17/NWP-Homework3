package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.GroupDto;
import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Collection<User> users;

    public static GroupDto groupToDto(Group group){
        GroupDto groupDto = new GroupDto();
        groupDto.setName(group.name);
        groupDto.setId(group.id);
        List<UserDto> userDtos = new ArrayList<>();
        if(group.getUsers() != null && !group.getUsers().isEmpty()){
        for(User u : group.getUsers()){
            userDtos.add(User.userToDto(u));
        }}
        if(!userDtos.isEmpty())
            groupDto.setUsers(userDtos);
        return groupDto;
    }

}
