package com.gestao.absenteismo.controllers;

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
import com.gestao.absenteismo.services.ColaboradorService;
import com.gestao.absenteismo.services.ExceptionsServive;
import com.gestao.absenteismo.services.RegistroPresencaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

  @Autowired
  private RegistroPresencaService registroPresencaService;
  @Autowired
  private ColaboradorService colaboradorService;
  @Autowired
  private ExceptionsServive exceptionsServive;

  @DeleteMapping("/delete/{cpf}")
   public ResponseEntity<Object> deleteGestor(HttpServletRequest request, @PathVariable String cpf){
    HttpStatus status;
    var colaborador = colaboradorService.findByColaborador(cpf);
    if(colaborador.isPresent()) {
      status = HttpStatus.OK;
      colaboradorService.delete(colaborador.get());
      return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Colaborador deletado com sucesso"));
    }
    status = HttpStatus.NOT_FOUND;
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Colaborador não encontrado"));
  }

  @PutMapping("/update/{cpf}")
  public ResponseEntity<Object> updateById(HttpServletRequest request, @PathVariable String cpf,@Valid @RequestBody FuncionarioDTO funcionarioDTO){
    HttpStatus status;
    var colaborador = colaboradorService.findByColaborador(cpf);
    if(colaborador.isEmpty()) {
      status = HttpStatus.NOT_FOUND;
      return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Colaborador não reconhecido"));
    }
    status = HttpStatus.OK;
    return ResponseEntity.status(status).body(colaboradorService.update(colaborador.get(),funcionarioDTO));
  }

  @GetMapping("/{cpf}/mensagens")
  public ResponseEntity<Object> all_mensagens(HttpServletRequest request,@PathVariable String cpf){
    HttpStatus status;
    var colaborador = colaboradorService.findByColaborador(cpf);
    if(colaborador.isPresent()){
      status = HttpStatus.OK;
      return ResponseEntity.status(status).body(colaborador.get().getComunicados());
    }
    status = HttpStatus.NOT_FOUND;
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Colaborador não reconhecido"));
  }
  
  @GetMapping("/presenca/{cpf}")
  public ResponseEntity<Object> presenca_colaborador(HttpServletRequest request, @PathVariable String cpf){
    HttpStatus status;
    if(registroPresencaService.validacao(cpf)){
      status = HttpStatus.CREATED;
      return ResponseEntity.status(status).body(registroPresencaService.gerar_presenca_colaborador(cpf));
    }
    status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Colaborador não reconhecido"));
  }
}
