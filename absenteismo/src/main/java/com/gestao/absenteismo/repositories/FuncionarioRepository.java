package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.absenteismo.models.Funcionario;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{
}
