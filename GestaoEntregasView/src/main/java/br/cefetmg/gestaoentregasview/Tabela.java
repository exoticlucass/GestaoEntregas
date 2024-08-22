/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasview;
import java.io.*;
import java.util.ArrayList;

public class Tabela {
    private String nome;
    private String quantidade;
    private String endereco;
    private String valorUnitario;
    private String valorTotal;
    private String marca;
    private String formaPagamento;
    private String observacoes;
    private String entregador;

    public Tabela(String nome, String quantidade, String endereco, String valorUnitario, String valorTotal, String marca, String formaPagamento, String observacoes, String entregador) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.endereco = endereco;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.marca = marca;
        this.formaPagamento = formaPagamento;
        this.observacoes = observacoes;
        this.entregador = entregador;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getEntregador() {
        return entregador;
    }

    public void setEntregador(String entregador) {
        this.entregador = entregador;
    }
    
}
