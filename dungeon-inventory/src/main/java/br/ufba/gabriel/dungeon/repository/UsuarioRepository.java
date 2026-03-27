package br.ufba.gabriel.dungeon.repository;

import br.ufba.gabriel.dungeon.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String  username);
}
