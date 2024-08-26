/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasentidades.*;
import br.cefetmg.gestaoentregasdao.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Master
 */
public class PedidoController {

    PedidoDAO pedidoDAO = new PedidoDAO();
    
    public void estadoPedido(Pedido pedido){
        if(pedido.getStatus() == Pedido.Status.EM_PREPARACAO){
            pedido.setStatus(Pedido.Status.ENTREGA);
        }
        else if(pedido.getStatus() == Pedido.Status.ENTREGA){
            pedido.setStatus(Pedido.Status.ENTREGUE);
        }
        pedidoDAO.modificar(pedido);
    }
    
    
    public boolean salvarPedido(Produto produto, int quantidade, double valorUnitario, double valorTotal, String marca, String formaPagamento, String endereco, String observacoes) {

        Pedido pedido = new Pedido();
        ItemPedido itemPedido = new ItemPedido();
        pedido.setStatus(Pedido.Status.EM_PREPARACAO);
        pedido.setValorTotal(valorTotal);

        java.util.Date date = new java.util.Date();   
        pedido.setData(date);

        List<Produto> listproduto = new ArrayList<>();
        listproduto.add(produto);

        itemPedido.setProduto(listproduto);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setValorUnitario(valorUnitario);
        itemPedido.setPedido(pedido);

        List<ItemPedido> listItem = new ArrayList<>();
        listItem.add(itemPedido);
        pedido.setItemPedido(listItem);

        pedidoDAO.inserir(pedido);
        return true;
    }
    
    
}
