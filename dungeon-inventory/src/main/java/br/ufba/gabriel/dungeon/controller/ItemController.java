package br.ufba.gabriel.dungeon.controller;

import br.ufba.gabriel.dungeon.model.Item;
import br.ufba.gabriel.dungeon.model.Usuario;
import br.ufba.gabriel.dungeon.repository.ItemRepository;
import br.ufba.gabriel.dungeon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin(origins= "*")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @DeleteMapping("/{usuarioId}/{idItem}")
    public ResponseEntity<String> removerItem(@PathVariable Long usuarioId, @PathVariable Long idItem){
        Item   itemDoBanco = itemRepository.findById(idItem).orElse(null);
        if(itemDoBanco != null && itemDoBanco.getUsuario().getId().equals(usuarioId)){
            itemRepository.deleteById(idItem);
            return ResponseEntity.ok("Item Removido com sucesso");
        }else{
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Item não existe");
        }
    }

    @GetMapping("usuario/{usuarioId}")
    public List<Item> lista(@PathVariable Long usuarioId){
        Usuario donoDoItem = usuarioRepository.findById(usuarioId).orElse(null);
        if (donoDoItem != null){
            return itemRepository.findByUsuarioId(usuarioId);
        }else{return null;}

    }

    @PostMapping("/usuario/{usuarioId}")
    public Item adicionarItem(@PathVariable Long usuarioId, @RequestBody Item novoItem) {
        Usuario donoDoItem = usuarioRepository.findById(usuarioId).orElse(null);

        if (donoDoItem != null) {
            novoItem.setUsuario(donoDoItem);
            return itemRepository.save(novoItem);
        } else {
            return null;
        }
    }

}
