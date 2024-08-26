package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Pedido;
// import br.cefetmg.gestaoentregasentidades.Status; // Supondo que Status é um enum
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PedidoDAO {

    public void inserir(Pedido pedido) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Estabelecendo a conexão
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO pedido (id_pedido, data_pedido, valor_total, id_cliente_cliente, status_pedido) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, pedido.getId());
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Data e hora atuais
            pstmt.setDouble(3, pedido.getValorTotal());
            pstmt.setInt(4, pedido.getClienteId());
            pstmt.setString(5, pedido.getStatus().name()); // Converte o enum Status para String
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void modificar(Pedido pedido) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Estabelecendo a conexão
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String modificar = "UPDATE pedido SET data_pedido = ?, valor_total = ?, id_cliente_cliente = ?, status_pedido = ? WHERE id_pedido = ?";
            pstmt = conn.prepareStatement(modificar);
            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // Atualiza para a data e hora atuais
            pstmt.setDouble(2, pedido.getValorTotal());
            pstmt.setInt(3, pedido.getClienteId());
            pstmt.setString(4, pedido.getStatus().name()); // Converte o enum Status para String
            pstmt.setInt(5, pedido.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Nenhum pedido encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
