package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.PerfilController;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CadastroEntregador {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    
    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldSenha;

    FuncionarioController funcionarioController;

    @FXML
    private void initialize() {
        this.funcionarioController = new FuncionarioController();
    }

    @FXML
    private void salvarPedido() {
        try {
            if (verificarCamposPreenchidos()) {
                
                // adicionar campos
                Funcionario funcionario = new Funcionario();

                String nome = textFieldNome.getText();
                String telefone = textFieldTelefone.getText();
                String cpf = textFieldCPF.getText();
                String senha = textFieldSenha.getText();

                Perfil perfil = new Perfil();
                perfil.setTipoPerfil(Perfil.TipoPerfil.ENTREGADOR);
                perfil.setSenha(senha);
                

                
                funcionario.setCPF(cpf);
                funcionario.setNome(nome);
                funcionario.setPerfil(perfil);
                funcionario.setPorcentagemComissaoEntregador(0);
                funcionario.setTelefone(telefone);
                
                
                
                funcionarioController.inserir(funcionario);

                exibirAlerta("Sucesso", "Perfil cadastrado com sucesso!", AlertType.INFORMATION);
                limparCampos();
            } else {
                exibirAlerta("Campos Incompletos", "Preencha todos os campos obrigatórios.", AlertType.WARNING);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Erro ao cadastrar o Perfil. Tente novamente.", AlertType.ERROR);
        }
    }


    private boolean verificarCamposPreenchidos() {
        return  !textFieldNome.getText().isEmpty()
                && !textFieldTelefone.getText().isEmpty()
                && !textFieldCPF.getText().isEmpty()
                && !textFieldSenha.getText().isEmpty();
    }

    private void limparCampos() {
        textFieldNome.clear();
        textFieldTelefone.clear();
        textFieldCPF.clear();
        textFieldSenha.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void onCancelar() {
        if (textFieldNome.getScene() != null) {
            textFieldNome.getScene().getWindow().hide();
        }
    }
}
