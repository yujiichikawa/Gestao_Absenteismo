package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.absenteismo.models.Registro_Presenca;

public interface RegistroPresencaRepository extends JpaRepository<Registro_Presenca,Long>{
}
