package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.absenteismo.models.Gestor;

@Repository
public interface GestorRepository extends JpaRepository<Gestor,Long>{
  
}
