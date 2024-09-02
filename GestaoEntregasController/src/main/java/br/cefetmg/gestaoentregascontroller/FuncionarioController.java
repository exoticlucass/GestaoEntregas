/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.PerfilDAO;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FuncionarioController {

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void inserir(Funcionario funcionario) {
        
        funcionarioDAO.create(funcionario);
    }

    public List<Funcionario> listarTodos() {

        return funcionarioDAO.listAll();
    }

    public Funcionario procurarCPF(String cpf) {
        List<Funcionario> lista = funcionarioDAO.listAll();

        for (int i = 0; i < lista.size(); i++) {
            Funcionario f = lista.get(i);
            if (f.getCPF().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    public boolean validarSenha(Funcionario f, String senha) {
        if (f.getPerfil().getSenha().equals(senha)) {
            return true;
        }
        return false;
    }
}
