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

    public GroupDto groupToDto(){
        GroupDto groupDto = new GroupDto();
        groupDto.setName(this.name);
        groupDto.setId(this.id);
        List<UserDto> userDtos = new ArrayList<>();
        if(users != null && !users.isEmpty()){
        for(User u : this.getUsers()){
            userDtos.add(u.userToDto());
        }}
        if(!userDtos.isEmpty())
            groupDto.setUsers(userDtos);
        return groupDto;
    }

}
