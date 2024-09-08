package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final ClienteController clienteController = new ClienteController();
    FXRedirecionador r =  new FXRedirecionador();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleLogin() {
        String cpf = usernameField.getText();
        String senha = passwordField.getText();

        Funcionario funcionario = funcionarioController.procurarCPF(cpf);
        if (funcionario != null && funcionarioController.validarSenha(funcionario, senha)) {
            Perfil perfil = funcionario.getPerfil();
            if (perfil.getTipoPerfil() == Perfil.TipoPerfil.ADMINISTRADOR
                    || perfil.getTipoPerfil() == Perfil.TipoPerfil.ATENDENTE) {
                r.loadScene("FXMLTelaInicialAdmAtendente.fxml", funcionario, usernameField);
            } else if (perfil.getTipoPerfil() == Perfil.TipoPerfil.ENTREGADOR) {
                r.loadScene("FXMLTelaInicialEntregador.fxml", funcionario, usernameField);
            }
            return;
        }

        Cliente cliente = clienteController.procurarCPF(cpf);
        if (cliente != null && clienteController.validarSenha(cliente, senha)) {
            r.loadScene("FXMLTelaInicialCliente.fxml", cliente, usernameField);
            return;
        }

        r.showAlert("Login falhou", "CPF ou senha incorretos. Por favor, tente novamente.");
    }


    @FXML
    private void handleCadastro() {
        r.loadScene("FXMLCadastroCliente.fxml", usernameField);
    }
}
