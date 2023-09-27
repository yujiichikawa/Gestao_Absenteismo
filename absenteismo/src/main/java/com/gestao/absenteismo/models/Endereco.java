package com.gestao.absenteismo.models;

import com.gestao.absenteismo.enums.TipoMoradia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enderecos")
public class Endereco {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(nullable = false,length = 50)
  private String cidade;
  @Column(nullable = false,length = 50)
  private String bairro;
  @Column(nullable = false,length = 50)
  private String rua;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoMoradia moradia;
  @Column(nullable = false)
  private Integer numero;
  
  public Endereco() {
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getCidade() {
    return cidade;
  }
  public void setCidade(String cidade) {
    this.cidade = cidade;
  }
  public String getBairro() {
    return bairro;
  }
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  public String getRua() {
    return rua;
  }
  public void setRua(String rua) {
    this.rua = rua;
  }
  public TipoMoradia getMoradia() {
    return moradia;
  }
  public void setMoradia(TipoMoradia moradia) {
    this.moradia = moradia;
  }
  public Integer getNumero() {
    return numero;
  }
  public void setNumero(Integer numero) {
    this.numero = numero;
  }
  
}
