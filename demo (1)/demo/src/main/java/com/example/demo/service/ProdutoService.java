package com.example.demo.service;

import com.example.demo.controller.ProdutoDTO;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar(String termoBusca) {
        List<Produto> todos = repository.findAll();
        if (termoBusca != null && !termoBusca.isBlank()){
            return todos.stream()
                    .filter(p -> p.getNome().toLowerCase().contains(termoBusca.toLowerCase()))
                    .toList();
        }
        return todos;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Produto cadastrar(ProdutoDTO dto){
        if (dto.getPreco() <= 0){
            throw new IllegalArgumentException()
        }
    }
}