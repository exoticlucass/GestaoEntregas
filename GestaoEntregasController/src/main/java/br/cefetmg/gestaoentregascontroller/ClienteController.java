/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.ClienteDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;

/**
 *
 * @author lucas
 */
public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void inserir(Cliente cliente) {
        clienteDAO.inserir(cliente);
    }
}
