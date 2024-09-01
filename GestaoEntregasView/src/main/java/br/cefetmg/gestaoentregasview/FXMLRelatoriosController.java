package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasview.model.FuncionarioComissao;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.PedidoController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FXMLRelatoriosController {

    @FXML
    private TableView<FuncionarioComissao> tabelaFuncionarios;

    @FXML
    private TableColumn<FuncionarioComissao, String> colunaNome;

    @FXML
    private TableColumn<FuncionarioComissao, String> colunaCpf;

    @FXML
    private TableColumn<FuncionarioComissao, Double> colunaValorTotalVendido;

    @FXML
    private TableColumn<FuncionarioComissao, Double> colunaPorcentagem;

    @FXML
    private TableColumn<FuncionarioComissao, Double> colunaComissao;

    @FXML
    private TextField campoBuscaCpf;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    private FuncionarioController funcionarioController = new FuncionarioController();
    private PedidoController pedidoController = new PedidoController();

    @FXML
    public void initialize() {
        // Configurando as colunas da tabela
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaValorTotalVendido.setCellValueFactory(new PropertyValueFactory<>("valorTotalVendido"));
        colunaPorcentagem.setCellValueFactory(new PropertyValueFactory<>("porcentagem"));
        colunaComissao.setCellValueFactory(new PropertyValueFactory<>("comissao"));

        preencherTabelaFuncionarios();
    }

    @FXML
    private void preencherTabelaFuncionarios() {
        List<Funcionario> funcionarios = funcionarioController.listarTodos();

        String cpfBusca = campoBuscaCpf.getText().trim();
        if (!cpfBusca.isEmpty()) {
            funcionarios = funcionarios.stream()
                    .filter(funcionario -> funcionario.getCPF().contains(cpfBusca))
                    .collect(Collectors.toList());
        }

        LocalDate startDate = dataInicio.getValue();
        LocalDate endDate = dataFim.getValue();

        ObservableList<FuncionarioComissao> listaFuncionariosComissao = FXCollections.observableArrayList();

        for (Funcionario funcionario : funcionarios) {
            double valorTotalVendido = 0.0;
            if (startDate != null && endDate != null) {
                valorTotalVendido = calcularValorTotalVendido(funcionario, startDate, endDate);
            }

            double porcentagem = funcionario.getPorcentagemComissaoEntregador();
            double comissao = valorTotalVendido * porcentagem;

            FuncionarioComissao funcionarioComissao = new FuncionarioComissao(funcionario.getNome(),funcionario.getCPF(),valorTotalVendido,porcentagem,comissao
            );

            listaFuncionariosComissao.add(funcionarioComissao);
        }

        tabelaFuncionarios.setItems(listaFuncionariosComissao);
    }

    private double calcularValorTotalVendido(Funcionario funcionario, LocalDate startDate, LocalDate endDate) {
        List<Pedido> pedidos = pedidoController.pesquisarPeriodo(funcionario, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
        return pedidos.stream().mapToDouble(Pedido::getValorTotal).sum();
    }

}
