package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FXMLFuncionariosListagemController {

    @FXML
    private TableView<Funcionario> tableViewFuncionarios;
    @FXML
    private TableColumn<Funcionario, Integer> columnId;
    @FXML
    private TableColumn<Funcionario, String> columnCpf;
    @FXML
    private TableColumn<Funcionario, String> columnNome;
    @FXML
    private TableColumn<Funcionario, String> columnTelefone;
    @FXML
    private TableColumn<Funcionario, Double> columnPorcentagem;
    @FXML
    private TableColumn<Funcionario, String> columnTipo;

    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldPorcentagem;
    @FXML
    private ComboBox<String> comboBoxTipo;

    private FuncionarioController funcionarioController;

    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }

    @FXML
    private void initialize() {
        funcionarioController = new FuncionarioController();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnPorcentagem.setCellValueFactory(new PropertyValueFactory<>("porcentagemComissaoEntregador"));

        columnTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerfil().getTipoEnumToString()));

        comboBoxTipo.setItems(FXCollections.observableArrayList("ADMINISTRADOR", "ATENDENTE", "ENTREGADOR", "CLIENTE"));

        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        ObservableList<Funcionario> pedidos = FXCollections.observableArrayList(funcionarioController.listarTodos());
        tableViewFuncionarios.setItems(pedidos);
    }

    @FXML
    private void pesquisar() {
        String cpf = textFieldCpf.getText();
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();
        String porcentagemText = textFieldPorcentagem.getText().replace(',', '.');
        Double porcentagem = null;

        if (!porcentagemText.isEmpty()) {
            porcentagem = Double.parseDouble(porcentagemText);
        }

        String tipos = comboBoxTipo.getValue();

        List<Funcionario> funcionariosFiltrados = funcionarioController.pesquisarFuncionarios(cpf, nome, telefone, porcentagem, tipos);
        tableViewFuncionarios.setItems(FXCollections.observableArrayList(funcionariosFiltrados));
    }

}
