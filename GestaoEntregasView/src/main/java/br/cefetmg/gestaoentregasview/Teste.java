package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasentidades.Cliente;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Random;

public class Teste {

    public static void main(String[] args) {

        // Validação dos campos
        String nome = "lucas";
        String telefone = "12321321";
        String bairro = "buritis";
        String CPF = "12321321";
        String logradouro = "1092";

        Cliente cliente = new Cliente();
        cliente.setBairro(bairro);
        cliente.setNome(nome);
        cliente.setCPF(CPF);
        cliente.setLogradouro(logradouro);
        cliente.setTelefone(telefone);
        ClienteController controller = new ClienteController();
        controller.inserir(cliente);

    }
}
