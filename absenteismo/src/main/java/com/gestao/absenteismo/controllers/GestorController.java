package com.gestao.absenteismo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.absenteismo.dtos.ComunicadoDTO;
import com.gestao.absenteismo.dtos.FuncionarioDTO;
import com.gestao.absenteismo.services.ColaboradorService;
import com.gestao.absenteismo.services.ExceptionsServive;
import com.gestao.absenteismo.services.GestorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestor")
public class GestorController {
  @Autowired
  private GestorService gestorService;  
  @Autowired
  private ExceptionsServive exceptionsServive;
  @Autowired
  private ColaboradorService colaboradorService;

  @PostMapping("/cadastro")
  public ResponseEntity<Object> cadastro_gestor(HttpServletRequest request, @Valid @RequestBody FuncionarioDTO funcionarioDTO){
    HttpStatus status;
    if(gestorService.findByGestor(funcionarioDTO.getCpf()).isPresent()){
      status = HttpStatus.BAD_REQUEST;

      return ResponseEntity.status(status).body(exceptionsServive.create(request, status,"Gestor já existente"));
    }
    status = HttpStatus.CREATED;
    return ResponseEntity.status(status).body(gestorService.cadastro(funcionarioDTO));
  }

  @PostMapping("/{cpf}/colaborador/cadastro")
  public ResponseEntity<Object> cadastro_colaborador(HttpServletRequest request, @PathVariable String cpf, @Valid @RequestBody FuncionarioDTO funcionarioDTO){
    var gestor = gestorService.findByGestor(cpf);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if(gestor.isPresent()){
      if(!colaboradorService.findByColaborador(funcionarioDTO.getCpf()).isPresent()){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.cadastro(funcionarioDTO,gestor.get()));
      }
      return ResponseEntity.status(status).body(exceptionsServive.create(request, status,"Colaborador já existente"));
    }
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status,"Gestor não reconhecido"));
  }

  @PutMapping("/update/{cpf}")
  public ResponseEntity<Object> updateById(HttpServletRequest request, @PathVariable String cpf,@Valid @RequestBody FuncionarioDTO funcionarioDTO){
    var gestor = gestorService.findByGestor(cpf);
    HttpStatus status;
    if(!gestor.isPresent()) {
      status = HttpStatus.BAD_REQUEST;
      return ResponseEntity.status(status).body(exceptionsServive.create(request, status,"Gestor não reconhecido"));
    }
    status = HttpStatus.OK;
    return ResponseEntity.status(status).body(gestorService.update(funcionarioDTO,gestor.get()));
  }

  @DeleteMapping("/delete/{cpf}")
  public ResponseEntity<Object> deleteGestor(HttpServletRequest request, @PathVariable String cpf){
    var gestor = gestorService.findByGestor(cpf);
    HttpStatus status;
    if(gestor.isPresent()) {
      if(gestor.get().getColaboradores().isEmpty()){
        status = HttpStatus.OK;
        gestorService.delete(gestor.get());
        return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Gestor foi deletado com sucesso"));
      }
      status = HttpStatus.BAD_REQUEST;
      return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Gestor com colaboradores"));
    }
    status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status,"Gestor não reconhecido"));
  } 

  @PostMapping("/{cpf_gestor}/mensagem/enviar/{cpf_colaborador}")
  public ResponseEntity<Object> comunicadoSave(HttpServletRequest request, @PathVariable(name = "cpf_gestor") String cpf_gestor,
  @PathVariable(name = "cpf_colaborador") String cpf_colaborador,@Valid @RequestBody ComunicadoDTO comunicadoDTO){
    var geOptional = gestorService.findByGestor(cpf_gestor);
    HttpStatus status = HttpStatus.NOT_FOUND;
    if(geOptional.isPresent()){
      var cOptional = colaboradorService.findByColaborador(cpf_colaborador);
      if(cOptional.isPresent()){
        status = HttpStatus.CREATED;
        return ResponseEntity.status(status).body(gestorService.enviar_comunicado(geOptional.get(), cOptional.get(), comunicadoDTO));
      }
      return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Colaborador não existente"));
    }
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Gestor não reconhecido"));
  }
  
  @GetMapping("/{cpf}/lista/colaboradores")
  public ResponseEntity<?> listar_todos(HttpServletRequest request, @PathVariable String cpf){
    var gestor = gestorService.findByGestor(cpf);
    HttpStatus status;
    if(gestor.isPresent()){
      status = HttpStatus.OK;
      return ResponseEntity.status(status).body(gestor.get().getColaboradores());  
    }
    status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).body(exceptionsServive.create(request, status, "Gestor não reconhecido"));
  }
}
