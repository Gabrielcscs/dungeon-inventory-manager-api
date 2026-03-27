package br.ufba.gabriel.dungeon.repository;

import br.ufba.gabriel.dungeon.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUsuarioId(Long UsuarioId);

}
