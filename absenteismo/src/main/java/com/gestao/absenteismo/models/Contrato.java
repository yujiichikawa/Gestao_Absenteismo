package com.gestao.absenteismo.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.enums.Cargo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contratos")
public class Contrato {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Cargo cargo;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Atuacao atuacao;
  @Column(nullable = false,length = 1000)
  private String descricao;

  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
  @Column(nullable = false)
  private LocalDate data_inicio;
  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
  @Column(nullable = false)
  private LocalDate data_termino;

  public Contrato() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getData_inicio() {
    return data_inicio;
  }

  public void setData_inicio(LocalDate data_inicio) {
    this.data_inicio = data_inicio;
  }

  public LocalDate getData_termino() {
    return data_termino;
  }

  public void setData_termino(LocalDate data_termino) {
    this.data_termino = data_termino;
  }
  public Atuacao getAtuacao() {
    return atuacao;
  }
  public void setAtuacao(Atuacao atuacao) {
    this.atuacao = atuacao;
  }
}
