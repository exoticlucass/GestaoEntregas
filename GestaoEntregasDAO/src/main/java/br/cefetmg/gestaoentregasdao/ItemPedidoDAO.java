
package br.cefetmg.gestaoentregasdao;


import  br.cefetmg.gestaoentregasentidades.ItemPedido;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ItemPedidoDAO {

    public void inserir(ItemPedido itemPedido) {
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO ItemPedido (id_item, valor_unitario, quantidade_item, id_pedido_pedido) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, itemPedido.getId());
            pstmt.setInt(2, itemPedido.getQuantidade()); // Current date and time
            pstmt.setDouble(3, itemPedido.getValorUnitario());
            pstmt.setInt(4, itemPedido.getPedidoId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
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
