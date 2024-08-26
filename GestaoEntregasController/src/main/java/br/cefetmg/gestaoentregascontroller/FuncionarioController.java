/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.PerfilDAO;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class FuncionarioController {

    FuncionarioDAO dao;
    PerfilDAO perfilDAO;

    public FuncionarioController() {
        this.dao = new FuncionarioDAO();
        this.perfilDAO = new PerfilDAO();

    }

    public void inserir(Funcionario funcionario, Perfil perfil) {
        perfilDAO.inserir(perfil);
        dao.inserir(funcionario);
    }

    public boolean validarLogin(Funcionario funcionario) throws SQLException {
        Funcionario funcionarioComparar = dao.pesquisar(funcionario.getTelefone());
        return funcionarioComparar.getSenha().equals(funcionario.getSenha());
    }
}
