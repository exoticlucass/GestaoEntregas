/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.PerfilDAO;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;

/**
 *
 * @author lucas
 */
public class FuncionarioController {
    
    public void inserir(Funcionario funcionario, Perfil perfil) {
        PerfilDAO perfilDAO = new PerfilDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        perfilDAO.inserir(perfil);
        funcionarioDAO.inserir(funcionario);
    }
}
