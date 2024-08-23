/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasview;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXPrincipal extends Application {

    private static Stage stage;
    private static Scene telaLogin;
    private static Scene telaCadastroPedido;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            stage = primaryStage;

            primaryStage.setTitle("Gestão de Entregas");

            
            Parent loaderTelaLogin = FXMLLoader.load(getClass().getResource("/fxml/FXMLLogin.fxml"));
            telaLogin = new Scene(loaderTelaLogin, 1280, 720);
            
            Parent loaderTelaCadastroPedido = FXMLLoader.load(getClass().getResource("/fxml/FXMLPedidoCadastro.fxml"));
            telaCadastroPedido = new Scene(loaderTelaCadastroPedido, 1280, 720);

            

            primaryStage.setScene(telaLogin);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changedScreen(String tela, Object userData) {
        switch (tela) {
            case "FXMLLogin":
                stage.setScene(telaLogin);
                break;
            case "FXMLPedidoCadastro":
                stage.setScene(telaCadastroPedido);
                break;
        }
    }

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {

        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, userData);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
