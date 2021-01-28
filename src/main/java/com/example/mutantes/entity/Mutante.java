package com.example.mutantes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Mutante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mutante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dna")
    private String[] dna;


}
