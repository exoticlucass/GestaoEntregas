package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.ItemPedidoDAO;
import br.cefetmg.gestaoentregasentidades.ItemPedido;

public class ItemPedidoController {

    private ItemPedidoDAO dao;

    public ItemPedidoController() {
        dao = new ItemPedidoDAO();
    }

    public void inserir(ItemPedido itemPedido) {
        dao.inserir(itemPedido);
    }
}
