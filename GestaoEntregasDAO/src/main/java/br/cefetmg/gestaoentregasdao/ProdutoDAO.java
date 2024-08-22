
package br.cefetmg.gestaoentregasdao;


import br.cefetmg.gestaoentregasentidades.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {

    public void inserir(Produto produto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO produto (id_produto, nome_produto, localizacao_produto, \"id_item_ItemPedido\") VALUES (?, ?, ?, ?)";

            // Create a prepared statement
            pstmt = conn.prepareStatement(inserir);

            // Set the parameters
            pstmt.setInt(1, 1);
            pstmt.setString(2, produto.getNome());
            pstmt.setString(3, produto.getLocalização());
            pstmt.setInt(4, 1);

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
