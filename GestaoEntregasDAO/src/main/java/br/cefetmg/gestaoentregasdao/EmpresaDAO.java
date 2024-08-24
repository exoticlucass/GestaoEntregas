package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Empresa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmpresaDAO {

    public List<Empresa> listarTodas() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Empresa> empresas = new ArrayList<>();

        try {
            // Estabelece a conexão
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );

            // SQL para listar todas as empresas
            String sql = "SELECT id_empresa, nome, CNPJ FROM empresa";

            // Cria o prepared statement
            pstmt = conn.prepareStatement(sql);

            // Executa a consulta
            rs = pstmt.executeQuery();

            // Itera pelos resultados e cria os objetos Empresa
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCNPJ(rs.getString("CNPJ"));
                empresas.add(empresa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return empresas;
    }

    public Empresa pesquisar(String nome) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Empresa empresa = null;

        try {
            // Estabelece a conexão
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );

            // SQL para pesquisar a empresa pelo ID
            String sql = "SELECT id_empresa, nome, CNPJ FROM empresa WHERE nome = ?";

            // Cria o prepared statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);

            // Executa a consulta
            rs = pstmt.executeQuery();

            // Se encontrar a empresa, cria o objeto Empresa
            if (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCNPJ(rs.getString("CNPJ"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return empresa;
    }

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
            String inserir = "INSERT INTO empresa (id_empresa, nome, CNPJ) VALUES (?, ?, ?)";

            // Create a prepared statement
            pstmt = conn.prepareStatement(inserir);

            // Generate a random ID. This is just an example; adjust the range as needed.
            // Set the parameters
            pstmt.setInt(1, empresa.getId());
            pstmt.setString(2, empresa.getNome());
            pstmt.setString(3, empresa.getCNPJ());

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
