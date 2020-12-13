package com.edu.raf.Domaci3_Lazar_Erdeljan_RN39_17.model.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String ime;
    private String prezime;
    private String tipKorisnika;
    private String imeGrupe;
}
