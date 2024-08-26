package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.EmpresaController;
import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasentidades.Empresa;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CadastroAtendente {

    @FXML
    private ComboBox<String> comboBoxProduto;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldSenha;

    @FXML
    private void initialize() {
        // Carregar a lista de empresas do banco de dados
        EmpresaController empresaController = new EmpresaController();
        List<Empresa> empresas = empresaController.listarTodas();

        // Adicionar os nomes das empresas ao ComboBox
        comboBoxProduto.setItems(FXCollections.observableArrayList(empresas.stream().map(Empresa::getNome).collect(Collectors.toList())
        ));
    }

    @FXML
    public void salvar() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(textFieldNome.getText());
        funcionario.setTelefone(textFieldTelefone.getText());
        funcionario.setSenha(textFieldSenha.getText());
        Perfil perfil = new Perfil();
        String nomeEmpresaSelecionada = comboBoxProduto.getSelectionModel().getSelectedItem();
        EmpresaController empresaController = new EmpresaController();
        Empresa empresaSelecionada = empresaController.pesquisar(nomeEmpresaSelecionada);
        funcionario.setEmpresa(empresaSelecionada);
        Random random = new Random();
        perfil.setTipoPerfilById(1); // Defina o tipo de perfil conforme necessário
        perfil.setId(random.nextInt(1000)); // ID aleatório para o perfil
        funcionario.setPerfil(perfil);
        perfil.setFuncionario(funcionario);
        FuncionarioController funcionarioController = new FuncionarioController();
        funcionarioController.inserir(funcionario, perfil);
    }

    @FXML
    private void onCancelar() {
        // Fechar a janela ou limpar os campos, se necessário
    }
}
