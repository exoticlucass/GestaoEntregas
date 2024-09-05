package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaInicialAdmAtendenteController implements Initializable {

    @FXML
    private Label welcomeLabel;

    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização básica
    }

    @FXML
    public void onRelatorio() {
        r.loadScene("FXMLRelatorios.fxml", loggedInFuncionario, welcomeLabel);
    }

    @FXML
    public void onCadastrarAtendente() {
        r.loadScene("FXMLCadastroAtendente.fxml", loggedInFuncionario, welcomeLabel);

    }

    @FXML
    public void onCadastrarProduto() {
        r.loadScene("FXMLProdutoCadastro.fxml", loggedInFuncionario, welcomeLabel);
    }

    @FXML
    public void onListarPedidos() {
        r.loadScene("FXMLPedidosListagem.fxml", loggedInFuncionario, welcomeLabel);
    }

    public void onListarFuncionarios(){
        r.loadScene("FXMLFuncionariosListagem.fxml", loggedInFuncionario, welcomeLabel);
    }
    @FXML
    public void onCadastarPerfil() {
        r.loadScene("FXMLCadastroEntregador.fxml", loggedInFuncionario, welcomeLabel);
    }
    @FXML
    public void onSair(){
        r.loadScene("FXMLLogin.fxml", welcomeLabel);
    }

    // Método para definir o Funcionario
    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
        welcomeLabel.setText("Bem-vindo, " + funcionario.getNome());
    }
}
