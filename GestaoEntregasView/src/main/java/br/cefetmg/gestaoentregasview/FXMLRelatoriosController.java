package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.ItemPedido;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasentidades.Perfil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLRelatoriosController {

    @FXML
    private TextField textFieldCpfEntregador;
    @FXML
    private TextField textFieldDataInicio;
    @FXML
    private TextField textFieldDataFim;
    @FXML
    private Button buttonPesquisar;
    @FXML
    private Label labelTotalEntregue;
    @FXML
    private Label labelTotalComissao;
    @FXML
    private Label labelMediaDia;
    
    
    @FXML
    private VBox vboxResultados;

    private PedidoController pedidoController;
    private FuncionarioController funcionarioController;
    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }

    @FXML
    private void initialize() {
        pedidoController = new PedidoController();
        funcionarioController = new FuncionarioController();
    }

    private Double somarValores(List<ItemPedido> lista) {
        Double valorTotal = 0d;
        for (ItemPedido itens : lista) {
            valorTotal += itens.getValorTotal();
        }
        return valorTotal;
    }

    @FXML
    private void pesquisarPedidos() {
        String cpfEntregador = textFieldCpfEntregador.getText();
        String dataInicioStr = textFieldDataInicio.getText();
        String dataFimStr = textFieldDataFim.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicio = null;
        Date dataFim = null;

        try {
            dataInicio = sdf.parse(dataInicioStr);
            dataFim = sdf.parse(dataFimStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Funcionario entregador = funcionarioController.procurarCPF(cpfEntregador);
        if (entregador == null || entregador.getPerfil().getTipoPerfil() != Perfil.TipoPerfil.ENTREGADOR) {
            System.out.println("Entregador não encontrado!");
            return;
        }

        List<Pedido> pedidos = pedidoController.pesquisarPeriodo(entregador, dataInicio, dataFim);

        double totalEntregue = 0d;
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus() == Pedido.Status.ENTREGUE) {
                totalEntregue += pedido.getValorTotal();
            }
        }

        // Preencher os resultados na tela
        labelTotalEntregue.setText(String.format("R$ %.2f", totalEntregue));
        labelTotalComissao.setText(String.format("R$ %.2f", totalEntregue * entregador.getPorcentagemComissaoEntregador()));

        // Calcular a diferença em milissegundos
        long diferencaEmMilissegundos = dataFim.getTime() - dataInicio.getTime();

        // Converter a diferença de milissegundos para dias
        double milissegundosPorDia = 1000.0 * 60 * 60 * 24;
        double diferencaEmDias = diferencaEmMilissegundos / milissegundosPorDia;
        labelMediaDia.setText(String.format("R$ %.2f", totalEntregue/diferencaEmDias));
        
        // Exibir o container de resultados
        vboxResultados.setVisible(true);
    }
    @FXML
    private Button buttonVoltar;

    @FXML
    private void voltarPagina() {
        r.loadScene("FXMLTelaInicialAdmAtendente.fxml", loggedInFuncionario, textFieldDataFim);
    }

}
