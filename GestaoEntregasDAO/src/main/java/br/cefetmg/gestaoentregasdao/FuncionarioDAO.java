package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class FuncionarioDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public FuncionarioDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_gestaoentregasdao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public Funcionario read(int id) {
        return em.find(Funcionario.class, id);
    }

    public void update(Funcionario funcionario) {
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Funcionario funcionario = em.find(Funcionario.class, id);
        if (funcionario != null) {
            em.remove(funcionario);
        }
        em.getTransaction().commit();
    }

    public List<Funcionario> listAll() {
        return em.createQuery("FROM Funcionario", Funcionario.class).getResultList();
    }
    
    public Funcionario selecionar(int id) {
        em.getTransaction().begin();
        Funcionario x = em.find(Funcionario.class, id);
        return x;
    }
    
}
