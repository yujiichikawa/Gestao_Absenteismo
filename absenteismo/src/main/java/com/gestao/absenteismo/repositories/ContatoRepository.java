package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.absenteismo.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long>{
}
