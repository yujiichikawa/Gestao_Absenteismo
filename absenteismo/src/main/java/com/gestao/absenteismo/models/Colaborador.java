package com.gestao.absenteismo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.enums.TipoMoradia;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "colaboradores")
public class Colaborador{

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
  @Column(nullable = false,length = 11)
  private String telefone;
  @Column(nullable = false,length = 50)
  private String email;
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

  @JsonManagedReference
  @OneToMany(mappedBy = "colaborador",cascade = CascadeType.PERSIST)
  private List<Comunicado> comunicados;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_gestor",referencedColumnName = "id")
  private Gestor gestor;

  public Colaborador() {
    this.comunicados = new ArrayList<Comunicado>();
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

  public Atuacao getAtuacao() {
    return atuacao;
  }

  public void setAtuacao(Atuacao atuacao) {
    this.atuacao = atuacao;
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
    Colaborador other = (Colaborador) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  
  
}
