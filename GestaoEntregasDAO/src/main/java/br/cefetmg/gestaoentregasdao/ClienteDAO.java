package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ClienteDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ClienteDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_GestaoEntregasDAO_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente read(int id) {
        return em.find(Cliente.class, id);
    }

    public void update(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.remove(cliente);
        }
        em.getTransaction().commit();
    }

    public List<Cliente> listAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Cliente selecionar(int id) {
        em.getTransaction().begin();
        Cliente x = em.find(Cliente.class, id);
        return x;
    }
    public List<Cliente> pesquisarClientes(String cpf, String nome, String telefone) {
        StringBuilder queryStr = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");

        if (cpf != null && !cpf.isEmpty()) {
            queryStr.append(" AND c.cpf = :cpf");
        }
        if (nome != null && !nome.isEmpty()) {
            queryStr.append(" AND c.nome LIKE :nome");
        }
        if (telefone != null && !telefone.isEmpty()) {
            queryStr.append(" AND c.telefone = :telefone");
        }

        TypedQuery<Cliente> query = em.createQuery(queryStr.toString(), Cliente.class);

        if (cpf != null && !cpf.isEmpty()) {
            query.setParameter("cpf", cpf);
        }
        if (nome != null && !nome.isEmpty()) {
            query.setParameter("nome", "%" + nome + "%");
        }
        if (telefone != null && !telefone.isEmpty()) {
            query.setParameter("telefone", telefone);
        }

        return query.getResultList();
    }
}
