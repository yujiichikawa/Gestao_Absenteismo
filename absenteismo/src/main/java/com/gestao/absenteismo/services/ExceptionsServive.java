package com.gestao.absenteismo.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestao.absenteismo.controllers.exceptions.ControllerError;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ExceptionsServive {
  public ControllerError create(HttpServletRequest request, HttpStatus status, String msg){
    return new ControllerError(status.value(), status, msg, request.getRequestURI());
  }
}
