package edu.rafael.desafiospringboot.controller;

import edu.rafael.desafiospringboot.model.Produto;
import edu.rafael.desafiospringboot.model.ProdutoDTO;
import edu.rafael.desafiospringboot.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity getAll(){
        var produtos = repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Optional<Produto> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()){
            return ResponseEntity.ok(produtoOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid ProdutoDTO dto){
        Produto produto = new Produto(dto);
        repository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Produto> update(@RequestBody @Valid ProdutoDTO dto){
        Optional<Produto> produtoOptional = repository.findById(dto.getId());
        if (produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            produto.setNome(dto.getNome());
            produto.setPrecoCentavos(dto.getPrecoCentavos());
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }
}
