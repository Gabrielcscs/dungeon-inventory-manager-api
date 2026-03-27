package br.ufba.gabriel.dungeon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Item> itens;
}
