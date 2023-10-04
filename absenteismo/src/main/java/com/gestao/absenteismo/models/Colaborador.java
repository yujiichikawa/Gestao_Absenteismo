package com.gestao.absenteismo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "colaboradores")
public class Colaborador extends Funcionario{

  @JsonManagedReference
  @OneToMany(mappedBy = "colaborador",cascade = CascadeType.REMOVE)
  private List<Comunicado> comunicados;

  @JsonManagedReference
  @OneToMany(mappedBy = "colaborador",cascade = CascadeType.REMOVE)
  private List<Registro_Presenca> registro_presencas;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_gestor",referencedColumnName = "id")
  private Gestor gestor;

  public Colaborador() {
    super();
    comunicados = new ArrayList<Comunicado>();
    registro_presencas = new ArrayList<Registro_Presenca>();
  }


  public List<Comunicado> getComunicados() {
    return comunicados;
  }

  public void setComunicados(Comunicado comunicado) {
    this.comunicados.add(comunicado);
  }

  public Gestor getGestor() {
    return gestor;
  }

  public void setGestor(Gestor gestor) {
    this.gestor = gestor;
  }
  
  public List<Registro_Presenca> getRegistro_presencas() {
    return registro_presencas;
  }
  public void setRegistro_presenca(Registro_Presenca registro_presenca) {
    this.registro_presencas.add(registro_presenca);
  }

}
