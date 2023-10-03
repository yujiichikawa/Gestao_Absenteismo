package com.gestao.absenteismo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.absenteismo.dtos.FuncionarioDTO;
import com.gestao.absenteismo.models.Colaborador;
import com.gestao.absenteismo.models.Registro_Presenca;
import com.gestao.absenteismo.repositories.ColaboradorRepository;
import com.gestao.absenteismo.services.RegistroPresencaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {
  @Autowired
  private ColaboradorRepository colaboradorRepository;
  @Autowired
  private RegistroPresencaService registroPresencaService;

  @DeleteMapping("/delete/{cpf}")
   public ResponseEntity<Object> deleteGestor(@PathVariable String cpf){
    Optional<Colaborador> funcionario = colaboradorRepository.findByCpf(cpf);
    if(funcionario.isPresent()) {
      colaboradorRepository.delete(funcionario.get());
      return ResponseEntity.status(HttpStatus.OK).body("Colaborador removido");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
  }
  @GetMapping("/lista")
  public ResponseEntity<List<Colaborador>> listar_todos(){
    return ResponseEntity.status(HttpStatus.OK).body(colaboradorRepository.findAll());
  }

  @PutMapping("/update/{cpf}")
  public ResponseEntity<Object> updateById(@PathVariable String cpf,@Valid @RequestBody FuncionarioDTO funcionarioDTO){
    Optional<Colaborador> funcionario = colaboradorRepository.findByCpf(cpf);
    if(funcionario.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
    }
    var funcionarioModel = funcionario.get();
    BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
    return ResponseEntity.status(HttpStatus.OK).body(colaboradorRepository.save(funcionarioModel));
  }

  @GetMapping("/{cpf}/mensagens")
  public ResponseEntity<Object> all_mensagens(@PathVariable
   String cpf){
    var colaborador = colaboradorRepository.findByCpf(cpf);
    if(colaborador.isPresent()){
      return ResponseEntity.status(HttpStatus.OK).body(colaborador.get().getComunicados());
    }
    return ResponseEntity.status(HttpStatus.OK).body("Colaborador nao existente");
  }
  
  @GetMapping("/presenca/{cpf}")
  public ResponseEntity<Registro_Presenca> presenca_colaborador(@PathVariable String cpf){  
    return ResponseEntity.status(HttpStatus.OK).body(registroPresencaService.gerar_presenca_colaborador(cpf));
  }
}
