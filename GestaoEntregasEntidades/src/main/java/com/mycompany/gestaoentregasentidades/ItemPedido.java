/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private int id;
    @Column(name = "valor_unitario")
    private double valorUnitario;
    @Column(name = "quantidade")
    private int quantidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido_pedido")
    public Pedido pedido;
    
    
    // Talvez tenha que mudar nome
    @OneToMany(fetch = FetchType.EAGER, cascade
            = CascadeType.PERSIST, mappedBy = "itemPedido")
    private List<Produto> produto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
    
    
}
