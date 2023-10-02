package com.gestao.absenteismo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_presencas")
public class Registro_Presenca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  private LocalDate data;
  private LocalTime hora;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_colaborador",referencedColumnName = "id")
  private Colaborador colaborador;

  public Registro_Presenca() {
    this.data = LocalDate.now();
    this.hora = LocalTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

}
