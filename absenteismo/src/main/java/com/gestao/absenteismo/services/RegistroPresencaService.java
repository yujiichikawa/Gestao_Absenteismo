package com.gestao.absenteismo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.absenteismo.models.Registro_Presenca;
import com.gestao.absenteismo.repositories.ColaboradorRepository;
import com.gestao.absenteismo.repositories.RegistroPresencaRepository;

@Service
public class RegistroPresencaService {
  @Autowired
  private ColaboradorRepository colaboradorRepository;
  @Autowired
  private RegistroPresencaRepository registroPresencaRepository;

  public Registro_Presenca gerar_presenca_colaborador(Long id){  
    var colaborador = colaboradorRepository.findById(id);
    var presenca = new Registro_Presenca();
    colaborador.get().setRegistro_presenca(presenca);
    presenca.setColaborador(colaborador.get());
    return registroPresencaRepository.save(presenca);
  }
}
