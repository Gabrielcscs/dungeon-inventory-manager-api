package br.ufba.gabriel.dungeon.controller;

import br.ufba.gabriel.dungeon.model.Item;
import br.ufba.gabriel.dungeon.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin(origins= "*")
public class ItemController {
    @Autowired
    private ItemRepository repository;

    @DeleteMapping("/{idItem}")
    public  void removerItem(@PathVariable Long idItem){
        repository.deleteById(idItem);
    }

    @GetMapping
    public List<Item> lista(){
        return repository.findAll();
    }

    @PostMapping
    public Item adicionar(@RequestBody Item novoItem){
        return repository.save(novoItem);
    }

}
