/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXRedirecionador {

    public void loadScene(String fxmlFile, TextField field) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) field.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    public void loadScene(String fxmlFile, Label label) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) label.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    public void loadScene(String fxmlFile, Funcionario funcionario, TextField field) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Grama esse aqui é o codigo que passa se vc precisar (dentro do controller desse fxml tem a outra parte dele)
            if (fxmlFile.equals("FXMLTelaInicialAdmAtendente.fxml")) {
                FXMLTelaInicialAdmAtendenteController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLTelaInicialEntregador.fxml")) {
                FXMLTelaInicialEntregadorController controller = loader.getController();
                controller.setFuncionario(funcionario);
            }

            Stage stage = (Stage) field.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    public void loadScene(String fxmlFile, Funcionario funcionario, Label label) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Grama esse aqui é o codigo que passa se vc precisar (dentro do controller desse fxml tem a outra parte dele)
            if (fxmlFile.equals("FXMLTelaInicialAdmAtendente.fxml")) {
                FXMLTelaInicialAdmAtendenteController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLTelaInicialEntregador.fxml")) {
                FXMLTelaInicialEntregadorController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLRelatorios.fxml")) {
                FXMLRelatoriosController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLCadastroAtendente.fxml")) {
                FXMLCadastroAtendenteController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLProdutoCadastro.fxml")) {
                FXMLProdutoCadastroController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLPedidosListagem.fxml")) {
                FXMLPedidosListagemController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLCadastroEntregador.fxml")) {
                FXMLCadastroEntregadorController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLFuncionariosListagem.fxml")) {
                FXMLFuncionariosListagemController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLRealizarEntrega.fxml")) {
                FXMLRealizarEntregaController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLProdutosListagem.fxml")) {
                FXMLProdutosListagemController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLClientesListagem.fxml")) {
                FXMLClientesListagemController controller = loader.getController();
                controller.setFuncionario(funcionario);
            }

            Stage stage = (Stage) label.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    public void loadScene(String fxmlFile, Cliente cliente, TextField field) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            if (fxmlFile.equals("FXMLTelaInicialCliente.fxml")) {
                FXMLTelaInicialClienteController controller = loader.getController();
                controller.setCliente(cliente);
            }
            if (fxmlFile.equals("FXMLPedidosCliente.fxml")) {
                FXMLPedidosClienteController controller = loader.getController();
                controller.setCliente(cliente);
            }

            Stage stage = (Stage) field.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    public void loadScene(String fxmlFile, Cliente cliente, Label label) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            if (fxmlFile.equals("FXMLTelaInicialCliente.fxml")) {
                FXMLTelaInicialClienteController controller = loader.getController();
                controller.setCliente(cliente);
            }
            if (fxmlFile.equals("FXMLPedidoCadastro.fxml")) {
                FXMLPedidoCadastroController controller = loader.getController();
                controller.setCliente(cliente);
            }
            if (fxmlFile.equals("FXMLPedidosCliente.fxml")) {
                FXMLPedidosClienteController controller = loader.getController();
                controller.setCliente(cliente);
            }

            Stage stage = (Stage) label.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
