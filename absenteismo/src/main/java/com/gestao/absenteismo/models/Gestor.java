package com.gestao.absenteismo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "gestores")
public class Gestor extends Funcionario{


  @JsonManagedReference
  @OneToMany(mappedBy = "gestor",cascade = CascadeType.PERSIST)
  private List<Comunicado> comunicados;

  @JsonManagedReference
  @OneToMany(mappedBy = "gestor",cascade = CascadeType.PERSIST)
  private List<Colaborador> colaboradores;

  public Gestor() {
    super();
    this.comunicados = new ArrayList<Comunicado>();
    this.colaboradores = new ArrayList<Colaborador>();
  }

  public List<Comunicado> getComunicados() {
    return comunicados;
  }

  public void setComunicados(Comunicado comunicado) {
    this.comunicados.add(comunicado);
  }

  public List<Colaborador> getColaboradores() {
    return colaboradores;
  }

  public void setColaboradores(Colaborador colaborador) {
    this.colaboradores.add(colaborador);
  }


}
