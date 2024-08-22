
package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    
    
    public void inserir(Cliente cliente) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/GestaoEntregas", 
                "lucas", 
                "123456"
            );
            String inserir = "INSERT INTO cliente (id_cliente, nome, logradouro, bairro, telefone, CNPJ_cliente, CPF_cliente, id_empresa_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a prepared statement
            pstmt = conn.prepareStatement(inserir);
            
            // Set the parameters
            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getLogradouro());
            pstmt.setString(4, cliente.getBairro());
            pstmt.setString(5, cliente.getTelefone());
            pstmt.setString(6, cliente.getCNPJ());
            pstmt.setString(7, cliente.getCPF());
            pstmt.setInt(8, cliente.getEmpresaId());  // Adjust if needed based on your Empresa entity
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
