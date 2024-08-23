
package br.cefetmg.gestaoentregasdao;


import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FuncionarioDAO {

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
    public boolean validarLogin(Funcionario funcionario) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT f.telefone, f.senha FROM Funcionario AS f WHERE f.telefone = :telefone AND f.senha = :senha");
            query.setParameter("telefone", funcionario.getTelefone());
            query.setParameter("senha", funcionario.getSenha());
            List<Funcionario> funcionarioPersistence = query.getResultList();
            if (!funcionarioPersistence.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
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

