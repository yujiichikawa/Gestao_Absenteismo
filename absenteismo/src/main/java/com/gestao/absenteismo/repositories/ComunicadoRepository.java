package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.absenteismo.models.Comunicado;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado,Long>{
}
