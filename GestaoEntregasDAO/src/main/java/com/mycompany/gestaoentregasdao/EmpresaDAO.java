package com.mycompany.gestaoentregasdao;

import com.mycompany.gestaoentregasentidades.Empresa;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class EmpresaDAO {

    public void inserir(Empresa empresa) {
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO empresa (id_empresa, nome, CNPJ, CPF) VALUES (?, ?, ?, ?)";

            // Create a prepared statement
            pstmt = conn.prepareStatement(inserir);

            // Generate a random ID. This is just an example; adjust the range as needed.

            // Set the parameters
            pstmt.setInt(1, empresa.getId());
            pstmt.setString(2, empresa.getNome());
            pstmt.setString(3, empresa.getCNPJ());
            pstmt.setString(4, empresa.getCPF());

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
