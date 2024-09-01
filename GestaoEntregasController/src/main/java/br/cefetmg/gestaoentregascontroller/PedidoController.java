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
import javax.persistence.TypedQuery;

/**
 *
 * @author Master
 */
public class PedidoController {

    PedidoDAO pedidoDAO = new PedidoDAO();

    public void estadoPedido(Pedido pedido) {
        if (pedido.getStatus() == Pedido.Status.EM_PREPARACAO) {
            pedido.setStatus(Pedido.Status.ENTREGA);
        } else if (pedido.getStatus() == Pedido.Status.ENTREGA) {
            pedido.setStatus(Pedido.Status.ENTREGUE);
        }
        pedidoDAO.update(pedido);
    }

    public void salvarPedido(Pedido pedido) {
        pedidoDAO.create(pedido);
    }
    public void atualizarPedido(Pedido pedido){
        pedidoDAO.update(pedido);
    }
    public List<Pedido> listarPedidos() {
        return pedidoDAO.listAll();
    }

    public List<Pedido> listaPedidosStatus(Pedido.Status status) {
        List<Pedido> lista = listarPedidos();
        List<Pedido> listaStatus = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getStatus() == status) {
                listaStatus.add(lista.get(i));
            }
        }
        return listaStatus;
    }
    public List<Pedido> pesquisarPeriodo(Funcionario funcionario, Date startDate, Date endDate) {
        return pedidoDAO.pesquisarPeriodo(funcionario, startDate, endDate);
    }
}
