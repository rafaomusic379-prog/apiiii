package com.example.demo.controller;

import com.example.demo.DTO.ProdutoPreviewDTO;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {



    @GetMapping
    public ResponseEntity<List<Produto>> listar(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isBlank()) {
            List<Produto> filtrados = catalogo.stream()
                    .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .toList();
            return ResponseEntity.ok(filtrados);
        }
        return ResponseEntity.ok(catalogo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return catalogo.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody ProdutoDTO dto) {
        Produto novoProduto = new Produto(proximoId++, dto.nome(), dto.preco());
        catalogo.add(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto){
        for (Produto p : catalogo){
            if (p.getId().equals(id)){
                p.setNome(dto.nome);
                p.setValor(dto.preco);
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        boolean removeu = catalogo.removeIf(p -> p.getId().equals(id));
        if (removeu){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}