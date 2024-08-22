import com.mycompany.gestaoentregasentidades.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PedidoDAO {

    public void inserir(Pedido pedido) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO pedido (id_pedido, data_pedido, valor_total, id_cliente_cliente) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(inserir);
            pstmt.setInt(1, pedido.getId());
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Current date and time
            pstmt.setDouble(3, pedido.getValorTotal());
            pstmt.setInt(4, pedido.getClienteId());
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
