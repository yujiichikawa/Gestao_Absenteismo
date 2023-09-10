package com.gestao.absenteismo.models;

import java.io.Serializable;

import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.enums.Cargo;

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
@Table(name = "funcionarios")
public class Funcionario implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(nullable = false,length = 255)
  private String nome;
  @Column(unique = true,nullable = false,length = 11)
  private String cpf;
  @Enumerated(EnumType.STRING)
  private Cargo cargo;
  @Enumerated(EnumType.STRING)
  private Atuacao atuacao;
  @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
  @JoinColumn(name = "id_contatos",referencedColumnName = "id")
  private Contato contato;

  public Funcionario() {
  }
  public Long getId() {
    return id;
  }
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
  public Cargo getCargo() {
    return cargo;
  }
  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }
  public Atuacao getAtuacao() {
    return atuacao;
  }
  public void setAtuacao(Atuacao atuacao) {
    this.atuacao = atuacao;
  }
  public Contato getContato() {
    return contato;
  }
  public void setContato(Contato contato) {
    this.contato = contato;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Funcionario other = (Funcionario) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  
}