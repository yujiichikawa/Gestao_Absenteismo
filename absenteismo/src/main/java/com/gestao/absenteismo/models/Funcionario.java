package com.gestao.absenteismo.models;

import com.gestao.absenteismo.enums.Atuacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionários")
public class Funcionario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(nullable = false,length = 255)
  private String nome;
  @Column(unique = true,nullable = false,length = 11)
  private String cpf;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Atuacao atuacao;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_contrato",referencedColumnName = "id")
  private Contrato contrato;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_contato",referencedColumnName = "id")
  private Contato contato;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_endereco",referencedColumnName = "id")
  private Endereco endereco;

  public Funcionario(){}

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Atuacao getAtuacao() {
    return atuacao;
  }

  public void setAtuacao(Atuacao atuacao) {
    this.atuacao = atuacao;
  }

  public Contrato getContrato() {
    return contrato;
  }

  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }

  public Contato getContato() {
    return contato;
  }

  public void setContato(Contato contato) {
    this.contato = contato;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  
}
