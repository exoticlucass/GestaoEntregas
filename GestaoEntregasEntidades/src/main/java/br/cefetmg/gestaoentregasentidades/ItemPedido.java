/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasentidades;

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
    private Pedido pedido;
    
    
    // Conferir se ta certo no banco de dados
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto_produto")
    private Produto produto;

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

    public int getPedidoId() {
        return pedido.getId();
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
