package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasdao.EmpresaDAO;
import br.cefetmg.gestaoentregasdao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.PerfilDAO;
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
        EmpresaDAO empresaDAO = new EmpresaDAO();
        List<Empresa> empresas = empresaDAO.listarTodas();

        // Adicionar os nomes das empresas ao ComboBox
        comboBoxProduto.setItems(FXCollections.observableArrayList(empresas.stream().map(Empresa::getNome).collect(Collectors.toList())
        ));
    }

    @FXML
    public void salvarPedido() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(textFieldNome.getText());
        funcionario.setTelefone(textFieldTelefone.getText());
        funcionario.setSenha(textFieldSenha.getText());
        Perfil perfil = new Perfil();
        Random random = new Random();
        perfil.setTipoPerfilById(1); // Defina o tipo de perfil conforme necessário
        perfil.setId(random.nextInt(1000)); // ID aleatório para o perfil
        funcionario.setPerfil(perfil);
        perfil.setFuncionario(funcionario);
        PerfilDAO perfilDAO = new PerfilDAO();
        perfilDAO.inserir(perfil);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.inserir(funcionario);
    }

    @FXML
    private void onCancelar() {
        // Fechar a janela ou limpar os campos, se necessário
    }
}
