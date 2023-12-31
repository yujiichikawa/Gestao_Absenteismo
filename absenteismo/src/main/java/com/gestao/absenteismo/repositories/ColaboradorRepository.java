package com.gestao.absenteismo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.absenteismo.models.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador,Long>{
  Optional<Colaborador> findByCpf(String cpf);
}
