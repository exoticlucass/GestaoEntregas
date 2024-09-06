/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.ClienteDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ClienteController {

    ClienteDAO clienteDAO = new ClienteDAO();

    public void salvarCliente(Cliente cliente) {
        clienteDAO.create(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listAll();
    }

    public Cliente procurarCPF(String cpf) {
        List<Cliente> lista = clienteDAO.listAll();

        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
    
    public boolean validarSenha(Cliente c, String senha) {
        if (c.getPerfil().getSenha()    .equals(senha)) {
            return true;
        }
        return false;
    }
//    public List<Cliente> pesquisarClientes(String cpf, String nome, String email, String telefone) {
//        return clienteDAO.pesquisarClientes(cpf, nome, email, telefone);
//    }
}
