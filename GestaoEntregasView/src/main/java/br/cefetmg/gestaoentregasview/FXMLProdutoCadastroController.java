package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasentidades.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXMLProdutoCadastroController {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldLocalizacao;

    @FXML
    private TextField textFieldValorUnitario;    
    
    private ProdutoController produtoController;
    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();
    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }


    public FXMLProdutoCadastroController() {
        this.produtoController = new ProdutoController(); 
    }

    @FXML
    private void salvarProduto() {
        try {
            if (verificarCamposPreenchidos()) {
                double valorUnitario = Double.parseDouble(textFieldValorUnitario.getText());
                String nome = textFieldNome.getText();
                String localizacao = textFieldLocalizacao.getText();
                
                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setLocalizacao(localizacao);
                produto.setValorUnitario(valorUnitario);
                
                produtoController.salvarProduto(produto);

                exibirAlerta("Sucesso", "Produto salvo com sucesso!", AlertType.INFORMATION);
                limparCampos();
            } else {
                exibirAlerta("Campos Incompletos", "Preencha todos os campos obrigatórios.", AlertType.WARNING);
            }
        }
        catch(Exception e){
            exibirAlerta("Erro", "Erro ao salvar o produto. Tente novamente.", AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelar() {
        limparCampos();
        r.loadScene("FXMLTelaInicialAdmAtendente.fxml", loggedInFuncionario, textFieldNome);
    }

    private boolean verificarCamposPreenchidos() {
        return !textFieldNome.getText().isEmpty()
                && !textFieldValorUnitario.getText().isEmpty()
                && !textFieldLocalizacao.getText().isEmpty();
    }

    private void limparCampos() {
        textFieldValorUnitario.clear();
        textFieldNome.clear();
        textFieldLocalizacao.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    
}
