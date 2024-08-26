
package br.cefetmg.gestaoentregasdao;
import  br.cefetmg.gestaoentregasentidades.Perfil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PerfilDAO {
        
    private EntityManagerFactory emf;
    private EntityManager em;

    public PerfilDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_gestaoentregasdao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        em.getTransaction().begin();
        em.persist(perfil);
        em.getTransaction().commit();
    }

    public Perfil read(int id) {
        return em.find(Perfil.class, id);
    }

    public void update(Perfil perfil) {
        em.getTransaction().begin();
        em.merge(perfil);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Perfil perfil = em.find(Perfil.class, id);
        if (perfil != null) {
            em.remove(perfil);
        }
        em.getTransaction().commit();
    }

    public List<Perfil> listAll() {
        return em.createQuery("FROM Perfil", Perfil.class).getResultList();
    }

    public Perfil selecionar(int id) {
        em.getTransaction().begin();
        Perfil x = em.find(Perfil.class, id);
        return x;
    }

}
