package com.gestao.absenteismo.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestao.absenteismo.enums.TipoComunicado;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comunicados")
public class Comunicado implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoComunicado tipo;
  @Column(nullable = false,length = 500)
  private String mensagem;

  private LocalDate data;
  private LocalTime hora;
  
  @JsonBackReference
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_gestor",referencedColumnName = "id")
  private Gestor gestor;
  @JsonBackReference
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_colaborador",referencedColumnName = "id")
  private Colaborador colaborador;

  public Comunicado() {
    this.data = LocalDate.now();
    this.hora = LocalTime.now();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TipoComunicado getTipo() {
    return tipo;
  }

  public void setTipo(TipoComunicado tipo) {
    this.tipo = tipo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public LocalTime getHora() {
    return hora;
  }

  public void setHora(LocalTime hora) {
    this.hora = hora;
  }

  public Colaborador getColaborador() {
    return colaborador;
  }

  public void setColaborador(Colaborador colaborador) {
    this.colaborador = colaborador;
  }
  public Gestor getGestor() {
    return gestor;
  }
  public void setGestor(Gestor gestor) {
    this.gestor = gestor;
  }

}
