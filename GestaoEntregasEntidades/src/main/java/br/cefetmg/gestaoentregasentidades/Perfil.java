/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private int id;
    
    public enum TipoPerfil{
        ADMINISTRADOR,ATENDENTE,ENTREGADOR;
    }
    
    @Column(name = "tipo_perfil")
    private TipoPerfil tipoPerfil;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario_funcionario")
    private Funcionario funcionario;
    public int getTipoEnumToInt(){
        if(this.getTipoPerfil() == TipoPerfil.ADMINISTRADOR)
            return 1;
        else if(this.getTipoPerfil() == TipoPerfil.ATENDENTE)
            return 2;
        return 3;        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public int getFuncionarioId() {
        return funcionario.getId();
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
