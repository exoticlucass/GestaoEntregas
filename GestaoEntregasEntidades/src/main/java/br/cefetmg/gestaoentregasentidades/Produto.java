/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private int id;
    @Column(name = "nome_produto")
    private String nome;
    @Column(name = "localizacao_produto")
    private String localização;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_itemPedido_itemPedido")
    private ItemPedido itemPedido;
   
    @Column(name = "valor_unitario")
    private double valorUnitario;
    
     
    public Produto() {
    }

    public Produto(int id, String nome, String localização, ItemPedido itemPedido) {
        this.id = id;
        this.nome = nome;
        this.localização = localização;
        this.itemPedido = itemPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalização() {
        return localização;
    }

    public void setLocalização(String localização) {
        this.localização = localização;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public Produto(String nome, String localização) {
        this.nome = nome;
        this.localização = localização;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
}
