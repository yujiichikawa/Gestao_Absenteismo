package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.absenteismo.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{
}
