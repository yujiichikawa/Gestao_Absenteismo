package com.gestao.absenteismo.models;

import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.enums.Cargo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_gestores")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Gestor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false,length = 255)
  private String nome;
  @Enumerated(EnumType.STRING)
  private Cargo cargo;
  @Enumerated(EnumType.STRING)
  private Atuacao atuacao;
  @OneToOne
  private Contato contato; 
}
