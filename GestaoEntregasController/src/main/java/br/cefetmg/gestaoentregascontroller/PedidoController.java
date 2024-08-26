/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.PedidoDAO;
import br.cefetmg.gestaoentregasentidades.*;

/**
 *
 * @author Master
 */
public class PedidoController {

    public PedidoDAO dao;

    public PedidoController() {
        dao = new PedidoDAO();
    }

    public void inserir(Pedido pedido) {
        dao.inserir(pedido);
    }
}
