package com.example.demo.controller;

public class ProdutoDTO {

    String nome;
    double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String nome() {
        return nome;
    }

    public double preco() {
        return preco;
    }
}
