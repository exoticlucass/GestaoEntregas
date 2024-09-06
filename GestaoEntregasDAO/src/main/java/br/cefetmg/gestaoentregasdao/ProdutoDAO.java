package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasentidades.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProdutoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProdutoDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_GestaoEntregasDAO_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Produto produto) {
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public Produto read(int id) {
        return em.find(Produto.class, id);
    }

    public void update(Produto produto) {
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.remove(produto);
        }
        em.getTransaction().commit();
    }

    public List<Produto> listAll() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    public Produto selecionar(int id) {
        em.getTransaction().begin();
        Produto x = em.find(Produto.class, id);
        return x;
    }
    public List<Produto> pesquisarProdutos(String nome, String localizacao, Double valorUnitario) {
        StringBuilder queryStr = new StringBuilder("SELECT p FROM Produto p WHERE 1=1");

        if (nome != null && !nome.isEmpty()) {
            queryStr.append(" AND p.nome LIKE :nome");
        }
        if (localizacao != null && !localizacao.isEmpty()) {
            queryStr.append(" AND p.localizacao LIKE :localizacao");
        }
        if (valorUnitario != null) {
            queryStr.append(" AND p.valorUnitario >= :valorUnitario");
        }

        TypedQuery<Produto> query = em.createQuery(queryStr.toString(), Produto.class);

        if (nome != null && !nome.isEmpty()) {
            query.setParameter("nome", "%" + nome + "%");
        }
        if (localizacao != null && !localizacao.isEmpty()) {
            query.setParameter("localizacao", "%" + localizacao + "%");
        }
        if (valorUnitario != null) {
            query.setParameter("valorUnitario", valorUnitario);
        }

        return query.getResultList();
    }
}
