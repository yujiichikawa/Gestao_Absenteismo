package com.gestao.absenteismo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestao.absenteismo.dtos.GestorRecord;
import com.gestao.absenteismo.models.Gestor;
import com.gestao.absenteismo.repositories.GestorRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
  @Autowired
  private GestorRepository gestorRepository;

  @PostMapping("/gestor/save")
  public ResponseEntity<Gestor> saveGestor(@RequestBody GestorRecord gestorRecord){
    var gestor = new Gestor();
    BeanUtils.copyProperties(gestorRecord, gestor);
    return ResponseEntity.status(HttpStatus.CREATED).body(gestorRepository.save(gestor));
  }

  @GetMapping("/gestor/all")
  public ResponseEntity<List<Gestor>> list(){
    return ResponseEntity.status(HttpStatus.OK).body(gestorRepository.findAll());
  }

}
