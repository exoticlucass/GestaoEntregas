/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.*;
import br.cefetmg.gestaoentregasentidades.*;

/**
 *
 * @author lucas
 */
public class ClienteController {

    public void inserir(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        dao.create(cliente);
    }

}
