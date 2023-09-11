package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.absenteismo.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
}
