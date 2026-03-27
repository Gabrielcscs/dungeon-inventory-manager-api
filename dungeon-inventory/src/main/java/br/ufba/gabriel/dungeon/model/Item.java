package br.ufba.gabriel.dungeon.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
