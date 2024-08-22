package com.mycompany.gestaoentregasdao;
import  com.mycompany.gestaoentregasentidades.Perfil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PerfilDAO {
        public void inserir(Perfil perfil) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO perfil (tipo_perfil, id_perfil, id_funcionario_funcionario) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(inserir);
            // talvez o smallint de problema
            pstmt.setInt(1, perfil.getTipoEnumToInt());
            pstmt.setInt(2, perfil.getId()); 
            pstmt.setInt(3, perfil.getFuncionarioId());;
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
