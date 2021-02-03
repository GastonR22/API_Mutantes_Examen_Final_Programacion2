package com.example.mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MutanteDto implements Serializable {

    private String[] dna;


}
