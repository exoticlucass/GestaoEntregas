package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FuncionarioDAO {

    public void testeConexao() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/GestaoEntregas",
                "lucas",
                "123456"
        );
        String sql = "SELECT * FROM funcionario";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

    }

    public void inserir(Funcionario funcionario) {
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/GestaoEntregas",
                    "lucas",
                    "123456"
            );
            String inserir = "INSERT INTO funcionario (id_funcionario, nome, senha, telefone, id_empresa_empresa) VALUES (?, ?, ?, ?, ?)";

            // Create a prepared statement
            pstmt = conn.prepareStatement(inserir);

            // Set the parameters
            pstmt.setInt(1, funcionario.getId());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getSenha());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setInt(5, funcionario.getEmpresaId());
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

public Funcionario pesquisar(String telefone) throws SQLException {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Funcionario funcionario = null;

    try {
        // Establish the connection
        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/GestaoEntregas",
                "lucas",
                "123456"
        );

        // SQL to find a Funcionario by name
        String sql = "SELECT id_funcionario, nome, senha, telefone, id_empresa_empresa FROM funcionario WHERE telefone = ?";

        // Create a prepared statement
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, telefone);

        // Execute the query
        rs = pstmt.executeQuery();

        // Process the results
        if (rs.next()) {
            funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id_funcionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setSenha(rs.getString("senha"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Re-throw the exception to notify caller
    } finally {
        // Close resources
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

    return funcionario;
}

    public Funcionario procurarPorTelefone(String telefone) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Funcionario AS f WHERE f.telefone =:telefone ");
            query.setParameter("telefone", telefone);
            List<Funcionario> funcionarioPersistence = query.getResultList();
            if (!funcionarioPersistence.isEmpty()) {
                return funcionarioPersistence.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
}
