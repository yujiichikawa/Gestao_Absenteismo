package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.absenteismo.models.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato,Long>{
}
