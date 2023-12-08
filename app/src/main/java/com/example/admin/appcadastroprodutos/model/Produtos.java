package com.example.admin.appcadastroprodutos.model;

import java.io.Serializable;

public class Produtos implements Serializable {

    private Long id;
    private String nomeProduto;
    private String Descricao;
    private int quantidade;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nomeProduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nomeProduto = nome;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        String text = "";
        text += "Nome: " + this.nomeProduto +
                "Quantidade: " + this.quantidade;

        return text;
    }
}
