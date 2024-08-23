package br.cefetmg.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "senha")
    private String senha;
    @Column(name = "telefone")
    private String telefone;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "funcionario")
    private List<Perfil> perfil;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa_empresa")
    private Empresa empresa;

    public Funcionario() {
        this.perfil = new ArrayList<Perfil>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil.add(perfil);
    }

    public int getEmpresaId() {
        return empresa.getId();
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
