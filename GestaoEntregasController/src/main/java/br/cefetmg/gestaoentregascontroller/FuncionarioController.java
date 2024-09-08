/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.FuncionarioDAO;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FuncionarioController {

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    PedidoController pedidoController = new PedidoController();

    public void inserir(Funcionario funcionario) {

        funcionarioDAO.create(funcionario);
    }

    public List<Funcionario> listarTodos() {

        return funcionarioDAO.listAll();
    }

    public List<Funcionario> listarEntregadores() {
        List<Funcionario> entregadores = new ArrayList<>();
        List<Funcionario> geral = this.listarTodos();

        for (Funcionario funcionario : geral) {
            if (funcionario.getPerfil().getTipoPerfil() == Perfil.TipoPerfil.ENTREGADOR) {
                entregadores.add(funcionario);
            }
        }
        return entregadores;
    }

    public Funcionario escolhaDeFuncionario() {

        List<Funcionario> funcionarios = this.listarEntregadores();
        Funcionario escolhido = null;
        int menorNumeroDePedidos = Integer.MAX_VALUE;

        Calendar cal = Calendar.getInstance();
        Date endDate = cal.getTime();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        Date startDate = cal.getTime();

        for (Funcionario funcionario : funcionarios) {
            List<Pedido> pedidosNaUltimaSemana = pedidoController.pesquisarPeriodo(funcionario, startDate, endDate);
            int numeroDePedidos = pedidosNaUltimaSemana.size();

            if (numeroDePedidos < menorNumeroDePedidos) {
                menorNumeroDePedidos = numeroDePedidos;
                escolhido = funcionario;
            }
        }
        return escolhido;
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

    public List<Funcionario> pesquisarFuncionarios(String cpf, String nome, String telefone, Double porcentagem, String tipos) {
        return funcionarioDAO.pesquisarFuncionarios(cpf, nome, telefone, porcentagem, tipos);
    }

    public void deletarFuncionario(int id) {
        funcionarioDAO.delete(id);
    }
}
