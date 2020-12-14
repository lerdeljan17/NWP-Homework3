package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model;

import com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @OneToOne
    @JoinColumn(name = "USERTYPE_ID", referencedColumnName = "ID", nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private Group group;

    public static UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.id);
        userDto.setIme(user.firstname);
        userDto.setPrezime(user.getLastname());
        userDto.setTipKorisnika(user.userType.getName());
        if(user.group != null) userDto.setImeGrupe(user.group.getName());
        return userDto;
    }

}
