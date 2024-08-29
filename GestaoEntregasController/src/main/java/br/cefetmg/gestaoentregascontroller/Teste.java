package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;

import java.util.Random;

public class Teste {

    public static void main(String[] args) {

        {
            // Validação dos campos
            String nome = "Juan";
            String telefone = "1231321312321";
            String senha = "sarcofago";

            if (nome.isEmpty() || telefone.isEmpty() || senha.isEmpty()) {
                return;
            }

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setTelefone(telefone);
            funcionario.setSenha(senha);

            Perfil perfil = new Perfil();
            perfil.setTipoPerfil(Perfil.TipoPerfil.ATENDENTE);
            perfil.setFuncionario(funcionario);
            FuncionarioController funcionarioController = new FuncionarioController();
            funcionarioController.inserir(funcionario, perfil);
        }

    }
}
